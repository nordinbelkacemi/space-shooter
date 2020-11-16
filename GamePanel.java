import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    public static int WIDTH = 600;
    public static int HEIGHT = 600;
    public static int MARGIN = 10;
    public static int HEALTHBARWIDTH = 100;
    
    private Game game;
    private SpaceShip spaceShip;

    private Timer timer;
    private int updatePeriod = 33;

    public GamePanel() {
        init();
        startTimer();
    }

    private void init() {
        game = new Game();
        spaceShip = game.getSpaceShip();
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(new KeyListener(this));
        setFocusable(true);
    }

    private void startTimer() {
        timer = new Timer(updatePeriod, new TimerListener(this));
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHealthBar(g);
        g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), null);
    }

    private void drawHealthBar(Graphics g) {
        int health = spaceShip.getHealth();
        g.setColor(Color.WHITE);
        g.drawRect(WIDTH - MARGIN - HEALTHBARWIDTH - 20, MARGIN + 20, HEALTHBARWIDTH, 10);
        if (health > 40)
            g.setColor(Color.GREEN);
        if (health == 40)
            g.setColor(Color.GREEN);
        if (health == 20)
            g.setColor(Color.RED);
        g.fillRect(WIDTH - MARGIN - HEALTHBARWIDTH - 20, MARGIN + 20, health, 10);
    }

    public void update() {
        Sprite sprite = game.getSpaceShip();
        sprite.move();
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        game.keyReleased(e);
    }
}