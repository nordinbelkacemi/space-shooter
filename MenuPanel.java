import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    private Window window;
    private JButton newGameButton;
    private JButton savedGamesButton;
    private JButton leaderBoardButton;
    private JButton exitButton;

    public MenuPanel(Window window) {
        newGameButton = new JButton("New Game");
        savedGamesButton = new JButton("Saved Games");
        leaderBoardButton = new JButton("LeaderBoard");
        exitButton = new JButton("Exit");

        add(newGameButton);
        add(savedGamesButton);
        add(leaderBoardButton);
        add(exitButton);
    
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.initGamePanel(new Game());
                window.showGame();
            }
        });

        savedGamesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showSavedGames();
            }
        });
    }
}