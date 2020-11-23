package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.ArrayList;
import javax.swing.table.*;
import java.util.*;

public class SavedGamesData extends AbstractTableModel {

    private static final long serialVersionUID = -1027040641549483407L;

    private ArrayList<GameData> savedGames;
    
    public SavedGamesData() {
        savedGames = new ArrayList<GameData>();
    }  

    public int getRowCount() {
        return savedGames.size();
    }

    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int columnIndex) {
        String[] columnNames = {"Name", "Score", "Level", "Score at start of level", "Player won"};
        return  columnNames[columnIndex];
    }

    public GameData getSavedGame(int index) {
        return savedGames.get(index);
    }

    public void removeSavedGame(int rowIndex) {
        savedGames.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        GameData gameData = savedGames.get(rowIndex);
        switch (columnIndex) {
            case 0: return gameData.getName();
            case 1: return gameData.getScore();
            case 2: return gameData.getLevel();
            case 3: return gameData.getScoreAtLevelStart();
            default: return gameData.playerWon();
        }
    }

    public void addSavedGame(GameData gameData) {
        savedGames.add(gameData);
    }

    public void sortAndReduce(int finalSize) {
        Collections.sort(savedGames, new Comparator<GameData>() {
            public int compare(GameData gameData1, GameData gameData2) {
                if (gameData1.getScore() > gameData2.getScore()) {
                    return -1;
                } else if (gameData1.getScore() == gameData2.getScore()) {
                    return 0;
                } else {
                    return 1;
                }
            }  
        });

        ArrayList<GameData> tmp = new ArrayList<GameData>();
        for (int i = 0; i < savedGames.size() && i < finalSize; i++) {
            tmp.add(savedGames.get(i));
        }
        savedGames = tmp;
    }

    @SuppressWarnings("unchecked")
    public void loadSavedGames() {
        savedGames = new ArrayList<GameData>();
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
    }

    public void saveSavedGames() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.saveGamePath));
            oos.writeObject(savedGames);
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}