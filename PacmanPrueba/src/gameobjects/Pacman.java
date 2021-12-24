package gameobjects;

import supers.GameObject;

public class Pacman extends GameObject{
    int lives;
    int rx, ry;
    String stop;

    public Pacman() {
        super(168,168,0,0,4);
        lives = 0;
        rx = 0;
        ry = 0;
        stop = "src\\images\\pacman.png";
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

    public void setRy(int ry) {
        this.ry = ry;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }
    
    
    
    
    
    
}
