import java.io.Serializable;

public class GameData implements Serializable {
    private String name;
    private String password;
    private int level;
    private int score;
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