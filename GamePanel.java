import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private Window window;
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

    public GamePanel(Game game, Window window) {
        this.game = game;
        this.window = window;
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

    private void setUpPanel() {
        setPreferredSize(new Dimension(Constants.PANELWIDTH, Constants.PANELHEIGHT));
        setBackground(Color.BLACK);
        // addKeyListener(new KeyListener(this));
        setFocusable(true);
        setKeyBindings();
    }

    private void setKeyBindings() {
        for (int i = 0; i < Constants.controlKeys.length; i++) {
            int key = Constants.controlKeys[i];
            addPressKeyBind(KeyStroke.getKeyStroke(key, 0), key);
            addReleaseKeyBind(KeyStroke.getKeyStroke(key, 0, true), key);
        }
    }

    private void addPressKeyBind(KeyStroke keyStroke, int key) {
        getInputMap(2).put(keyStroke, "pressed" + key);      
        getActionMap().put("pressed" + key, new AbstractAction(){         
            public void actionPerformed(ActionEvent a) {
                game.keyPressed(key);
            }
        });
    }

    private void addReleaseKeyBind(KeyStroke keyStroke, int key) {
        getInputMap(2).put(keyStroke, "released" + key);      
        getActionMap().put("released" + key, new AbstractAction(){         
            public void actionPerformed(ActionEvent a) {
                game.keyReleased(key);
            }
        });
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
            window.showSaveGameForm(new GameData(game));
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
    
    // public void keyPressed(KeyEvent e) {
    //     game.keyPressed(e);
    // }
    
    // public void keyReleased(KeyEvent e) {
    //     game.keyReleased(e);
    // }

    public void update() {
        if (game.isNotPaused()) {
            game.update();
        }
        repaint();
    }

    // private void showSaveGameForm() {
    //     JPanel form = new JPanel();
    //     form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));
    //     form.setBackground(Color.WHITE);
    //     form.setSize(400, 200);
    //     form.setLocation(WIDTH / 2 - 400 / 2, 3 * HEIGHT / 5 - 200 / 2);

    //     JPanel namePanel = new JPanel();
    //     JLabel nameLabel = new JLabel("Name:");
    //     JTextField nameField = new JTextField(20);
    //     namePanel.add(nameLabel);
    //     namePanel.add(nameField);

    //     JPanel passwordPanel = new JPanel();
    //     JLabel passwordLabel = new JLabel("Password:");
    //     JTextField passwordField = new JTextField(20);
    //     passwordPanel.add(passwordLabel);
    //     passwordPanel.add(passwordField);

    //     JPanel buttonsPanel = new JPanel();
    //     JButton saveButton = new JButton("Save");
    //     JButton cancelButton = new JButton("Back to Menu");
    //     buttonsPanel.add(saveButton);
    //     buttonsPanel.add(cancelButton);

    //     form.add(namePanel);
    //     form.add(passwordPanel);
    //     form.add(buttonsPanel);
        
    //     form.setVisible(true);
    //     add(form, BorderLayout.CENTER);
    // }
}
