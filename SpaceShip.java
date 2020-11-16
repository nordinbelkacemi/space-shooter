import javax.swing.*;
import java.awt.event.*;

public class SpaceShip extends Sprite {
    private int health;
    private int spriteWidth = 37;
    private int spriteHeight = 35;
    private int dx, dy;

    public SpaceShip() {
        health = 100;
        setPos(300 - spriteWidth / 2, 500);
        dx = 0;
        dy = 0;
        setImage("spaceship.png");
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage() {
        health -= 20;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setMoveDirection(int key) {
        if (key == KeyEvent.VK_W)
            dy = -2;
        
        if (key == KeyEvent.VK_A)
            dx = -2;

        if (key == KeyEvent.VK_S)
            dy = 2;
        
        if (key == KeyEvent.VK_D)
            dx = 2;

    }

    public void stopMoveDirection(int key) {
        if (key == KeyEvent.VK_W && dy < 0)
            dy = 0;
        
        if (key == KeyEvent.VK_S && dy > 0)
            dy = 0;

        if (key == KeyEvent.VK_A && dx < 0)
            dx = 0;

        if (key == KeyEvent.VK_D && dx > 0)
            dx = 0;
    }

    public void move() {
        int h = GamePanel.HEIGHT;
        int w = GamePanel.WIDTH;
        int margin = GamePanel.MARGIN;

        if (x - margin > 0)
            x += dx;
        else
            x = margin + 1;

        if (x + spriteWidth + margin < w)
            x += dx;
        else
            x = w - (spriteWidth + margin);

        if (y - margin > 0)
            y += dy;
        else
            y = margin + 1;

        if (y + spriteHeight + margin < h)
            y += dy;
        else
            y = h - (spriteWidth + margin);
    }

}