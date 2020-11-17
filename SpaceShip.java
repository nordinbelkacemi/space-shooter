import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SpaceShip extends Sprite {
    private int health;
    private int xSpeed, ySpeed;

    public SpaceShip() {
        setImage("spaceship.png", Constants.spaceShipIconWidth, Constants.spaceShipIconHeight);

        health = 100;
        setPos(300 - spriteWidth / 2, 500);
        xSpeed = 0;
        ySpeed = 0;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage() {
        health -= 20;
    }

    public void setMoveDirection(int key) {
        if (key == KeyEvent.VK_W)
            ySpeed = -Constants.playerSpeed;
        
        if (key == KeyEvent.VK_A)
            xSpeed = -Constants.playerSpeed;

        if (key == KeyEvent.VK_S)
            ySpeed = Constants.playerSpeed;
        
        if (key == KeyEvent.VK_D)
            xSpeed = Constants.playerSpeed;

    }

    public void stopMoveDirection(int key) {
        if (key == KeyEvent.VK_W && ySpeed < 0)
            ySpeed = 0;
        
        if (key == KeyEvent.VK_S && ySpeed > 0)
            ySpeed = 0;

        if (key == KeyEvent.VK_A && xSpeed < 0)
            xSpeed = 0;

        if (key == KeyEvent.VK_D && xSpeed > 0)
            xSpeed = 0;
    }

    public void move() {
        int h = Constants.PANELHEIGHT;
        int w = Constants.PANELWIDTH;
        int margin = Constants.PANELMARGIN;

        if (x - margin > 0)
            x += xSpeed;
        else
            x = margin + 1;

        if (x + spriteWidth + margin < w)
            x += xSpeed;
        else
            x = w - (spriteWidth + margin);

        if (y - margin > 0)
            y += ySpeed;
        else
            y = margin + 1;

        if (y + spriteHeight + margin < h)
            y += ySpeed;
        else
            y = h - (spriteWidth + margin);
    }
}