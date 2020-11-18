import java.util.Random;
import java.lang.Math;

public class EnemyShip extends SpaceShip {
    private int explodeCounter;
    private boolean exploding;
    private int waitTime;
    private boolean waiting;

    Random random = new Random();

    public EnemyShip(int health) {
        setImage(Constants.enemyShipIcon, Constants.enemyShipIconWidth, Constants.enemyShipIconHeight);
        imagePath = Constants.enemyShipIcon;
        
        setPos(random.nextInt(Constants.PANELWIDTH - spriteWidth), 0 - spriteHeight);
        ySpeed = Constants.enemySpeed;
        xSpeed = 0;
        inactive = false;
        this.health = health;
        waiting = true;
        waitTime = Math.abs(random.nextInt() % Constants.minEnemyShootPeriod);
        timeUntilNextDamage = 0;
    }
    
    public void move() {
        y += ySpeed;
        if (y > Constants.PANELHEIGHT)
            outOfRange = true;
    }
    
    public boolean isExploding() {
        return exploding;
    }
    
    public void stepExplosionState() {
        setImage(Constants.explosionIcons[10 - explodeCounter], Constants.explosionIconWidth, Constants.explosionIconHeight);
        explodeCounter -= 1;
        if (explodeCounter == 0) {
            exploding = false;
            inactive = true;
        }
    }
    
    public void die() {
        exploding = true;
        explodeCounter = 10;
    }
    
    private void setRandomWaitTime() {
        waitTime = Constants.minEnemyShootPeriod;
        waitTime += Math.abs(random.nextInt() % (Constants.minEnemyShootPeriod / 5));
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
        EnemyLaser laser = new EnemyLaser(x, y);
        waiting = true;
        return laser;
    }

    protected void changeImageAtDamage() {
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