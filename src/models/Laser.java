package models;

import game.Constants;

public abstract class Laser extends Sprite {
    /**
     * A lézer sebessége
     * 
     */
    protected int speed;

    /**
     * A lézert kilövő űrhajó
     * 
     */
    protected SpaceShip spaceShip;

    /**
     * Lézer konstruktor
     * 
     * A lézer az őt kilövő űrhajó poziciójához illeszkedően jön létre.
     * @param spaceShip A lézert kilövő űrhajó
     * 
     */
    public Laser(SpaceShip spaceShip) {
        setPos(spaceShip.getX() + Constants.playerShipIconWidth / 2, spaceShip.getY());
        this.spaceShip = spaceShip;
    }

    /**
     * Ütközést kezelő függvény
     * 
     * Ha ütközik a lézer egy űrhajóval, akkor sebzést ad neki.
     * @param spaceShip A lézerrel ütköző űrhajó.
     * 
     */
    public void handleCollisionWith(SpaceShip spaceShip) {
        if (this.overlapsWith(spaceShip)) {
            giveDamage(spaceShip);
            inactive = true;
        }
    }

    /**
     * Mozgás függvény
     * 
     * Az y koordinátájához egyszerűen hozzáadja a sebességet.
     * 
     */
    public void move() {
        y += speed;
    }

    protected abstract void giveDamage(SpaceShip spaceShip);
}
