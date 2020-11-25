package models;

import javax.swing.*;
import java.awt.Image;
import game.Constants;

public abstract class Sprite {
    protected ImageIcon img;
    protected String imagePath;
    protected int spriteWidth;
    protected int spriteHeight;

    protected int x;
    protected int y;

    protected boolean inactive = false;

    public void setImage(String imagePath, int width, int height) {
        this.img = new ImageIcon(getClass().getResource(imagePath));
        spriteWidth = width;
        spriteHeight = height;
    }
    
    public Image getImage() {
        return img.getImage();
    }
    
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

    public boolean isOutOfRange() {
        return y < 0 - spriteHeight || y > Constants.PANELHEIGHT;
    }

    public boolean isInactive() {
        return inactive;
    }

    protected abstract void move();
}
