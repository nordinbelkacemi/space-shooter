import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SaveGamePanel extends JPanel {
    private GameData gameData;
    private Window window;

    SaveGamePanel(Window window) {
        this.window = window;
        reload();
    }

    public void setGame(GameData gameData) {
        this.gameData = gameData;
    }

    public void reload() {
        removeAll();

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));
        // form.setBackground(Color.WHITE);
        form.setSize(400, 200);
        form.setLocation(WIDTH / 2 - 400 / 2, 3 * HEIGHT / 5 - 200 / 2);

        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel buttonsPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Back to Menu");
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);

        form.add(namePanel);
        form.add(passwordPanel);
        form.add(buttonsPanel);
        
        form.setVisible(true);
        add(form, BorderLayout.CENTER);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String password = passwordField.getText();
                if (name.length() != 0 && password.length() != 0) {
                    gameData.setName(name);
                    gameData.setPassword(password);
                    ArrayList<GameData> savedGames = SavedGamesData.loadSavedGames();
                    savedGames.add(gameData);
                    SavedGamesData.saveSavedGames(savedGames);
                    window.showMenu();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showMenu();
            }
        });
    }
}