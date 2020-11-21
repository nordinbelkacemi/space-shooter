import java.io.Serializable;

public class GameData implements Serializable {

    private String name;
    private String password;
    private int level;
    private int score;

    GameData(Game game) {
        this.level = game.getCurrentLevel();
        this.score = game.getPointsAtLevelStart();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean passwordMatches(String input) {
        return password.equals(input);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }
}