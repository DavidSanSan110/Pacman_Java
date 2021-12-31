package gameobjects;

import supers.Token;

public class Axs extends Token{
    
    public Axs(int x, int y) {
        super(x, y, "https://i.imgur.com/Gq4Cx3C.png");
    }

    @Override
    public boolean checkCollisions(Pacman pacman, int blockSize) {
        if(pacman.getX() > getX() - (blockSize / 2) && pacman.getX() < getX() + (blockSize / 2) && pacman.getY() > getY() - (blockSize / 2) && pacman.getY() < getY() + (blockSize / 2)){
            pacman.setIsInmortal(true);
            pacman.setCounter(1, 0);
            playSound(getSound(), (float) 0.0);
            return true;
        }
        return  false;
    }
}
