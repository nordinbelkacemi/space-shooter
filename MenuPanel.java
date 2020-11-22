import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JButton newGameButton;
    private JButton savedGamesButton;
    private JButton leaderBoardButton;
    private ImageIcon backgroundImage;

    public MenuPanel(Window window) {
        backgroundImage = new ImageIcon(getClass().getResource(Constants.menuBackground));
        repaint();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createRigidArea(new Dimension(0,175)));

        newGameButton = new JButton("New Game");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(newGameButton);

        add(Box.createRigidArea(new Dimension(0,80)));
        
        savedGamesButton = new JButton("Saved Games");
        savedGamesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(savedGamesButton);

        add(Box.createRigidArea(new Dimension(0,80)));
        
        leaderBoardButton = new JButton("Leaderboard");
        leaderBoardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(leaderBoardButton);
        
        
    
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showGame(new Game());
            }
        });

        savedGamesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showSavedGames();
            }
        });

        leaderBoardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showLeaderBoard();
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, null);
    }
}