package gameobjects;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import supers.Token;

public class Axs extends Token{
    
    public Axs(int x, int y, URL iconp, byte [] a1, AudioInputStream a1ais) {
        super(x, y, iconp, a1, a1ais);
    }

    @Override
    public boolean checkCollisions(Pacman pacman, int blockSize) {
        if(pacman.getX() > getX() - (blockSize / 2) && pacman.getX() < getX() + (blockSize / 2) && pacman.getY() > getY() - (blockSize / 2) && pacman.getY() < getY() + (blockSize / 2)){
            pacman.setIsInmortal(true);
            pacman.setCounter(1, 0);
            playSound(getPU(), getPUais(), (float) 0.0);
            return true;
        }
        return  false;
    }
}
