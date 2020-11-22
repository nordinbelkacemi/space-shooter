import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SaveGamePanel extends JPanel {
    private GameData gameData;
    private Window window;
    private JTextField nameField;
    private JPasswordField passwordField;
    private int savedGameIndex = -1;
    
    private SavedGamesData data;

    SaveGamePanel(Window window) {
        this.window = window;
        data = new SavedGamesData();
        reload();
    }

    public void setGameData(GameData gameData, int savedGameIndex) {
        this.gameData = gameData;
        this.savedGameIndex = savedGameIndex;
    }

    public void reload() {
        removeAll();
        data.loadSavedGames();

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));
        form.setSize(400, 200);
        form.setLocation(WIDTH / 2 - 400 / 2, 3 * HEIGHT / 5 - 200 / 2);

        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel buttonsPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);

        if (gameIsSaved()) {
            nameField.setText(gameData.getName());
            nameField.setEditable(false);
        }

        form.add(namePanel);
        form.add(passwordPanel);
        form.add(buttonsPanel);
        
        form.setVisible(true);
        add(form, BorderLayout.CENTER);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gameIsSaved()) {
                    overwrite(gameData, savedGameIndex);
                } else {
                    save(gameData);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.showMenu();
            }
        });

    }

    private boolean gameIsSaved() {
        return savedGameIndex != -1;
    }

    private void save(GameData gameData) {
        String name = nameField.getText();
        String password = new String(passwordField.getPassword());
        if (name.length() != 0 && password.length() != 0) {
            gameData.setName(name);
            gameData.setPassword(password);
            data.addSavedGame(gameData);
            data.saveSavedGames();
            window.showMenu();
        }
    }

    private void overwrite(GameData gameData, int savedGameIndex) {
        String input = new String(passwordField.getPassword());
        if (gameData.getPassword().equals(input)) {
            data.removeSavedGame(savedGameIndex);
            data.addSavedGame(gameData);
            data.saveSavedGames();
            window.showMenu();
        } else {
            JOptionPane.showMessageDialog(window,
            "Password is incorrect!",
            "Alert",
            JOptionPane.PLAIN_MESSAGE);
        }
    }
}