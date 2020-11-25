package models;

import game.*;

public class EnemyLaser extends Laser {
    public EnemyLaser(SpaceShip spaceShip) {
        super(spaceShip);
        setImage(Constants.enemyLaserIcon, Constants.laserIconWidth, Constants.laserIconHeight);
        this.speed = Constants.enemyLaserSpeed;
    }

    protected void giveDamage(SpaceShip spaceShip) {
        spaceShip.takeDamage(spaceShip.getDamage(), Constants.laserDamageTimeout);
    }
}
