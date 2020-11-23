package models;

import game.Constants;

public abstract class SpaceShip extends Sprite {
    protected int health;
    protected int xSpeed, ySpeed;
    protected int timeUntilNextDamage;

    private int explodeCounter;
    private boolean exploding;
    private boolean dead;
    protected int points;
    protected int damage;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        if (points >= 0) {
            this.points = points;
        } else {
            this.points = 0;
        }
    }

    public int getDamage() {
        return damage;
    }

    public int getTimeUntilNextDamage() {
        return timeUntilNextDamage;
    }

    public void takeDamage(int damage, int damageTimeout) {
        if (this.timeUntilNextDamage == 0) {
            health -= damage;
            if (health == 0) {
                die();
            }
            this.setPoints(points - Constants.hitPenalty);
            this.timeUntilNextDamage = damageTimeout / Constants.timerPeriod;
        }
        reactToDamage();
    }

    public void stepDamageTimeout() {
        if (timeUntilNextDamage > 0) {
            timeUntilNextDamage -= 1;
            reactToDamage();
        }
    }

    public void die() {
        dead = true;
        explode();
    }

    public void explode() {
        exploding = true;
        explodeCounter = 10;
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

    public boolean hasExploded() {
        return inactive;
    }

    public boolean isDead() {
        return dead;
    }

    public abstract void move();
    protected abstract void reactToDamage();
}
