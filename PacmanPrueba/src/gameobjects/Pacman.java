package gameobjects;

import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import supers.GameObject;

public class Pacman extends GameObject{
    
    int [] counter;
    int lives, score, tempLives;
    int rx, ry, rs;
    String sound, winSound;
    boolean isInmortal = false;
    URL stop, up, down, left, right, heart;
    

    public Pacman() {
        super(168,168,0,0,4);
        lives = 3;
        tempLives = 3;
        rx = 0;
        ry = 0;
        rs = -1;
        score = 0;
        counter = new int[]{0, 0};
        try {
            stop = new URL("https://i.imgur.com/xypYDPO.png"); 
            up = new URL("https://i.imgur.com/yPBEXm1.gif");
            down = new URL("https://i.imgur.com/QCBrVU9.gif");
            left = new URL("https://i.imgur.com/L8t05RA.gif");
            right = new URL("https://i.imgur.com/CCXauwj.gif");
            heart = new URL("https://i.imgur.com/lq7BoyD.png");
        } catch (MalformedURLException ex) {
            System.out.println("Error al cargar imagen");
        }
        sound = "src\\sounds\\fastWaka.wav";
        winSound = "src\\sounds\\winSound.wav";
    }
    
    public void movePacman(int[] screenData, int blockSize, int numBlocks){

        int pos;
        int posXi;
        int posYi;
        
            
        if(getX() % blockSize == 0 && getY() % blockSize == 0){
            
            if(rs != getSpeed() && rs != -1) {
                setSpeed(rs);
            }
            checkTokens();
            
            posXi = getX() / blockSize;
            posYi = getY() / blockSize;

            pos = posXi + numBlocks * posYi;
            //System.out.printf("%d\n", pos);

            checkScore(screenData, pos);
            checkRequest(screenData, pos, numBlocks);
            checkDirection(screenData, pos, numBlocks);
        }
        
        setX(getX() + getDx() * getSpeed());
        setY(getY() + getDy() * getSpeed()); 
        
        checkEndMap(blockSize, numBlocks);
    }
    
    public void checkTokens() {
        if (rs != -1) {
            if(getSpeed() == rs) {
                counter[0]++;
            }
            if(counter[0] >= 50) {
                setSpeed(4);
                rs = -1;
                counter[0] = 0;
            }
        } 
        if (isInmortal) {
            counter[1]++;
        }
        if (counter[1] >= 25) {
            setIsInmortal(false);
            counter[1] = 0;
        }
    }
    
    public void checkEndMap(int blockSize, int numBlocks) {
        //System.out.printf("%d-%d\n", getX(), getY());
        if (getX() < 0) {
            setX(blockSize * numBlocks - blockSize);
        } else if (getX() >= numBlocks * blockSize) {
            setX(0);
        }
    }
    
    public void checkRequest(int screenData[], int pos, int numBlocks){
        
        int next;
        int prev;
        
        if (pos % 14 == 0) {
            next = pos + 1;
            prev = pos + 13;
        } else if (pos % 14 == 13) {
            next = pos - 13;
            prev = pos - 1;
        } else {
            next = pos + 1;
            prev = pos - 1;
        }

        if(getRx() == 1 && getRy() == 0){
            //RIGHT
            if(screenData[next] != 0){  // no choca con solido
                setDx(getRx());
                setDy(getRy());
            }
        }
        else if(getRx() == -1 && getRy() == 0){
            //LEFT
            if(screenData[prev] != 0){  // no choca con solido
                setDx(getRx());
                setDy(getRy());
            }
        }
        else if(getRx() == 0 && getRy() == 1){
            //DOWN
            if(screenData[pos + numBlocks] != 0){  // no choca con solido
                setDx(getRx());
                setDy(getRy());
            }
        }
        else if(getRx() == 0 && getRy() == -1){
            //UP
            if(screenData[pos - numBlocks] != 0){  // no choca con solido
                setDx(getRx());
                setDy(getRy());
            }
        }
    }
    
    public void checkScore(int screenData[] , int pos) {
        if(screenData[pos] == 1) {
            setScore(getScore() + 1);
        }
        screenData[pos] = 2;
    }
    
    public void checkDirection(int screenData[], int pos, int numBlocks) {
        
        int next;
        int prev;
        
        if (pos % 14 == 0) {
            next = pos + 1;
            prev = pos + 13;
        } else if (pos % 14 == 13) {
            next = pos - 13;
            prev = pos - 1;
        } else {
            next = pos + 1;
            prev = pos - 1;
        }
        
        if(getDx() == 1 && getDy() == 0){
            //RIGHT
            if(screenData[next] == 0){  // choca con solido
                setDx(0);
                setDy(0);
                return;
            }
        }
        else if(getDx() == -1 && getDy() == 0){
            //LEFT
            if(screenData[prev] == 0){  // choca con solido
                setDx(0);
                setDy(0);
                return;
            }
        }
        else if(getDx() == 0 && getDy() == 1){
            //DOWN
            if(screenData[pos + numBlocks] == 0){  // choca con solido
                setDx(0);
                setDy(0);
                return;
            }
        }
        else if(getDx() == 0 && getDy() == -1){
            //UP
            if(screenData[pos - numBlocks] == 0){  // choca con solido
                setDx(0);
                setDy(0);
                return;
            }
        } else {
            return;
        }
        
        if (getClip() == null) {
                playSound(sound, (float) -30.0);
        } else if (getClip() != null && !getClip().isActive()) {
            playSound(sound, (float) -20.0);
        }
    }

    public String getWinSound() {
        return winSound;
    }
    
    public int getTempLives() {
        return tempLives;
    }

    public void setTempLives(int tempLives) {
        this.tempLives = tempLives;
    }    
    
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getRx() {
        return rx;
    }

    public void setRx(int rx) {
        this.rx = rx;
    }

    public int getRy() {
        return ry;
    }

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public void setRy(int ry) {
        this.ry = ry;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCounter(int token, int counter) {
        this.counter[token] = counter;
    }

    public URL getStop() {
        return stop;
    }

    public URL getUp() {
        return up;
    }

    public URL getDown() {
        return down;
    }

    public URL getLeft() {
        return left;
    }

    public URL getRight() {
        return right;
    }

    public URL getHeart() {
        return heart;
    }

    public boolean isIsInmortal() {
        return isInmortal;
    }

    public void setIsInmortal(boolean isInmortal) {
        this.isInmortal = isInmortal;
    }
    
    
 
}
