package models;
import game.Constants;

import java.util.Random;
import java.lang.Math;

public class EnemyShip extends SpaceShip {
    private int waitTime;
    private boolean waiting;
    private int minShootPeriod;

    Random random = new Random();

    public EnemyShip(int health, int minShootPeriod) {
        setImage(Constants.enemyShipIcon, Constants.enemyShipIconWidth, Constants.enemyShipIconHeight);
        imagePath = Constants.enemyShipIcon;
        
        setPos(random.nextInt(Constants.PANELWIDTH - spriteWidth), 0 - spriteHeight);
        ySpeed = Constants.enemySpeed;
        xSpeed = 0;
        waitTime = Math.abs(random.nextInt(minShootPeriod));
        this.health = health;
        this.minShootPeriod = minShootPeriod;
        inactive = false;
        waiting = true;
        timeUntilNextDamage = 0;
        damage = Constants.enemyDamage;
    }
    
    public void move() {
        y += ySpeed;
        if (y > Constants.PANELHEIGHT)
            outOfRange = true;
    }
    
    private void setRandomWaitTime() {
        waitTime = minShootPeriod;
        waitTime += Math.abs(random.nextInt(minShootPeriod / 5));
    }

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

    public EnemyLaser shootLaser() {
        EnemyLaser laser = new EnemyLaser(x, y, this);
        waiting = true;
        return laser;
    }

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
