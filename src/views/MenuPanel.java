package views;

import game.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private static final long serialVersionUID = -859601827275645340L;

    /**
     * Uj játék gomb
     * 
     */
    private JButton newGameButton;

    /**
     * Mentett játékok gomb
     * 
     */
    private JButton savedGamesButton;

    /**
     * Dicsöséglista gomb
     * 
     */
    private JButton leaderBoardButton;

    /**
     * Háttérkép
     * 
     */
    private ImageIcon backgroundImage;

    /**
     * Menü panel konstruktora
     * 
     * @param window
     */
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

    /**
     * Megjeleníti a háttérképet a menü panelen
     * 
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, null);
    }
}
