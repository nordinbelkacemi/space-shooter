import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        setTitle("SpaceShooter");
        
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}