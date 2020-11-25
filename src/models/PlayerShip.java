package models;

import game.Constants;
import java.awt.event.*;
import java.util.ArrayList;

public class PlayerShip extends SpaceShip{
    private String redImagePath;

    public PlayerShip() {
        setImage(Constants.playerShipIcon, Constants.playerShipIconWidth, Constants.playerShipIconHeight);
        imagePath = Constants.playerShipIcon;
        redImagePath = Constants.redSpaceShipIcon;

        health = 100;
        setPos(Constants.PANELWIDTH / 2 - spriteWidth / 2, Constants.PANELHEIGHT - 100);
        xSpeed = 0;
        ySpeed = 0;
        timeUntilNextDamage = 0;
        damage = Constants.playerDamage;
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
        int xMin = margin;
        int xMax = w - spriteWidth - margin;
        int yMin = margin;
        int yMax = h - spriteHeight - margin;

        /* left boundary, moving left */
        if (xSpeed < 0) {
            if (x > xMin) {
                if (xSpeed < xMin - x) {
                    x -= x - xMin;
                } else {
                    x += xSpeed;
                }
            }
        }

        /* right boundary, moving right */
        if (xSpeed > 0) {
            if (x < xMax) {
                if (xMax - x < xSpeed) {
                    x += xMax - x;
                } else {
                    x += xSpeed;
                }
            }
        }

        /* top boundary, moving up */
        if (ySpeed < 0) {
            if (y > yMin) {
                if (ySpeed < yMin - y) {
                    y -= y - yMin;
                } else {
                    y += ySpeed;
                }
            }
        }

        /* bottom boundary, moving down */
        if (ySpeed > 0) {
            if (y < yMax) {
                if (yMax - y < ySpeed) {
                    y += yMax - y;
                } else {
                    y += ySpeed;
                }
            }
        }
    }

    public PlayerLaser shootLaser() {
        PlayerLaser laser = new PlayerLaser(this);
        return laser;
    }

    protected void reactToDamage() {
        if (health != 0) {
            if (timeUntilNextDamage == 0) {
                setImage(imagePath, spriteWidth, spriteHeight);
            } else {
                setImage(redImagePath, spriteWidth, spriteHeight);
            }
        }
    }

    public void handleCollisionWith(ArrayList<EnemyShip> enemyShips) {
        for (int i = 0; i < enemyShips.size(); i++) {
            EnemyShip enemyShip = enemyShips.get(i);
            if (this.overlapsWith(enemyShip)) {
                takeDamage(damage, Constants.shipDamageTimeout);
            }
        }
    }
}
