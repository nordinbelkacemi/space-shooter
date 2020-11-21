import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.ArrayList;
import javax.swing.table.*;

public class SavedGamesData extends AbstractTableModel {
    ArrayList<GameData> savedGames = new ArrayList<GameData>();

    public int getRowCount() {
        return savedGames.size();
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        GameData gameData = savedGames.get(columnIndex);
        switch (columnIndex) {
            case 0: return gameData.getName();
            default: return gameData.getScore();
        }
    }

    public void setSavedGames(ArrayList<GameData> savedGames) {
        this.savedGames = savedGames;
    }

    public ArrayList<GameData> getSavedGames() {
        return savedGames;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<GameData> loadSavedGames() {
        ArrayList<GameData> savedGames = new ArrayList<GameData>();
        File file = new File(Constants.saveGamePath);
        if (file.exists() && file.length() != 0) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.saveGamePath));
                savedGames = (ArrayList<GameData>)ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return savedGames;
    }

    public static void saveSavedGames(ArrayList<GameData> savedGames) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.saveGamePath));
            oos.writeObject(savedGames);
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}