import java.util.ArrayList;

public class Laser extends Sprite {
    public Laser(int x, int y) {
        setImage("orange-laser.png", Constants.laserIconWidth, Constants.laserIconHeight);
        setPos(x + Constants.spaceShipIconWidth / 2, y - spriteHeight);
    }

    public void handleCollisionWith(ArrayList<EnemyShip> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip enemyShip = enemies.get(i);
            if (this.overlapsWith(enemyShip)) {
                enemyShip.takeDamage();
                inactive = true;
            }
        }
    }

    public void move() {
        y -= Constants.laserSpeed;
        if (y < 0 - spriteHeight)
            outOfRange = true;
    }
}