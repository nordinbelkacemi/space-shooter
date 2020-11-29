package views;

import game.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SaveGamePanel extends JPanel {

    private static final long serialVersionUID = -9061411989723017258L;

    /**
     * Játék adatait tároló objektum
     * 
     */
    private GameData gameData;

    /**
     * A játék mentése panelt tartalmazó ablak
     * 
     */
    private Window window;

    /**
     * A név mezö
     * 
     */
    private JTextField nameField;

    /**
     * A jelszó mezö
     * 
     */
    private JPasswordField passwordField;

    /**
     * A mentett játék indexe a mentet játékok tömbjében
     * 
     */
    private int savedGameIndex = -1;
    
    /**
     * A mentett játékok adatait tároló objektum
     * 
     */
    private SavedGamesData data;

    /**
     * Játék mentés panel konstruktora
     * 
     * beállítja az ablakot az átadott ablakra, inicializálja az adatokat, és betölti öket.
     * @param window A játék mentése panelt tartalmazó ablak
     */
    SaveGamePanel(Window window) {
        this.window = window;
        data = new SavedGamesData();
        reload();
    }

    /**
     * Átállítja a játék adatokat a menteni kívánt játék adataira
     * @param gameData A menteni kívánt játék adatai
     * @param savedGameIndex A menteni kívánt játék indexe a már elmentett játékok tömbjében
     */
    public void setGameData(GameData gameData, int savedGameIndex) {
        this.gameData = gameData;
        this.savedGameIndex = savedGameIndex;
    }

    /**
     * Ujratöltö függvény
     * 
     * ujratölti az adatokat, illetve a grafikus komponenseket.
     * 
     */
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

    /**
     * A menteni kívánt játék mentett állapotát lekérdezi.
     * 
     * @return ha egy már meglévö játékmenetet indított el (es az utolsó elért szinttöl folytatta)
     * a felhasználó es most az a játékmenet folytatása kerül mentésre, akkor igazat ad vissza.
     * Különben hamisat (ilyenkor egy teljsen uj - még el nem mentett - játékmenetet mentünk el).
     * 
     */
    private boolean gameIsSaved() {
        return savedGameIndex != -1;
    }

    /**
     * Egy még el nem mentett játék adatait elmentö függvény
     * 
     * @param gameData A menteni kívánt játékmenet adatai
     */
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

    /**
     * Egy már elmentett játék adait mentö/felülíró függvény
     * 
     * @param gameData A menteni kívánt játékmenet adatai
     * @param savedGameIndex A menteni kívánt játék indexe a már elmentett játékok tömbjében
     */
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
