package gameobjects;

public class Axs extends Token{
    
    public Axs(int x, int y) {
        super(x, y, "src\\images\\axs.png");
    }

    @Override
    public boolean checkCollisions(Pacman pacman, int blockSize) {
        if(pacman.getX() > getX() - (blockSize / 2) && pacman.getX() < getX() + (blockSize / 2) && pacman.getY() > getY() - (blockSize / 2) && pacman.getY() < getY() + (blockSize / 2)){
            pacman.setIsInmortal(true);
            pacman.setCounter(1, 0);
            return true;
        }
        return  false;
    }
}
