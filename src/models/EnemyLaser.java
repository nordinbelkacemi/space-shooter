package models;

import game.*;

public class EnemyLaser extends Laser {

    /**
     * Egy ellenség lézer konstruktora
     * 
     * létrehoz egy lézer objektumot a megfelelö képpel és sebességgel.
     * @param spaceShip
     */
    public EnemyLaser(SpaceShip spaceShip) {
        super(spaceShip);
        setImage(Constants.enemyLaserIcon, Constants.laserIconWidth, Constants.laserIconHeight);
        this.speed = Constants.enemyLaserSpeed;
    }

    /**
     * give damage függvény
     *
     * A játékos űrhajójának sebzést ad.
     * @param spaceShip az a játékos űrhajó amelynek sebzést ad
     */
    protected void giveDamage(SpaceShip spaceShip) {
        spaceShip.takeDamage(spaceShip.getDamage(), Constants.laserDamageTimeout);
    }
}
