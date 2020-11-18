public abstract class SpaceShip extends Sprite {
    protected int health;
    protected int xSpeed, ySpeed;
    protected int timeUntilNextDamage;

    public int getHealth() {
        return health;
    }

    public int getTimeUntilNextDamage() {
        return timeUntilNextDamage;
    }

    public void takeDamage(int damageTimeout) {
        if (this.timeUntilNextDamage == 0) {
            health -= 20;
            if (health == 0) {
                die();
            }
            this.timeUntilNextDamage = damageTimeout / Constants.timerPeriod;
        }
        changeImageAtDamage();
    }

    public void stepDamageTimeout() {
        if (timeUntilNextDamage > 0) {
            timeUntilNextDamage -= 1;
            changeImageAtDamage();
        }
    }

    public abstract void move();
    public abstract void die();
    protected abstract void changeImageAtDamage();
}