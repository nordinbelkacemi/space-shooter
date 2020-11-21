import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;


public class SavedGamesPanel extends JPanel {
    private ArrayList<GameData> savedGames;
    private Window window;

    public SavedGamesPanel(Window window) {
        this.window = window;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        reload();
    }

    public void reload() {
        savedGames = SavedGamesData.loadSavedGames();

        removeAll();
        JButton backButton = new JButton("Back To Menu");
        add(backButton);
        if (savedGames.size() == 0) {
            JLabel label = new JLabel("No saved games yet");
            add(label);
        } else {
            for (int i = 0; i < savedGames.size(); i++) {
                GameData gameData = savedGames.get(i);
                JLabel label = new JLabel(gameData.getName() + " - " + gameData.getScore());
                add(label);
            }
        }

        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                window.showMenu();
            }
        });
    }
}