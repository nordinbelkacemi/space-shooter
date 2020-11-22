import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private SavedGamesPanel savedGamesPanel;
    private SaveGamePanel saveGamePanel;
    private LeaderBoardPanel leaderBoardPanel;
    
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

    public void showGame(Game game) {
        gamePanel.loadGame(game);
        cardLayout.show(contentPanel, "gamePanel");
    }
    
    public void showMenu() {
        cardLayout.show(contentPanel, "menuPanel");
    }

    public void showSavedGames() {
        savedGamesPanel.reload();
        cardLayout.show(contentPanel, "savedGamesPanel");
    }

    public void showSaveGameForm(GameData gameData, int savedGameIndex) {
        saveGamePanel.setGameData(gameData, savedGameIndex);
        saveGamePanel.reload();
        cardLayout.show(contentPanel, "saveGamePanel");
    }

    public void showLeaderBoard() {
        leaderBoardPanel.reload();
        cardLayout.show(contentPanel, "leaderBoardPanel");
    }
}
