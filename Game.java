import java.awt.event.*;
// import javax.swing.*;

public class Game {
    private SpaceShip spaceShip;

    public Game() {
        spaceShip = new SpaceShip();
    }

    public SpaceShip getSpaceShip() {
        return spaceShip;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        spaceShip.setMoveDirection(key);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        spaceShip.stopMoveDirection(key);
    }
}