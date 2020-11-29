package models;

import game.Constants;

public abstract class SpaceShip extends Sprite {
    /**
     * Az űrhajó eletpontjai
     * 
     */
    protected int health;

    /**
     * az űrhajó x irányú sebessége
     * 
     */
    protected int xSpeed;

    /**
     * Az űrhajó y irányú sebessége
     * 
     */
    protected int ySpeed;
    
    /**
     * A sebzési timeoutból maradt idő. Amíg ez nem nulla, nem szenvedhet sebzést az űrhajó. Ha
     * viszont nullára csökken akkor újra sebezhetővé válik.
     * 
     */
    protected int timeUntilNextDamage;

    /**
     * A robbanási állapot (robbanási képeknek megfelelö) sorszáma.
     * 
     */
    private int explodeCounter;

    /**
     * Tárolja, hogy az űrhajó éppen robbanó állapotban van-e.
     * 
     */
    private boolean exploding;

    /**
     * Tárolja, hogy az űrhajó hallot-e.
     * 
     */
    private boolean dead;

    /**
     * Tárolja, hogy az űrhajónak hány pontja van (Az ellenségek esetén ez mindig nulla).
     * 
     */
    protected int points;

    /**
     * Sebzéskor az életpontokból való levonás mértéke.
     * 
     */
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
        this.points = points;
    }

    public int getDamage() {
        return damage;
    }

    public int getTimeUntilNextDamage() {
        return timeUntilNextDamage;
    }

    /**
     * Sebzést implementáló függvény
     * 
     * Az ürhajónak az életpontjaiból a damage átlal megadott mennyiséget levonjuk
     * @param damage
     * @param damageTimeout
     */
    public void takeDamage(int damage, int damageTimeout) {
        if (this.timeUntilNextDamage == 0) {
            health -= damage;
            if (health == 0) {
                die();
            }

            int newPoints = points - Constants.hitPenalty;
            if (newPoints < 0) {
                this.setPoints(0);
            } else {
                this.setPoints(newPoints);
            }

            this.timeUntilNextDamage = damageTimeout / Constants.timerPeriod;
        }
        reactToDamage();
    }

    /**
     * Timeout léptetö függvény
     * 
     * ha a következö sebzésig maradó idö nagyobb, mint 0, akkor azt dekrementálja
     * 
     */
    public void stepDamageTimeout() {
        if (timeUntilNextDamage > 0) {
            timeUntilNextDamage -= 1;
            reactToDamage();
        }
    }

    /**
     * Meghalás függvény
     * 
     * átállítja a dead attribútumot igazra és felrobban az ürhajó
     * 
     */
    public void die() {
        dead = true;
        explode();
    }

    /**
     * Felrobbanás függvény
     * 
     * átállítja az exploding attribútumot igazra és a explodeCounter-t 1-re
     * 
     */
    public void explode() {
        exploding = true;
        explodeCounter = 1;
    }

    /**
     * robbanó állapotot lekérdezö függvény
     * 
     * @return igazat ad vissza, ha éppen robbanó állapotban van az ürhajó, egyébként hamisat.
     * 
     */
    public boolean isExploding() {
        return exploding;
    }
    
    /**
     * Robbanási állapotot léptetö függvény
     * 
     * Átállítja az ürhajó ikonját egy robbanó ikonra és lépteti a robbanási állapotot úgy,
     * hogy az explodeCounter értékét egyel inkrementálja. A robbanó ikon különbözö más
     * explodeCounter érték esetén: ha explodeCounter 1, akkor a Constants.explosionIcons
     * tömb 0 indexü elemre állítja az ikont, ha 2, akkor az 1-es indexu elemre, stb.
     * 
     */
    public void stepExplosionState() {
        setImage(Constants.explosionIcons[explodeCounter - 1], Constants.explosionIconWidth, Constants.explosionIconHeight);
        explodeCounter += 1;
        if (explodeCounter == 10) {
            exploding = false;
            inactive = true;
        }
    }

    /**
     * Felrobbant állapotot lekérdezö függvény
     * 
     * @return igazat ad vissza, ha felrobbant az ürhajó, hamisat ha nem.
     */
    public boolean hasExploded() {
        return inactive;
    }

    /**
     * Halott állapotot lekérdezö függvény
     * 
     * @return igazat ad vissza, ha halott az ürhajó, hamisat ha nem.
     */
    public boolean isDead() {
        return dead;
    }

    public abstract void move();
    protected abstract void reactToDamage();
}
