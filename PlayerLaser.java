import java.util.ArrayList;

public class PlayerLaser extends Laser {
    public PlayerLaser(int x, int y) {
        super(x, y);
        setImage(Constants.playerLaserIcon, Constants.laserIconWidth, Constants.laserIconHeight);
        this.speed = Constants.playerLaserSpeed;
    }

    public void handleCollisionWith(ArrayList<EnemyShip> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip enemyShip = enemies.get(i);
            handleCollisionWith(enemyShip);
        }
    }
}