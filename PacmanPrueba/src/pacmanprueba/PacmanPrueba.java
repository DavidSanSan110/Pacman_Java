package pacmanprueba;

import graphics.GraphicsHandler;
import javax.swing.JFrame;
import gameobjects.Pacman;
import java.util.HashSet;

public class PacmanPrueba extends JFrame {
    
    public PacmanPrueba() {
        add(new GraphicsHandler());
    }

    public static void main(String[] args) {
        PacmanPrueba p = new PacmanPrueba();
        p.setVisible(true);
        p.setTitle("Pacman");
        p.setSize(352,375);
        p.setResizable(false);
        p.setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLocationRelativeTo(null);
    }
    
}
