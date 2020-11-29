package views;

import game.*;
import models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = -5605519769265821789L;

    /**
     * A játék panel-t tartalmazó ablak
     * 
     */
    private Window window;

    /**
     * A játékpanel szélessége
     * 
     */
    private int WIDTH = Constants.PANELWIDTH;
    
    /**
     * A játékpanel szélessége
     * 
     */
    private int HEIGHT = Constants.PANELHEIGHT;

    /**
     * A játékpanel margója. Ezen belül kell maradnia a játékosnak.
     * 
     */
    private int MARGIN = Constants.PANELMARGIN;

    /**
     * A játékos életponjait feltüntetö "csík" szélessége
     * 
     */
    private int HEALTHBARWIDTH = Constants.HEALTHBARWIDTH;

    /**
     * A játékpanelen lezajló játék példány.
     * 
     */
    private Game game;

    /**
     * A játékos ürhajója
     * 
     */
    private PlayerShip playerShip;

    /**
     * A játék idözítöje
     * 
     */
    private Timer timer;

    /**
     * A játékpanel újrarajzolásának periódusa (ha ez pl 17, akkor 17 ms-onként kerül
     * ujrarajzolásra a játékpanel)
     * 
     */
    private int timerPeriod = Constants.timerPeriod;

    /**
     * A játék szünetelésekor megjelenö képernyö
     * 
     */
    private ImageIcon pauseScreen;

    /**
     * Az egyes szintek kezdetekor megjelenő képek tömbje
     * 
     */
    private ImageIcon levels[];

    /**
     * A pontszámot kiíró betütipus
     * 
     */
    private Font textFont;

    /**
     * A játékpanel konstruktora
     * 
     * @param window Az ablak amely tartalmazza a játékpanelt
     * 
     */
    public GamePanel(Window window) {
        this.window = window;
    }
    
    /**
     * Játék adatait betöltö függvény
     * 
     * @param game a játék példány amely adatait betölti a függvény
     */
    public void loadGame(Game game) {
        this.game = game;
        initData();
        setUpPanel();
        startTimer();
    }

    /**
     * Adatokat inicializáló függvény
     * 
     */
    private void initData() {
        pauseScreen = new ImageIcon(getClass().getResource(Constants.pauseScreen));
        levels = new ImageIcon[10];
        for (int i = 0; i < 10; i++) {
            levels[i] = new ImageIcon(getClass().getResource(Constants.levels[i]));
        }
        playerShip = game.getPlayerShip();
    }

    /**
     * A játékpanel grafikus és egyéb elemeit inicializáló függvény
     * 
     */
    private void setUpPanel() {
        setPreferredSize(new Dimension(Constants.PANELWIDTH, Constants.PANELHEIGHT));
        setBackground(Color.BLACK);
        setTextFont();
        setKeyBindings();
        setFocusable(true);
    }

    /**
     * Az irányító billentyuk beállítása, key bind-okkal megoldva.
     * 
     */
    private void setKeyBindings() {
        for (int i = 0; i < Constants.controlKeys.length; i++) {
            int key = Constants.controlKeys[i];

            boolean onKeyRelease = true;
            addKeyBind(KeyStroke.getKeyStroke(key, 0), key, !onKeyRelease);
            addKeyBind(KeyStroke.getKeyStroke(key, 0, true), key, onKeyRelease);
        }
    }

    /**
     * Key bindot létrehozó függvény
     * 
     * Egy adott billentyüt egy egyedi névvel ellátva hozzáadja a játékpanel input mapjára, és a
     * hozzá tartozó AbstractAction-t hozzá adja a játékpanel actionMap-jára (a megfelelö névvel).
     * @param keyStroke Az adott billentyühöz tartozó KeyStroke típusú objektum
     * @param key Az adott billentyú key code-ja (egész szám)
     * @param onKeyRelease Ha igaz akkor a billentyü elengedésére hoz létre key bind-ot, egyébként
     * a lenyomására.
     */
    private void addKeyBind(KeyStroke keyStroke, int key, boolean onKeyRelease) {
        String name;
        if (onKeyRelease) {
            name = new String("released" + key);
        } else {
            name = new String("pressed" + key);
        }

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(keyStroke, name);
        getActionMap().put(name, new AbstractAction() {

            private static final long serialVersionUID = 5996049119178103641L;

            public void actionPerformed(ActionEvent a) {
                if (onKeyRelease) {
                    game.keyReleased(key);
                } else {
                    game.keyPressed(key);
                }
            }
        });
    }

    /**
     * A pontszám kiírására használt betütípust átállító függvény
     * 
     * A Constants.pointsFont ra állítja.
     */
    private void setTextFont() {
        try {
            InputStream is = getClass().getResourceAsStream(Constants.pointsFont);
            textFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Létrehozza és elindítja az játékpanel idözítöjét.
     * 
     */
    private void startTimer() {
        timer = new Timer(timerPeriod, new TimerListener(this, this.game));
        timer.start();
    }

    /**
     * Ujrarajzolja a játékpanel komponenseit
     * 
     */
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

    /**
     * Kiirja a játékos pontszámát.
     * @param g Graphics típusú objektum amely a rajzolást végzi
     * 
     */
    private void displayPoints(Graphics g) {
        int points = playerShip.getPoints();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(textFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString(Integer.toString(points), MARGIN + 15, MARGIN + 30);
    }

    /**
     * Kirajzolja a játékos életpontjait feltüntetö "csíkot".
     * @param g Graphics típusú objektum amely a rajzolást végzi
     * 
     */
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

    /**
     * Kirajzolja a játék sprite-jait.
     * @param g Graphics típusú objektum amely a rajzolást végzi
     */
    private void drawSprites(Graphics g) {
        ArrayList<Sprite> sprites = game.getAllSprites();
        for (int i = 0; i < sprites.size(); i++) {
            Sprite sprite = sprites.get(i);
            g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), null);
        }
    }

    /**
     * Átvált a játék mentés nézetre
     * 
     */
    public void showSaveGameForm() {
        int savedGameIndex = game.getSavedGameIndex();
        window.showSaveGameForm(game.getGameData(), savedGameIndex);
    }

    /**
     * Kezeli a játék végét. Ha vége, átvált a játék mentés nézetre és leállítja az idözítöt.
     */
    public void handleGameEnd() {
        if (game.isOver()) {
            showSaveGameForm();
            timer.stop();
        }
    }

    /**
     * frissíti a játékpanelt úgy, hogy frissíti a játékot és aztán újra lerajzolja.
     * 
     */
    public void update() {
        if (game.isNotPaused()) {
            game.update();
        }
        repaint();
    }
}
