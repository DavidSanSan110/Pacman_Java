package gameobjects;

import supers.GameObject;

public abstract class Token extends GameObject{

    private String icon;
    
    public Token (int x, int y, String path) {
        super(x, y, 0, 0, 0);
        this.icon = path;
    }
    /*
    public boolean checkCollisions(Pacman pacman, int blockSize){
        
        if(pacman.getX() > getX() - (blockSize / 2) && pacman.getX() < getX() + (blockSize / 2) && pacman.getY() > getY() - (blockSize / 2) && pacman.getY() < getY() + (blockSize / 2)){
            pacman.setSpeed(6);
            return true;
        }
        return  false;
    }
    */

    public abstract boolean checkCollisions(Pacman pacman, int blockSize);
    
    public String getIcon() {
        return this.icon;
    }
    
}
