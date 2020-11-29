package models;

import game.Constants;
import java.util.ArrayList;

public class PlayerLaser extends Laser {
    /**
     * Játékos lézer konstruktor
     * 
     * A lézer az őt kilövő űrhajó poziciójához illeszkedően jön létre.
     * @param spaceShip A lézert kilövő űrhajó.
     * 
     */
    public PlayerLaser(SpaceShip spaceShip) {
        super(spaceShip);
        setImage(Constants.playerLaserIcon, Constants.laserIconWidth, Constants.laserIconHeight);
        this.speed = Constants.playerLaserSpeed;
    }

    /**
     * Ellenség űrhajókkal való ütközéseket kezelő függvény
     * 
     * @param enemyShips Az ellenség űrhajók amelyekre vizsgálja az ütközés feltételét.
     * 
     */
    public void handleCollisionWith(ArrayList<EnemyShip> enemyShips) {
        for (int i = 0; i < enemyShips.size(); i++) {
            EnemyShip enemyShip = enemyShips.get(i);
            handleCollisionWith(enemyShip);
        }
    }

    /**
     * Sebzést adó függvény.
     * 
     * Ha nem inaktív és nincsen robbanó állpotban a lézerrel ütköző űrhajó, pontokat szerez a
     * játékos, illetve sebzést kap az űrhajó.
     * @param spaceShip Az az űrhajó amelynek sebzést ad a lézer.
     * 
     */
    protected void giveDamage(SpaceShip spaceShip) {
        int points = this.spaceShip.getPoints();
        if (inactive == false && !spaceShip.isExploding()) {
            this.spaceShip.setPoints(points + Constants.pointsPerDamage);
        }
        int damageTimeout = 0;
        spaceShip.takeDamage(spaceShip.getDamage(), damageTimeout);
    }
}
