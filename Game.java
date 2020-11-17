import java.awt.event.*;
// import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private SpaceShip spaceShip;
    private ArrayList<EnemyShip> enemies;
    private ArrayList<Laser> lasers;

    private int enemySpawnPeriod = Constants.enemySpawnPeriod / Constants.timerPeriod;
    private int timeUntilNextEnemy;

    public Game() {
        spaceShip = new SpaceShip();
        enemies = new ArrayList<EnemyShip>();
        lasers = new ArrayList<Laser>();
        setTimeUntilNextEnemy(Constants.firstEnemySpawnTime / Constants.timerPeriod);
    }

    public ArrayList<Sprite> getAllSprites() {
        ArrayList<Sprite> sprites = new ArrayList<Sprite>();

        sprites.add(spaceShip);
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip enemyShip = enemies.get(i);
            sprites.add(enemyShip);
        }
        for (int i = 0; i < lasers.size(); i++) {
            Laser laser = lasers.get(i);
            sprites.add(laser);
        }

        return sprites;
    }

    public SpaceShip getSpaceShip() {
        return spaceShip;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        handleKeyPress(key);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        handleKeyRelease(key);
    }

    private void handleKeyPress(int key) {
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D) {
            spaceShip.setMoveDirection(key);
        }

        if (key == KeyEvent.VK_UP) {
            shootLaser();
        }
    }

    private void handleKeyRelease(int key) {
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D)
            spaceShip.stopMoveDirection(key);
    }

    private void shootLaser() {
        Laser laser = new Laser(spaceShip.getX(), spaceShip.getY());
        lasers.add(laser);
    }

    public void setTimeUntilNextEnemy(int t) {
        timeUntilNextEnemy = t;
    }

    public void reduceTimeUntilNextEnemy() {
        timeUntilNextEnemy -= 1;
    }

    public void spawnEnemy() {
        EnemyShip enemyShip = new EnemyShip(20);
        enemies.add(enemyShip);
    }

    private void moveSprites() {
        spaceShip.move();

        for (int i = 0; i < lasers.size(); i++) {
            Sprite sprite = lasers.get(i);
            sprite.move();
        }

        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip enemyShip = enemies.get(i);
            enemyShip.move();
        }
    }

    private void spawnEnemies() {
        reduceTimeUntilNextEnemy();
        if (timeUntilNextEnemy == 0) {
            spawnEnemy();
            setTimeUntilNextEnemy(enemySpawnPeriod);
        }
    }

    private void removeOutOfRangeSprites() {
        lasers.removeIf(laser -> laser.isOutOfRange());
        enemies.removeIf(enemy -> enemy.isOutOfRange());
    }

    private void removeInactiveSprites() {
        lasers.removeIf(laser -> laser.isInactive());
        enemies.removeIf(enemy -> enemy.isInactive());
    }

    private void handleLaserCollisions() {
        for (int i = 0; i < lasers.size(); i++) {
            Laser laser = lasers.get(i);
            laser.handleCollisionWith(enemies);
        }
    }

    private void handleCollisions() {
        handleLaserCollisions();
    }

    private void handleExplosions() {
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip enemyShip = enemies.get(i);
            if (enemyShip.isExploding())
                enemyShip.stepExplosionAnimation();
        }
    }

    public void update() {
        moveSprites();
        spawnEnemies();
        handleCollisions();
        handleExplosions();
        removeInactiveSprites();
        removeOutOfRangeSprites();
    }
}