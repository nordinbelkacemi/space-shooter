import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.InputStream;

public class GamePanel extends JPanel {
    private Window window;
    private int WIDTH = Constants.PANELWIDTH;
    private int HEIGHT = Constants.PANELHEIGHT;
    private int MARGIN = Constants.PANELMARGIN;
    private int HEALTHBARWIDTH = Constants.HEALTHBARWIDTH;
    private Game game;
    private PlayerShip playerShip;
    private Timer timer;
    private int timerPeriod = Constants.timerPeriod;
    private ImageIcon pauseScreen;
    private ImageIcon levels[];
    private Font textFont;

    public GamePanel(Window window) {
        this.window = window;
    }
    
    public void loadGame(Game game) {
        this.game = game;
        initData();
        setUpPanel();
        startTimer();
    }

    private void initData() {
        pauseScreen = new ImageIcon(getClass().getResource(Constants.pauseScreen));
        levels = new ImageIcon[10];
        for (int i = 0; i < 10; i++) {
            levels[i] = new ImageIcon(getClass().getResource(Constants.levels[i]));
        }
        playerShip = game.getPlayerShip();
    }

    private void setUpPanel() {
        setPreferredSize(new Dimension(Constants.PANELWIDTH, Constants.PANELHEIGHT));
        setBackground(Color.BLACK);
        setTextFont();
        setKeyBindings();
        setFocusable(true);
    }

    private void setKeyBindings() {
        for (int i = 0; i < Constants.controlKeys.length; i++) {
            int key = Constants.controlKeys[i];

            boolean onKeyRelease = true;
            addKeyBind(KeyStroke.getKeyStroke(key, 0), key, !onKeyRelease);
            addKeyBind(KeyStroke.getKeyStroke(key, 0, true), key, onKeyRelease);
        }
    }

    private void addKeyBind(KeyStroke keyStroke, int key, boolean onKeyRelease) {
        String name;
        if (onKeyRelease) {
            name = new String("released" + key);
        } else {
            name = new String("pressed" + key);
        }

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(keyStroke, name);
        getActionMap().put(name, new AbstractAction() {
            public void actionPerformed(ActionEvent a) {
                if (onKeyRelease) {
                    game.keyReleased(key);
                } else {
                    game.keyPressed(key);
                }
            }
        });
    }

    private void setTextFont() {
        try {
            InputStream is = getClass().getResourceAsStream(Constants.pointsFont);
            textFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startTimer() {
        timer = new Timer(timerPeriod, new TimerListener(this, this.game));
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (game.isNotPaused()) {
            if (game.startingNewLevel()) {
                g.drawImage(levels[game.getCurrentLevel() - 1].getImage(), WIDTH / 2 - Constants.levelLabelWidth / 2,
                        HEIGHT / 2 - Constants.levelLabelHeight / 2, null);
            }
            displayPoints(g);
            drawHealthBar(g);
            drawSprites(g);
        } else {
            g.drawImage(pauseScreen.getImage(), 0, 0, null);
        }

        handleGameEnd();
    }

    private void displayPoints(Graphics g) {
        int points = playerShip.getPoints();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(textFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString(Integer.toString(points), MARGIN + 15, MARGIN + 30);
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

    public void showSaveGameForm() {
        int savedGameIndex = game.getSavedGameIndex();
        window.showSaveGameForm(game.getGameData(), savedGameIndex);
    }

    public void handleGameEnd() {
        if (game.isOver()) {
            showSaveGameForm();
            timer.stop();
        }
    }

    public void update() {
        if (game.isNotPaused()) {
            game.update();
        }
        repaint();
    }
}
