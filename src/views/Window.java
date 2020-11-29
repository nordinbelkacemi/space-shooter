package views;

import game.*;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static final long serialVersionUID = 5165378072367073281L;

    /**
     * A tartalmakat összesítö panel
     * 
     */
    private JPanel contentPanel;

    /**
     * A card layout
     * 
     * Ezzel tesszük lehetövé az egyes panelek (játék panel, játék mentése panel, mentett játékok
     * panel és dicsöség lista panel) közti váltást.
     * 
     */
    private CardLayout cardLayout;

    /**
     * A menü panel
     * 
     */
    private MenuPanel menuPanel;

    /**
     * A játék panel
     * 
     */
    private GamePanel gamePanel;

    /**
     * A mentett játékok panel
     * 
     */
    private SavedGamesPanel savedGamesPanel;

    /**
     * A játék mentés panel
     * 
     */
    private SaveGamePanel saveGamePanel;

    /**
     * A dicsöséglista panel
     * 
     */
    private LeaderBoardPanel leaderBoardPanel;
    
    /**
     * Az ablak konstruktora.
     * 
     * Inicializálja a grafikus komponenseket és átvált a menü panelre.
     * 
     */
    public Window() {
        setTitle("SpaceShooter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPanel = new JPanel();
        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);
        savedGamesPanel = new SavedGamesPanel(this);
        saveGamePanel = new SaveGamePanel(this);
        leaderBoardPanel = new LeaderBoardPanel(this);
        cardLayout = new CardLayout();

        contentPanel.setLayout(cardLayout);
        contentPanel.add(menuPanel, "menuPanel");
        contentPanel.add(gamePanel, "gamePanel");
        contentPanel.add(savedGamesPanel, "savedGamesPanel");
        contentPanel.add(saveGamePanel, "saveGamePanel");
        contentPanel.add(leaderBoardPanel, "leaderBoardPanel");

        contentPanel.setPreferredSize(new Dimension(Constants.PANELWIDTH, Constants.PANELHEIGHT));
        add(contentPanel);
        pack();
        setVisible(true);

        showMenu();
    }

    /**
     * Játék panelre váltó függvény.
     * 
     * Betölti az adott játékot (újonnan létrehozott vagy már mentettet) és átvált a játék panelre.
     * @param game A betöltésre kerülö játék
     */
    public void showGame(Game game) {
        gamePanel.loadGame(game);
        cardLayout.show(contentPanel, "gamePanel");
    }
    
    /**
     * A menü panelre átváltó függvény
     * 
     */
    public void showMenu() {
        cardLayout.show(contentPanel, "menuPanel");
    }

    /**
     * A mentett játékok panelre átváltó függvény.
     * 
     * újratölti a mentett játékok panelt a reload függvényével és átvált rá.
     * 
     */
    public void showSavedGames() {
        savedGamesPanel.reload();
        cardLayout.show(contentPanel, "savedGamesPanel");
    }

    /**
     * A játék mentése panelre átváltó függvény.
     * 
     * Beállítja a játék mentése panel mentésre kerülö játékát a menteni kívánt játékra, újratölti
     * a játék mentése panelt a reload függvényével és átvált rá.
     * 
     */
    public void showSaveGameForm(GameData gameData, int savedGameIndex) {
        saveGamePanel.setGameData(gameData, savedGameIndex);
        saveGamePanel.reload();
        cardLayout.show(contentPanel, "saveGamePanel");
    }

    /**
     * A dicsöséglista panelre átváltó függvény
     * 
     * Ujratölti a dicsöségpanel tartalmát a reload függvényével és átvált rá.
     */
    public void showLeaderBoard() {
        leaderBoardPanel.reload();
        cardLayout.show(contentPanel, "leaderBoardPanel");
    }
}
