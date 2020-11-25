package models;

import game.Constants;

public abstract class Laser extends Sprite {
    protected int speed;
    protected SpaceShip spaceShip;

    public Laser(SpaceShip spaceShip) {
        setPos(spaceShip.getX() + Constants.playerShipIconWidth / 2, spaceShip.getY());
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
    }

    protected abstract void giveDamage(SpaceShip spaceShip);
}
