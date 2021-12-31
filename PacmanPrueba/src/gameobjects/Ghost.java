package gameobjects;

import java.net.MalformedURLException;
import java.net.URL;
import supers.GameObject;

public class Ghost extends GameObject{
    private URL icon, iconInmortal;
    private int counter;
    private String deathSound, hitSound;
    
    public Ghost() {
        super(24,24,0,0,(int) Math.floor(Math.random()*(4-1+1)+1));
        try {
            icon = new URL("https://i.imgur.com/GTz2xQC.gif");
            iconInmortal = new URL("https://i.imgur.com/oRpzk78.gif");
        } catch (MalformedURLException ex) {
            System.out.println("Error al cargar imagen");
        }
        hitSound = "src\\sounds\\toHit.wav";
        deathSound = "src\\sounds\\toDeath.wav";
        counter = 0;
    }

    public void moveGhost(int[] screenData, int blockSize, int numBlocks, Pacman pacman){
        
        int pos;
        int posXi, posYi;
        
        if(getX() % blockSize == 0 && getY() % blockSize == 0){
            posXi = getX() / blockSize;
            posYi = getY() / blockSize;

            pos = posXi + numBlocks * posYi;
            
            //checkDirection(screenData, pos, numBlocks);

            if(!checkValid(screenData, pos, numBlocks)){
                checkDirection(screenData, pos, numBlocks);
            }else{
                counter++;
                if(counter > 5){
                    checkDirection(screenData, pos, numBlocks);
                    counter = (int) Math.floor(Math.random()*(3-1+1)+1);
                }
            }
            
        }        
        
        setX(getX() + getDx() * getSpeed());
        setY(getY() + getDy() * getSpeed()); 
        
        checkCollisions(pacman, blockSize);
        checkEndMap(blockSize, numBlocks);
        
    }
    
    public void checkCollisions(Pacman pacman, int blockSize){
        
        if(pacman.getX() > getX() - (blockSize / 2) && pacman.getX() < getX() + (blockSize / 2) && pacman.getY() > getY() - (blockSize / 2) && pacman.getY() < getY() + (blockSize / 2)){
            if (!pacman.isInmortal) {
                pacman.tempLives--;
                if(pacman.tempLives > 0) {
                    playSound(hitSound, (float) 0.0);
                } else {
                    playSound(deathSound, (float) 0.0);
                }
            }
        }
    }
    
    
    public void checkEndMap(int blockSize, int numBlocks) {
        //System.out.printf("%d-%d\n", getX(), getY());
        if (getX() < 0) {
            setDx(1);
        } else if (getX() >= numBlocks * blockSize - blockSize) {
            setDx(-1);
        }
    }    
    
    public void checkDirection(int[] screenData, int pos, int numBlocks){
        
        int num;
        
        do{
        
            num = (int) Math.floor(Math.random()*(4-1+1)+1);

            switch(num){
                case 1:  //RIGHT
                    setDx(1);
                    setDy(0);
                    break;

                case 2:  //LEFT
                    setDx(-1);
                    setDy(0);
                    break;                    
                
                case 3:  //UP
                    setDx(0);
                    setDy(-1);
                    break; 
                    
                case 4:  //DOWN
                    setDx(0);
                    setDy(1);
                    break;                    
            }
                
            
        }while(!checkValid(screenData, pos, numBlocks));
        
    }    
    
    public boolean checkValid(int[] screenData, int pos, int numBlocks){
        
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
                return false;
            }
        }
        else if(getDx() == -1 && getDy() == 0){
            //LEFT
            if(screenData[prev] == 0){  // choca con solido
                return false;
            }
        }
        else if(getDx() == 0 && getDy() == 1){
            //DOWN
            if(screenData[pos + numBlocks] == 0){  // choca con solido
                return false;
            }
        }
        else if(getDx() == 0 && getDy() == -1){
            //UP
            if(screenData[pos - numBlocks] == 0){  // choca con solido
                return false;
            }
        }        
        
        return true;
        
    }

    public URL getIcon() {
        return icon;
    }

    public URL getIconInmortal() {
        return iconInmortal;
    }  
}
