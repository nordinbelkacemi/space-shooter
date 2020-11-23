package models;

import game.*;

public class EnemyLaser extends Laser {
    public EnemyLaser(int x, int y, SpaceShip spaceShip) {
        super(x, y, spaceShip);
        setImage(Constants.enemyLaserIcon, Constants.laserIconWidth, Constants.laserIconHeight);
        this.speed = Constants.enemyLaserSpeed;
    }

    protected void giveDamage(SpaceShip spaceShip) {
        spaceShip.takeDamage(spaceShip.getDamage(), Constants.laserDamageTimeout);
    }
}
