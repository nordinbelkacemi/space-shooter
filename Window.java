import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        setTitle("SpaceShooter");
        
        Game game = new Game();

        GamePanel gamePanel = new GamePanel(game);
        add(gamePanel);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}