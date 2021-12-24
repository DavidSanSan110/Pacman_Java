package gameobjects;

import supers.GameObject;

public class Pacman extends GameObject{
    int lives, score;
    int rx, ry;
    String stop, up, down, right, left;
    

    public Pacman() {
        super(168,168,0,0,4);
        lives = 0;
        rx = 0;
        ry = 0;
        score = 0;
        stop = "src\\images\\pacman.png";
        up = "src\\images\\up.gif";
        down = "src\\images\\down.gif";
        right = "src\\images\\right.gif";
        left = "src\\images\\left.gif";
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

    public String getUp() {
        return up;
    }

    public String getDown() {
        return down;
    }

    public String getRight() {
        return right;
    }

    public String getLeft() {
        return left;
    }
    
    

    
}
