package supers;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

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
    }
    
    public void playSound(String sound, float volume) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audio);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume); // Reduce volume by 10 decibels.
            clip.start();
        } catch (Exception ex) {
        }
    }
    
    public void playSound(Clip c) {
        c.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public Clip createSound(String sound, float volume) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            return clip;
        } catch (Exception ex) {
            return null;
        }
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
