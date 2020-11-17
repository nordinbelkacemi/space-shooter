import java.util.Random;

public class EnemyShip extends Sprite {
    private int speed = Constants.enemySpeed;
    private int health;
    private int explodeCounter;
    private boolean exploding;

    public EnemyShip(int health) {
        setImage("enemy-ship.png", Constants.enemyShipIconWidth, Constants.enemyShipIconHeight);
        inactive = false;
        this.health = health;
        Random random = new Random();
        setPos(random.nextInt(Constants.PANELWIDTH - spriteWidth), 0 - spriteHeight);
    }

    public void move() {
        y += speed;
        if (y > Constants.PANELHEIGHT)
            outOfRange = true;
    }

    public void takeDamage() {
        health -= 20;
        if (health == 0)
            die();
    }

    public boolean isExploding() {
        return exploding;
    }

    public void stepExplosionAnimation() {
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
}