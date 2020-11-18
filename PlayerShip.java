import java.awt.event.*;

public class PlayerShip extends SpaceShip{
    private boolean dead;
    private boolean justTookDamage;
    protected int timeTurnedRed;
    private String redImagePath;

    public PlayerShip() {
        setImage(Constants.spaceShipIcon, Constants.spaceShipIconWidth, Constants.spaceShipIconHeight);
        imagePath = Constants.spaceShipIcon;
        redImagePath = Constants.redSpaceShipIcon;

        health = 100;
        setPos(Constants.PANELWIDTH / 2 - spriteWidth / 2, Constants.PANELHEIGHT - 100);
        xSpeed = 0;
        ySpeed = 0;
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

    public PlayerLaser shootLaser() {
        PlayerLaser laser = new PlayerLaser(x, y);
        return laser;
    }

    public void die() {
        dead = true;
    };

    public boolean isDead() {
        return dead;
    }

    public void changeImageAtDamage() {
        justTookDamage = true;
        setImage(redImagePath, spriteWidth, spriteHeight);
        timeTurnedRed = Constants.damageEffectTime / Constants.timerPeriod;
    }

    public boolean justTookDamage() {
        return justTookDamage;
    }

    public void stepTimeTurnedRed() {
        timeTurnedRed -= 1;
        if (timeTurnedRed == 0) {
            setImage(imagePath, spriteWidth, spriteHeight);
            justTookDamage = false;
        }
    }
}