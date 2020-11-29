package models;

import game.Constants;
import java.awt.event.*;
import java.util.ArrayList;

public class PlayerShip extends SpaceShip{
    /**
     * A játékos űrhajó ikonjának a piros változatának képfájljának az elérési útja.
     * 
     */
    private String redImagePath;

    /**
     * Játékos űrhajó konstruktor
     * 
     * Beállítja a kezdeti életpontokat, pozíciót, sebességet és egyéb alapadatokat.
     * 
     */
    public PlayerShip() {
        setImage(Constants.playerShipIcon, Constants.playerShipIconWidth, Constants.playerShipIconHeight);
        imagePath = Constants.playerShipIcon;
        redImagePath = Constants.redSpaceShipIcon;

        health = 100;
        setPos(Constants.PANELWIDTH / 2 - spriteWidth / 2, Constants.PANELHEIGHT - 100);
        xSpeed = 0;
        ySpeed = 0;
        timeUntilNextDamage = 0;
        damage = Constants.playerDamage;
    }

    /**
     * A mozgási irányt beállító függvény.
     * 
     * Beállítja a mozgási irányt
     * @param key
     */
    public void setMoveDirection(int key) {
        if (key == KeyEvent.VK_W)
            ySpeed = -Constants.playerSpeed;
        
        if (key == KeyEvent.VK_A)
            xSpeed = -Constants.playerSpeed;

        if (key == KeyEvent.VK_S)
            ySpeed = Constants.playerSpeed;
        
        if (key == KeyEvent.VK_D)
            xSpeed = Constants.playerSpeed;
    }

    /**
     * Mozgást leállító függvény
     * 
     * Lenullázza a sebességet egy adott irányban.
     * @param key
     */
    public void stopMoveDirection(int key) {
        if (key == KeyEvent.VK_W && ySpeed < 0)
            ySpeed = 0;
        
        if (key == KeyEvent.VK_S && ySpeed > 0)
            ySpeed = 0;

        if (key == KeyEvent.VK_A && xSpeed < 0)
            xSpeed = 0;

        if (key == KeyEvent.VK_D && xSpeed > 0)
            xSpeed = 0;
    }

    /**
     * Mozgás függvény
     * 
     * Hozzáadja az x koordinátához az x sebességet, illetve az y koordinátához az y sebességet úgy, hogy
     * a játékpanelen belül marad az űrhajó.
     */
    public void move() {
        int h = Constants.PANELHEIGHT;
        int w = Constants.PANELWIDTH;
        int margin = Constants.PANELMARGIN;
        int xMin = margin;
        int xMax = w - spriteWidth - margin;
        int yMin = margin;
        int yMax = h - spriteHeight - margin;

        /* left boundary, moving left */
        if (xSpeed < 0) {
            if (x > xMin) {
                if (xSpeed < xMin - x) {
                    x -= x - xMin;
                } else {
                    x += xSpeed;
                }
            }
        }

        /* right boundary, moving right */
        if (xSpeed > 0) {
            if (x < xMax) {
                if (xMax - x < xSpeed) {
                    x += xMax - x;
                } else {
                    x += xSpeed;
                }
            }
        }

        /* top boundary, moving up */
        if (ySpeed < 0) {
            if (y > yMin) {
                if (ySpeed < yMin - y) {
                    y -= y - yMin;
                } else {
                    y += ySpeed;
                }
            }
        }

        /* bottom boundary, moving down */
        if (ySpeed > 0) {
            if (y < yMax) {
                if (yMax - y < ySpeed) {
                    y += yMax - y;
                } else {
                    y += ySpeed;
                }
            }
        }
    }

    /**
     * Lézert lövő függvény
     * 
     * @return A kilőtt lézer
     */
    public PlayerLaser shootLaser() {
        PlayerLaser laser = new PlayerLaser(this);
        return laser;
    }

    /**
     * Az sebzésre reagáló függvény
     * 
     * Megváltoztatja az űrhajó ikonját, ha sebződik: Addig piros, amíg nem sebzödhet: pl. ha
     * lézerrel eltalálják, akkor a Constants osztályban szereplő laserDamageTimeout által megadott
     * ideig lesz piros (közben nem kaphat újabb sebzést).
     */
    protected void reactToDamage() {
        if (health != 0) {
            if (timeUntilNextDamage == 0) {
                setImage(imagePath, spriteWidth, spriteHeight);
            } else {
                setImage(redImagePath, spriteWidth, spriteHeight);
            }
        }
    }

    /**
     * Ellenség űrhajókkal való ütközéseket kezelő függvény
     * 
     * Ha ütközik egy ellenséggel a játékos, akkor az utóbbi sebzést kap.
     * @param enemyShips Az ellenség űrhajók amelyekkel vizsgálja az ütközés feltételét.
     * 
     */
    public void handleCollisionWith(ArrayList<EnemyShip> enemyShips) {
        for (int i = 0; i < enemyShips.size(); i++) {
            EnemyShip enemyShip = enemyShips.get(i);
            if (this.overlapsWith(enemyShip)) {
                takeDamage(damage, Constants.shipDamageTimeout);
            }
        }
    }
}
