import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        setSize(WIDTH, HEIGHT);
        setTitle("SpaceShooter");
        
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        setResizable(false);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}