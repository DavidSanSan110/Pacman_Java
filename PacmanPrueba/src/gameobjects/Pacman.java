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
    
    public void movePacman(int[] screenData, int blockSize, int numBlocks){

        int pos;
        int posXi;
        int posYi;
        
        if(getX() % blockSize == 0 && getY() % blockSize == 0){
            posXi = getX() / blockSize;
            posYi = getY() / blockSize;

            pos = posXi + numBlocks * posYi;
            System.out.printf("%d\n", pos);

            checkScore(screenData, pos);
            checkRequest(screenData, pos, numBlocks);
            checkDirection(screenData, pos, numBlocks);
        }
        
        setX(getX() + getDx() * getSpeed());
        setY(getY() + getDy() * getSpeed()); 
        
        checkEndMap(blockSize, numBlocks);
    }
    
    public void checkEndMap(int blockSize, int numBlocks) {
        System.out.printf("%d-%d\n", getX(), getY());
        if (getX() <= -1) {
            setX(blockSize * numBlocks - blockSize);
        } else if (getX() >= numBlocks * blockSize + 1) {
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
            }
        }
        else if(getDx() == -1 && getDy() == 0){
            //LEFT
            if(screenData[prev] == 0){  // choca con solido
                setDx(0);
                setDy(0);
            }
        }
        else if(getDx() == 0 && getDy() == 1){
            //DOWN
            if(screenData[pos + numBlocks] == 0){  // choca con solido
                setDx(0);
                setDy(0);
            }
        }
        else if(getDx() == 0 && getDy() == -1){
            //UP
            if(screenData[pos - numBlocks] == 0){  // choca con solido
                setDx(0);
                setDy(0);
            }
        }
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
