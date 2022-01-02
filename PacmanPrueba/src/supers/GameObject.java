package supers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class GameObject {
    private int x, y;
    private int dx, dy;
    private int speed;
    private Clip clip;

    public GameObject(int x, int y, int dx, int dy, int speed) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
        this.clip = null;
    }
    
    public void playSound(byte [] sound, AudioInputStream ais, float volume) {
        
        InputStream is = new ByteArrayInputStream(sound);
        AudioInputStream aisf = new AudioInputStream(is, ais.getFormat(), ais.getFrameLength());
        
        try {
            if (aisf == null) return;
            clip = AudioSystem.getClip();
            clip.open(aisf);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            clip.start();
        } catch (LineUnavailableException | IOException ex) {
        }
    }
    
    public void playSound(Clip c) {
        if (c != null) {
            c.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    public Clip createSound(byte [] sound, AudioInputStream ais, float volume) {
        
        InputStream is = new ByteArrayInputStream(sound);
        AudioInputStream aisf = new AudioInputStream(is, ais.getFormat(), ais.getFrameLength());
        
        try {
            if (aisf == null) return null;
            Clip p = AudioSystem.getClip();
            p.open(aisf);
            FloatControl gainControl = (FloatControl) p.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            return p;
        } catch (LineUnavailableException | IOException ex) {
        }
        return null;
    }

    public Clip getClip() {
        return clip;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
