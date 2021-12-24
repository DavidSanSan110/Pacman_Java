package graphics;

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
import javax.swing.ImageIcon;

public class GraphicsHandler extends JPanel implements ActionListener {
    
    private final int BLOCK_SIZE = 24;
    private final int NUM_BLOCKS = 14;
    private final int SCREEN_SIZE = NUM_BLOCKS * BLOCK_SIZE;
    private Dimension d;
    private Timer timer;
    private boolean inGame;
    private Pacman pacman;
    //private int tempDirectionX;
    //private int tempDirectionY;
    
    
    
    private int [] screenData;
    private final int mapaData[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0,
        0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0,
        0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0,
        1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 
        1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 
        1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1,
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
        screenData = new int[NUM_BLOCKS * NUM_BLOCKS];
        d = new Dimension(336,336);
        inGame = false;
        pacman = new Pacman();
        
        timer = new Timer(20, this);
        timer.start();
    }
    
    private void initGraphics() {
        
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);
        
        drawMaze(g2d);
        
        if (!inGame) {
            showIntroScreen(g2d);
        } else {
            drawPacman(g2d);
        }
    }
    
    public void drawPacman(Graphics2D g2d) {
        g2d.drawImage(new ImageIcon(pacman.getStop()).getImage(), pacman.getX() + 1, pacman.getY() + 1, this);
    }
    
    private void drawMaze(Graphics2D g2d) {
        int i = 0;
        int x, y;
        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {
                g2d.setColor(new Color(113,82,186));
                g2d.setStroke(new BasicStroke(5));
                
                if((mapaData[i]) == 0) {
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }
                if((mapaData[i]) == 1) {
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 8, y + 8, 6, 6);
                }
                i++;
            }
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
                    
                }
                if (key == KeyEvent.VK_DOWN) {
                    
                }
                if (key == KeyEvent.VK_RIGHT) {
                    
                }
                if (key == KeyEvent.VK_LEFT) {
                    
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
