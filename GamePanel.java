import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private int WIDTH = Constants.PANELWIDTH;
    private int HEIGHT = Constants.PANELHEIGHT;
    private int MARGIN = Constants.PANELMARGIN;
    private int HEALTHBARWIDTH = Constants.HEALTHBARWIDTH;
    
    private Game game;
    private PlayerShip playerShip;

    private Timer timer;
    private static int timerPeriod = Constants.timerPeriod;

    private ImageIcon pauseScreen;
    private ImageIcon gameOverScreen;
    private ImageIcon levels[];
    private Font textFont;

    public GamePanel(Game game) {
        this.game = game;
        init();
        startTimer();
    }

    private void init() {
        initData();
        setUpPanel();
    }

    private void initData() {
        pauseScreen = new ImageIcon(getClass().getResource(Constants.pauseScreen));
        gameOverScreen = new ImageIcon(getClass().getResource(Constants.gameOverScreen));
        levels = new ImageIcon[10];
        for (int i = 0; i < 10; i++) {
            levels[i] = new ImageIcon(getClass().getResource(Constants.levels[i]));
        }
        setTextFont();
        playerShip = game.getPlayerShip();
    }

    private void setTextFont() {
        try {
            textFont = Font.createFont(Font.TRUETYPE_FONT, new File(Constants.pointsFont)).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(Constants.pointsFont)));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void setUpPanel() {
        setPreferredSize(new Dimension(Constants.PANELWIDTH, Constants.PANELHEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(new KeyListener(this));
        setFocusable(true);
    }

    private void startTimer() {
        timer = new Timer(timerPeriod, new TimerListener(this, this.game));
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (game.isNotPaused()) {
            if (game.startingNewLevel()) {
                g.drawImage(levels[game.getCurrentLevel() - 1].getImage(),
                            WIDTH / 2 - Constants.levelLabelWidth / 2,
                            HEIGHT / 2 - Constants.levelLabelHeight / 2,
                            null);
            }
            displayPoints(g);
            drawHealthBar(g);
            drawSprites(g);
        } else {
            g.drawImage(pauseScreen.getImage(), 0, 0, null);
        }

        if (game.isOver()) {
            g.drawImage(gameOverScreen.getImage(), 0, 0, null);
        }
    }

    private void displayPoints(Graphics g) {
        int points = playerShip.getPoints();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(textFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString(Integer.toString(points), MARGIN, MARGIN + 10);
    }
    
    private void drawHealthBar(Graphics g) {
        int health = playerShip.getHealth();
        
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
        if (game.isNotPaused()) {
            game.update();
        }
        repaint();
    }
}
