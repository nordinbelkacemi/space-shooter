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

    /**
     * A mentett játékok tömbje.
     */
    private ArrayList<GameData> savedGames;
    
    /**
     * A mentett játékok osztály konstruktora. Egy új mentett játékok tömböt hoz létre.
     */
    public SavedGamesData() {
        savedGames = new ArrayList<GameData>();
    }  

    /**
     * Sorok száma
     * @return a mentett játékok száma
     * 
     */
    public int getRowCount() {
        return savedGames.size();
    }

    /**
     * Oszlopok száma
     * @return egy mentett játéknak hány feltüntetett attributuma van egy táblázatban
     * 
     */
    public int getColumnCount() {
        return 5;
    }

    /**
     * Oszlop neve
     * @param columnIndex az oszlop indexe. 0-tól kezdődik.
     * @return Az adott indexen lévő oszlopnak a neve.
     * 
     */
    public String getColumnName(int columnIndex) {
        String[] columnNames = {"Name", "Score", "Level", "Score at start of level", "Player won"};
        return  columnNames[columnIndex];
    }

    /**
     * mentett játék
     * @param index a mentett játékok tömb egy indexe
     * @return A mentett játékok tömbjének egy adott indexén lévő mentett játék.
     * 
     */
    public GameData getSavedGame(int index) {
        return savedGames.get(index);
    }

    /**
     * Mentett játékot törlő függvény
     * @param rowIndex A törlendő játék indexe a mentett játékok tömbben.
     * 
     */
    public void removeSavedGame(int rowIndex) {
        savedGames.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    /**
     * AbstreactTableModel getValueAt metódusa
     * @param rowIndex A mentett játékok táblázat egy sor indexe.
     * @param columnIndex A mentett játékok táblázat egy oszlop indexe.
     * @return A táblázatban egy sor index és oszlopindex-el adott cellának az értéke
     * 
     */
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

    /**
     * Hozzáad egy mentett játékot a mentett játékok tömbhöz.
     * @param gameData Az új mentett játék
     * 
     */
    public void addSavedGame(GameData gameData) {
        savedGames.add(gameData);
    }

    /**
     * A mentett játékok tömb valamennyi legmagasabb elért pontszámú játékait előállító függvény.
     * Csökkenő sorrendbe rendezi a mentett játékok tömb elemeit pontszám szerint és csak az első
     * n darabot tartja meg, ahol az n a finalSize paraméterrel megadható.
     * @param finalSize Az eredmény tömbben lévő játékok száma.
     */
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

    /**
     * Mentett játékok adatait betöltő függvény.
     * 
     * A Constants osztályban definiált saveGamePath elérési úton található fájlból beolvassa a
     * szerializált mentett játékok tömb adatait.
     * 
     */
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

    /**
     * Mentett játékok tömb fájlba mentése
     * 
     * A Constants osztályban definiált saveGamePath elérési úton található fájlba beleírja 
     * szerializáltan a mentett játékok tömb adatait.
     * 
     */
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
