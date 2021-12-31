package gameobjects;

import supers.Token;

public class Slp extends Token{
    
    public Slp(int x, int y) {
        super(x, y, "https://i.imgur.com/TzaCkr1.png");
    }

    @Override
    public boolean checkCollisions(Pacman pacman, int blockSize) {
        if(pacman.getX() > getX() - (blockSize / 2) && pacman.getX() < getX() + (blockSize / 2) && pacman.getY() > getY() - (blockSize / 2) && pacman.getY() < getY() + (blockSize / 2)){
            pacman.setRs(6);
            pacman.setCounter(0, 0);
            playSound(getSound(), (float) 0.0);
            return true;
        }
        return  false;
    }
}
