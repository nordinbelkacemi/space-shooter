import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private int WIDTH = Constants.PANELWIDTH;
    private int HEIGHT = Constants.PANELHEIGHT;
    private int MARGIN = Constants.PANELMARGIN;
    private int HEALTHBARWIDTH = Constants.HEALTHBARWIDTH;
    
    private Game game;
    private SpaceShip spaceShip;

    private Timer timer;
    private static int timerPeriod = Constants.timerPeriod;

    public GamePanel() {
        init();
        startTimer();
    }

    private void init() {
        initGameData();
        setUpPanel();
    }

    private void initGameData() {
        game = new Game();
        spaceShip = game.getSpaceShip();
    }

    private void setUpPanel() {
        setPreferredSize(new Dimension(Constants.PANELWIDTH, Constants.PANELHEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(new KeyListener(this));
        setFocusable(true);
    }

    private void startTimer() {
        timer = new Timer(timerPeriod, new TimerListener(this));
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHealthBar(g);
        drawSprites(g);
    }
    
    private void drawHealthBar(Graphics g) {
        int health = spaceShip.getHealth();
        
        g.setColor(Color.WHITE);
        g.drawRect(WIDTH - MARGIN - HEALTHBARWIDTH - 20, MARGIN + 20, HEALTHBARWIDTH, 10);
        
        if (health > 40)
        g.setColor(Color.GREEN);
        if (health == 40)
        g.setColor(Color.ORANGE);
        if (health == 20)
        g.setColor(Color.RED);
        g.fillRect(WIDTH - MARGIN - HEALTHBARWIDTH - 20, MARGIN + 20, health, 10);
    }
    
    private void drawSprites(Graphics g) {
        ArrayList<Sprite> sprites = game.getAllSprites();
        for (int i = 0; i < sprites.size(); i++) {
            Sprite sprite = sprites.get(i);
            g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), null);
        } 
    }
    
    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
    }
    
    public void keyReleased(KeyEvent e) {
        game.keyReleased(e);
    }

    public void update() {
        game.update();
        repaint();
    }
}