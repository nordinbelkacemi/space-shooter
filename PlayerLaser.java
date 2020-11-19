import java.util.ArrayList;

public class PlayerLaser extends Laser {
    public PlayerLaser(int x, int y, SpaceShip spaceShip) {
        super(x, y, spaceShip);
        setImage(Constants.playerLaserIcon, Constants.laserIconWidth, Constants.laserIconHeight);
        this.speed = Constants.playerLaserSpeed;
    }

    public void handleCollisionWith(ArrayList<EnemyShip> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip enemyShip = enemies.get(i);
            handleCollisionWith(enemyShip);
        }
    }

    protected void giveDamage(SpaceShip spaceShip) {
        int points = this.spaceShip.getPoints();
        if (inactive == false && !spaceShip.isExploding()) {
            this.spaceShip.setPoints(points + Constants.pointsPerDamage);
        }
        int damageTimeout = 0;
        spaceShip.takeDamage(spaceShip.getDamage(), damageTimeout);
    }
}
