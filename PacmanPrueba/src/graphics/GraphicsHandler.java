package graphics;

import gameobjects.Ghost;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import gameobjects.Pacman;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class GraphicsHandler extends JPanel implements ActionListener {
    
    private final int BLOCK_SIZE = 24;
    private final int NUM_BLOCKS = 14;
    private final int MAX_GHOSTS = 6;
    private final int SCREEN_SIZE = NUM_BLOCKS * BLOCK_SIZE;
    private Dimension d;
    private Timer timer;
    private boolean inGame;
    private Pacman pacman;
    private ArrayList<Ghost> ghosts;
    //private int tempDirectionX;
    //private int tempDirectionY;
    
    
    
    private int screenData[];
    private final int mapaData[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0,
        0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0,
        0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0,
        0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 
        1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 
        0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0,
        0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0,
        0, 1, 1, 1, 0, 1, 0, 0, 1, 0 ,1, 1, 1, 0,
        0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    };
    
    public GraphicsHandler() {
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGraphics();
    }
    
    private void initVariables() {
        int i = 0;
        
        pacman = new Pacman();
        ghosts = new ArrayList<>();
        for (i = 0; i < MAX_GHOSTS; i++) {
            ghosts.add(new Ghost());
        }
        
        screenData = new int[NUM_BLOCKS * NUM_BLOCKS];
        for (i = 0; i < NUM_BLOCKS * NUM_BLOCKS; i++) {
            screenData[i] = mapaData[i];
        }
        
    }
    
    private void initGraphics() {
        d = new Dimension(336,360);
        inGame = false;
        
        timer = new Timer(40, this);
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);
        
        drawMaze(g2d);
        drawScore(g2d);
        drawLives(g2d);
        
        if (!inGame) {
            showIntroScreen(g2d);
        } else { 
            //drawPacman(g2d);
            playGame(g2d);
        }
    }
    
    private void playGame(Graphics2D g2d) {
        //movePacman(g2d)
        pacman.movePacman(screenData, BLOCK_SIZE, NUM_BLOCKS);
        drawPacman(g2d);
        for (Ghost g : ghosts) {
            g.moveGhost(screenData, BLOCK_SIZE, NUM_BLOCKS, pacman);
            drawGhost(g2d, g);
        }
        
        if(pacman.getTempLives() <= 0){

            initVariables();
            inGame = false;
            
        }else if(pacman.getTempLives() < pacman.getLives()){
            
            reset();
            pacman.setLives(pacman.getTempLives());
            
        }
    }
    
    private void reset(){
        
        pacman.setX(168);
        pacman.setY(168);
        
        for(Ghost g : ghosts){
            
            g.setX(24);
            g.setY(24);
            
        }
        
    }
    
    private void drawPacman(Graphics2D g2d) {
        if (pacman.getDx() == 1 && pacman.getDy() == 0) {
            g2d.drawImage(new ImageIcon(pacman.getRight()).getImage(), pacman.getX() + 1, pacman.getY() + 1, this);
        } else if (pacman.getDx() == -1 && pacman.getDy() == 0) {
            g2d.drawImage(new ImageIcon(pacman.getLeft()).getImage(), pacman.getX() + 1, pacman.getY() + 1, this);
        } else if (pacman.getDx() == 0 && pacman.getDy() == 1) {
            g2d.drawImage(new ImageIcon(pacman.getDown()).getImage(), pacman.getX() + 1, pacman.getY() + 1, this);
        } else if (pacman.getDx() == 0 && pacman.getDy() == -1) {
            g2d.drawImage(new ImageIcon(pacman.getUp()).getImage(), pacman.getX() + 1, pacman.getY() + 1, this);
        } else {
            g2d.drawImage(new ImageIcon(pacman.getStop()).getImage(), pacman.getX() + 1, pacman.getY() + 1, this);
        }
    }
    
    private void drawGhost(Graphics2D g2d, Ghost g) {
            g2d.drawImage(new ImageIcon(g.getIcon()).getImage(), g.getX() + 1, g.getY() + 1, this);
    }
    
    private void drawMaze(Graphics2D g2d) {
        int i = 0;
        int x, y;
        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {
                g2d.setColor(new Color(113,82,186));
                g2d.setStroke(new BasicStroke(5));
                
                if((screenData[i]) == 0) {
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }
                if((screenData[i]) == 1) {
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 8, y + 8, 6, 6);
                }
                i++;
            }
        }
    }
    
    private void drawScore(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(new Color(255, 255, 255));
        String score = "Score: " + pacman.getScore();
        //String lives = "Lives: " + pacman.getLives();
        g2d.drawString(score, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);
        //g2d.drawString(lives, SCREEN_SIZE / 10, SCREEN_SIZE + 16);
        
    }   
    
    private void drawLives(Graphics2D g2d){
        
        int i;
        
        for(i = 0; i < pacman.getLives(); i++){
            
            g2d.drawImage(new ImageIcon(pacman.getHeart()).getImage(), SCREEN_SIZE / 20 + i * (BLOCK_SIZE / 2), SCREEN_SIZE, this);
            
        }
             
        
    }
    
    private void showIntroScreen(Graphics2D g2d) {
        String intro = "PRESS SPACE TO START";
        g2d.setColor(Color.yellow);
        g2d.drawString(intro, 96, 168);
    }

    class TAdapter implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            return;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(!inGame) {
                if (key == KeyEvent.VK_SPACE) {
                    inGame = true;
                }
            } else {
                if (key == KeyEvent.VK_UP) {
                    pacman.setRy(-1);
                    pacman.setRx(0);
                } else if (key == KeyEvent.VK_DOWN) {
                    pacman.setRy(1);
                    pacman.setRx(0);
                } else if (key == KeyEvent.VK_LEFT) {
                    pacman.setRy(0);
                    pacman.setRx(-1);
                } else if (key == KeyEvent.VK_RIGHT) {
                    pacman.setRy(0);
                    pacman.setRx(1);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            return;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
}
