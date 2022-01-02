package supers;

import gameobjects.Pacman;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;

public abstract class Token extends GameObject{

    private URL icon;
    byte [] pU;
    AudioInputStream pUais;
    
    public Token (int x, int y, URL iconp, byte [] a1, AudioInputStream a1ais) {
        super(x, y, 0, 0, 0);
        this.icon = iconp;
        this.pU = a1;
        this.pUais = a1ais;
    }

    public abstract boolean checkCollisions(Pacman pacman, int blockSize);
    
    public URL getIcon() {
        return this.icon;
    }

    public byte[] getPU() {
        return pU;
    }

    public AudioInputStream getPUais() {
        return pUais;
    }

    
    
    
    
    
    
}
