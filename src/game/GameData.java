package game;

import java.io.Serializable;

public class GameData implements Serializable {

    private static final long serialVersionUID = 6873345096784176083L;

    /**
     * A játékmenet neve.
     *  
     */
    private String name;

    /**
     * A játékmenet jelszava.
     *  
     */
    private String password;

    /**
     * A játékmenet utolsó elért szintje.
     *  
     */
    private int level;

    /**
     * A játékos pontszáma meghaláskor.
     *  
     */
    private int score;

    /**
     * A játékos pontszáma az utolsó elért szint elején.
     *  
     */
    private int scoreAtLevelStart;
    private boolean playerWon;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getScoreAtLevelStart() {
        return scoreAtLevelStart;
    }

    public void setScoreAtLevelStart(int score) {
        scoreAtLevelStart = score;
    }

    public Boolean playerWon() {
        return playerWon;
    }

    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
    }
}
