package pacmanprueba;

import graphics.GraphicsHandler;
import javax.swing.JFrame;

public class PacmanPrueba extends JFrame {
    
    public PacmanPrueba() {
        add(new GraphicsHandler());
    }

    public static void main(String[] args) {
        PacmanPrueba p = new PacmanPrueba();
        p.setTitle("Pacman");
        //p.setSize(352,399);
        p.setSize(472, 591);
        p.setResizable(false);
        p.setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
        p.toFront();
    }
    
}
