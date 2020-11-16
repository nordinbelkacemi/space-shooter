import java.awt.event.*;

public class KeyListener extends KeyAdapter {
    private GamePanel gamePanel;

    public KeyListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void keyPressed(KeyEvent e) {
        gamePanel.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        gamePanel.keyReleased(e);
    }
}