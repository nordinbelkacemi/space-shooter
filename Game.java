import java.awt.event.*;
// import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private PlayerShip playerShip;
    private ArrayList<EnemyShip> enemyShips;
    private ArrayList<PlayerLaser> playerLasers;
    private ArrayList<EnemyLaser> enemyLasers;

    private int enemySpawnPeriod = Constants.enemySpawnPeriod / Constants.timerPeriod;
    private int timeUntilNextEnemy;
    private boolean paused;

    public Game() {
        playerShip = new PlayerShip();
        enemyShips = new ArrayList<EnemyShip>();
        playerLasers = new ArrayList<PlayerLaser>();
        enemyLasers = new ArrayList<EnemyLaser>();
        timeUntilNextEnemy = Constants.firstEnemySpawnTime / Constants.timerPeriod;
    }

    public boolean isPaused() {
        return paused;
    }

    public ArrayList<Sprite> getAllSprites() {
        ArrayList<Sprite> sprites = new ArrayList<Sprite>();

        sprites.add(playerShip);

        for (int i = 0; i < enemyShips.size(); i++) {
            EnemyShip enemyShip = enemyShips.get(i);
            sprites.add(enemyShip);
        }
        
        for (int i = 0; i < playerLasers.size(); i++) {
            Laser laser = playerLasers.get(i);
            sprites.add(laser);
        }

        for (int i = 0; i < enemyLasers.size(); i++) {
            Laser laser = enemyLasers.get(i);
            sprites.add(laser);
        }

        return sprites;
    }

    public PlayerShip getPlayerShip() {
        return playerShip;
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
            playerShip.setMoveDirection(key);
        }

        if (key == KeyEvent.VK_UP) {
            PlayerLaser laser = playerShip.shootLaser();
            playerLasers.add(laser);
        }
    }

    private void handleKeyRelease(int key) {
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D)
            playerShip.stopMoveDirection(key);
    }

    public void spawnEnemy() {
        EnemyShip enemyShip = new EnemyShip(100);
        enemyShips.add(enemyShip);
    }

    private void moveSprites() {
        playerShip.move();
        
        for (int i = 0; i < enemyShips.size(); i++) {
            EnemyShip enemyShip = enemyShips.get(i);
            enemyShip.move();
        }

        for (int i = 0; i < enemyLasers.size(); i++) {
            EnemyLaser laser = enemyLasers.get(i);
            laser.move();
        }

        for (int i = 0; i < playerLasers.size(); i++) {
            PlayerLaser laser = playerLasers.get(i);
            laser.move();
        }
    }

    private void spawnEnemyShips() {
        timeUntilNextEnemy -= 1;
        if (timeUntilNextEnemy == 0) {
            spawnEnemy();
            timeUntilNextEnemy = enemySpawnPeriod;
        }
    }

    private void removeOutOfRangeSprites() {
        playerLasers.removeIf(laser -> laser.isOutOfRange());
        enemyLasers.removeIf(laser -> laser.isOutOfRange());
        enemyShips.removeIf(enemy -> enemy.isOutOfRange());
    }

    private void removeInactiveSprites() {
        playerLasers.removeIf(laser -> laser.isInactive());
        enemyLasers.removeIf(laser -> laser.isInactive());
        enemyShips.removeIf(enemy -> enemy.isInactive());
    }

    private void handlePlayerLaserCollisions() {
        for (int i = 0; i < playerLasers.size(); i++) {
            PlayerLaser laser = playerLasers.get(i);
            laser.handleCollisionWith(enemyShips);
        }
    }

    private void handleEnemyLaserCollisions() {
        for (int i = 0; i < enemyLasers.size(); i++) {
            EnemyLaser laser = enemyLasers.get(i);
            laser.handleCollisionWith(playerShip);
        }
    }

    private void handleSpaceShipCollisions() {
        playerShip.handleCollisionWith(enemyShips);
    }

    private void handleCollisions() {
        handlePlayerLaserCollisions();
        handleEnemyLaserCollisions();
        handleSpaceShipCollisions();
    }

    private void handleExplosions() {
        for (int i = 0; i < enemyShips.size(); i++) {
            EnemyShip enemyShip = enemyShips.get(i);
            if (enemyShip.isExploding())
                enemyShip.stepExplosionState();
        }
    }

    private void handleDamageTimeout() {
        playerShip.stepDamageTimeout();
    }

    private void makeEnemyShipsShoot() {
        for (int i = 0; i < enemyShips.size(); i++) {
            EnemyShip enemyShip = enemyShips.get(i);
            enemyShip.stepWaitTime();
            if (enemyShip.isNotWaiting() && !enemyShip.isExploding()) {
                EnemyLaser laser = enemyShip.shootLaser();
                enemyLasers.add(laser);
            }
        }
    }

    public void update() {
        moveSprites();
        spawnEnemyShips();
        makeEnemyShipsShoot();
        handleCollisions();
        handleExplosions();
        handleDamageTimeout();
        removeInactiveSprites();
        removeOutOfRangeSprites();
    }
}