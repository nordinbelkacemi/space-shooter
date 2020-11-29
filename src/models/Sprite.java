package models;

import javax.swing.*;
import java.awt.Image;
import game.Constants;

public abstract class Sprite {
    /**
     * A sprite ikonja
     * 
     */
    protected ImageIcon img;

    /**
     * A sprite ikonjának az elérési útja.
     * 
     */
    protected String imagePath;

    /**
     * A sprite ikonjának a szélessége
     * 
     */
    protected int spriteWidth;

    /**
     * A sprite ikonjának a magassága
     * 
     */
    protected int spriteHeight;

    /**
     * A sprite ikonnak az x koordinátája
     * 
     */
    protected int x;

    /**
     * A sprite ikonnak az y koordinátája
     * 
     */
    protected int y;

    /**
     * Tárolja, hogy az adott sprite inaktív-e vagy nem. Egy lézer akkor inaktív, ha eltalált
     * egy ürhajót, egy ürhajó akkor inaktív, ha felrobbant
     * 
     */
    protected boolean inactive;

    /**
     * A sprite ikonját beállító függvény
     * 
     * @param imagePath az ikon elérési útja
     * @param width az ikon szélessége
     * @param height az ikon magassága
     */
    public void setImage(String imagePath, int width, int height) {
        this.img = new ImageIcon(getClass().getResource(imagePath));
        spriteWidth = width;
        spriteHeight = height;
        inactive = false;
    }
    
    /**
     * Egy adott sprite ikonját lekérdezö függvény
     * 
     * @return Az adott sprite ikonja
     */
    public Image getImage() {
        return img.getImage();
    }
    
    /**
     * A sprite pozízióját/koordinátáit beállító függvény
     * @param px A sprite x koordinátája
     * @param py A sprite y koordinátája
     */
    public void setPos(int px, int py) {
        x = px;
        y = py;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }
    
    /**
     * A sprite-ok ütközését/átfedését vizsgáló függvény.
     * 
     * Téglalapokra megvizsgálja, hogy átfedik-e egymást
     * @param otherSprite Az a sprite amelyikkel vizsgáljuk egy már adott sprite-al való átfedést
     * @return igazat ad vissza ha van átfedés, hamisat ha nem.
     * 
     */
    public boolean overlapsWith(Sprite otherSprite) {
        int x1 = x;
        int x2 = otherSprite.getX();
        int y1 = y;
        int y2 = otherSprite.getY();

        int w1 = spriteWidth;
        int w2 = otherSprite.getSpriteWidth();
        int h1 = spriteHeight;
        int h2 = otherSprite.getSpriteHeight();

        return (x1 < x2 + w2 && 
                x1 + w1 > x2 &&
                y1 < y2 + h2 &&
                y1 + h1 > y2);
    }

    /**
     * Az "out of range" tulajdonság teljesülését lekérdezö függvény.
     * 
     * Egy sprite akkor out of range, ha olyan y koordinátája van, hogy a sprite ikonja a
     * játékpanelen kívülre esik. Mivel a játékos csak a játékpanelen belül mozoghat, ez
     * soha nem lesz out of range. A többi spritenak az x sebessége pedig mindig nulla,
     * és csak a játékpanelen belülre esö x koordinátákkal inicializálódnak; ezért van az,
     * hogy csak az y koordinátát vizsgáljuk az out of range tulajdonság teljesülésének
     * lekérdezésekor.
     * @return igazat as vissza ha out of range, hamisat ha nem
     */
    public boolean isOutOfRange() {
        return y < 0 - spriteHeight || y > Constants.PANELHEIGHT;
    }

    public boolean isInactive() {
        return inactive;
    }

    protected abstract void move();
}
