public abstract class SpaceShip extends Sprite {
    protected int health;
    protected int xSpeed, ySpeed;

    public int getHealth() {
        return health;
    }

    public void takeDamage() {
        health -= 20;
        if (health == 0)
            die();

        changeImageAtDamage();
    }

    public abstract void move();
    public abstract void die();
    public abstract void changeImageAtDamage();
}