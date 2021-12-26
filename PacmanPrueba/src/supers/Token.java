package supers;

import gameobjects.Pacman;
import supers.GameObject;

public abstract class Token extends GameObject{

    private String icon;
    private String sound;
    
    public Token (int x, int y, String path) {
        super(x, y, 0, 0, 0);
        this.icon = path;
        this.sound = "src\\sounds\\powerUp.wav";
    }

    public abstract boolean checkCollisions(Pacman pacman, int blockSize);
    
    public String getIcon() {
        return this.icon;
    }

    public String getSound() {
        return sound;
    }
    
    
    
}
