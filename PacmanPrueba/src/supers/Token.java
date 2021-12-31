package supers;

import gameobjects.Pacman;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class Token extends GameObject{

    private URL icon;
    private String sound;
    
    public Token (int x, int y, String path) {
        super(x, y, 0, 0, 0);
        try {
            this.icon = new URL(path);
        } catch (MalformedURLException ex) {
            System.out.println("Error al cargar imagen");
        }
        this.sound = "src\\sounds\\powerUp.wav";
    }

    public abstract boolean checkCollisions(Pacman pacman, int blockSize);
    
    public URL getIcon() {
        return this.icon;
    }

    public String getSound() {
        return sound;
    }
    
    
    
}
