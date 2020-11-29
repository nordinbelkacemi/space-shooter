package models;
import game.Constants;

import java.util.Random;
import java.lang.Math;

public class EnemyShip extends SpaceShip {
    /**
     * Két egymás utáni lövés közti várakozási idő
     * 
     */
    private int waitTime;

    /**
     * Tárolja, hogy vár-e a következő lövésre az adott ellenség űrhajó
     * 
     */
    private boolean waiting;

    /**
     * Két egymás utáni lövés közti minimális várakozási idő
     * 
     */
    private int minShootPeriod;

    Random random;

    /**
     * Egy ellenség űrhajót inicializáló konstruktor. Egy véletlenszerű x koordinátát kap.
     * @param health Kezdeti életpontok
     * @param minShootPeriod Két egymás utáni lövés közti minimális várakozási idő
     * 
     */
    public EnemyShip(int health, int minShootPeriod) {
        random = new Random();
        setImage(Constants.enemyShipIcon, Constants.enemyShipIconWidth, Constants.enemyShipIconHeight);
        imagePath = Constants.enemyShipIcon;
        ySpeed = Constants.enemySpeed;
        xSpeed = 0;
        waitTime = Math.abs(random.nextInt(minShootPeriod));
        this.health = health;
        this.minShootPeriod = minShootPeriod;
        inactive = false;
        waiting = true;
        timeUntilNextDamage = 0;
        damage = Constants.enemyDamage;

        setPos(random.nextInt(Constants.PANELWIDTH - spriteWidth), 0 - spriteHeight);
    }
    
    /**
     * Mozgás függvény.
     * 
     * Az y koordinátájához egyszerűen hozzáadja a sebességet.
     * 
     */
    public void move() {
        y += ySpeed;
    }
    
    /**
     * A következő lövésre való várakozási idő beállítása.
     * 
     * A várakozási időt beállítja a minimálisra és azután hozzáadja a minimális várakozási idő egy
     * ötödét.
     * 
     */
    private void setRandomWaitTime() {
        waitTime = minShootPeriod;
        waitTime += Math.abs(random.nextInt(minShootPeriod / 5));
    }

    /**
     * Várakozási időt léptető függvény
     * 
     * Dekrementálja a várakozási időt, ha pedig nullátra lecsökken, beállitja egy új értékre a
     * várakozási időt.
     * 
     */
    public void stepWaitTime() {
        waitTime -= 1;
        if (waitTime == 0) {
            waiting = false;
            setRandomWaitTime();
        }
    }

    public boolean isNotWaiting() {
        return !waiting;
    }

    /**
     * Ellenség lézert lövő függvény
     * @return A kilőtt ellenség lézer.
     * 
     */
    public EnemyLaser shootLaser() {
        EnemyLaser laser = new EnemyLaser(this);
        waiting = true;
        return laser;
    }

    /**
     * Az ellenség űrhajó ikon megváltoztatása az űrhajó életpontaitól függően.
     * 
     */
    protected void reactToDamage() {
        int width = Constants.enemyShipIconWidth;
        int height = Constants.enemyShipIconHeight;
        String[] images = Constants.redEnemyShipIcons;

        switch (health) {
            case 80:
                setImage(images[0], width, height);
                break;
            case 60:
                setImage(images[1], width, height);
                break;
            case 40:
                setImage(images[2], width, height);
                break;
            case 20:
                setImage(images[3], width, height);
                break;
        }
    }
}
