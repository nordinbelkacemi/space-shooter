public abstract class Laser extends Sprite {
    protected int speed;
    protected SpaceShip spaceShip;

    public Laser(int x, int y, SpaceShip spaceShip) {
        setPos(x + Constants.spaceShipIconWidth / 2, y - spriteHeight);
        this.spaceShip = spaceShip;
    }

    public void handleCollisionWith(SpaceShip spaceShip) {
        if (this.overlapsWith(spaceShip)) {
            giveDamage(spaceShip);
            inactive = true;
        }
    }

    public void move() {
        y += speed;
        if (y < 0 - spriteHeight)
            outOfRange = true;
    }

    protected abstract void giveDamage(SpaceShip spaceShip);
}
