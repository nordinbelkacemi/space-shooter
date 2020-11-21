import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private SavedGamesPanel savedGamesPanel;
    private SaveGamePanel saveGamePanel;

    // private ArrayList<Game> savedGames = SavedGamesData.loadSavedGames();
    
    public Window() {
        setTitle("SpaceShooter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPanel = new JPanel();
        initGamePanel(new Game());
        menuPanel = new MenuPanel(this);
        cardLayout = new CardLayout();
        savedGamesPanel = new SavedGamesPanel(this);
        saveGamePanel = new SaveGamePanel(this);

        contentPanel.setLayout(cardLayout);
        contentPanel.add(menuPanel, "menuPanel");
        contentPanel.add(gamePanel, "gamePanel");
        contentPanel.add(savedGamesPanel, "savedGamesPanel");
        contentPanel.add(saveGamePanel, "saveGamePanel");

        contentPanel.setPreferredSize(new Dimension(Constants.PANELWIDTH, Constants.PANELHEIGHT));
        add(contentPanel);
        pack();
        setVisible(true);

        showMenu();
    }

    public void initGamePanel(Game game) {
        gamePanel = new GamePanel(game, this);
    }

    public void showGame() {
        cardLayout.show(contentPanel, "gamePanel");
    }
    
    public void showMenu() {
        cardLayout.show(contentPanel, "menuPanel");
    }

    public void showSavedGames() {
        savedGamesPanel.reload();
        cardLayout.show(contentPanel, "savedGamesPanel");
    }

    public void showSaveGameForm(GameData gameData) {
        saveGamePanel.reload();
        saveGamePanel.setGame(gameData);
        cardLayout.show(contentPanel, "saveGamePanel");
    }
}
