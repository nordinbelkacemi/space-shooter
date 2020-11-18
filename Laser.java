import java.util.ArrayList;

public abstract class Laser extends Sprite {
    protected int speed;

    public Laser(int x, int y) {
        setPos(x + Constants.spaceShipIconWidth / 2, y - spriteHeight);
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