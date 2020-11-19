import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        setTitle("SpaceShooter");
        
        int level = 1;
        int points = 0;
        Game game = new Game(level, points);

        GamePanel gamePanel = new GamePanel(game);
        add(gamePanel);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
