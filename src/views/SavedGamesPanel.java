package views;

import game.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class SavedGamesPanel extends JPanel {

    private static final long serialVersionUID = -3973637354719708512L;

    private Window window;
    JPasswordField passwordField;
    
    private SavedGamesData data;

    public SavedGamesPanel(Window window) {
        this.window = window;
        data = new SavedGamesData();
        reload();
    }

    public void reload() {
        data.loadSavedGames();
        removeAll();
        
        this.setLayout(new BorderLayout());
        JTable table = new JTable(data);
        table.setFillsViewportHeight(true);
        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane, BorderLayout.CENTER);

        JPanel input = new JPanel(new FlowLayout());
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        JButton continueGameButton = new JButton("Continue Game");
        JButton removeGameButton = new JButton("Delete");
        input.add(passwordLabel);
        input.add(passwordField);
        input.add(continueGameButton);
        input.add(removeGameButton);
        add(input, BorderLayout.SOUTH);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        top.add(backButton, FlowLayout.LEFT);
        add(top, BorderLayout.NORTH);

        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                window.showMenu();
            }
        });

        continueGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    int index = table.getSelectedRow();
                    GameData gameData = data.getSavedGame(index);
                    String input = new String(passwordField.getPassword());
                    if (gameData.getPassword().equals(input)) {
                        Game game = new Game(gameData, index);
                        window.showGame(game);
                    } else {
                        JOptionPane.showMessageDialog(window,
                        "Password is incorrect!",
                        "Alert",
                        JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(window,
                        "Select a row first!",
                        "Alert",
                        JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        removeGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    int index = table.getSelectedRow();
                    GameData gameData = data.getSavedGame(index);
                    String input = new String(passwordField.getPassword());
                    if (gameData.getPassword().equals(input)) {
                        data.removeSavedGame(index);
                        data.saveSavedGames();
                    } else {
                        JOptionPane.showMessageDialog(window,
                        "Password is incorrect!",
                        "Alert",
                        JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(window,
                        "Select a row first!",
                        "Alert",
                        JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}