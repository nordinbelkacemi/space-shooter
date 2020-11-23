package game;
import models.*;

import java.awt.event.*;
import java.util.ArrayList;

public class Game {
    private PlayerShip playerShip;
    private ArrayList<EnemyShip> enemyShips;
    private ArrayList<PlayerLaser> playerLasers;
    private ArrayList<EnemyLaser> enemyLasers;

    private int enemySpawnPeriod = Constants.enemySpawnPeriod / Constants.timerPeriod;
    private int timeUntilNextEnemy;
    private boolean paused;
    private int currentLevel;
    private int spawns;
    private int enemiesPerSpawn;
    private int enemiesGone;
    private boolean gameOver;
    private boolean startingNewLevel;
    private boolean playerWon;
    private boolean playerQuit;

    private GameData gameData;
    private int savedGameIndex;

    public Game() {
        init();
        loadGameData(new GameData(), -1);
        startLevel(1);
    }

    public Game(GameData gameData, int savedGameIndex) {
        init();
        loadGameData(gameData, savedGameIndex);
        startLevel(gameData.getLevel());
    }

    private void init() {
        playerShip = new PlayerShip();
        enemyShips = new ArrayList<EnemyShip>();
        playerLasers = new ArrayList<PlayerLaser>();
        enemyLasers = new ArrayList<EnemyLaser>();

        spawns = 0;
        enemiesGone = 0;
        gameOver = false;
    }
    
    public void loadGameData(GameData gameData, int savedGameIndex) {
        this.gameData = gameData;
        playerShip.setPoints(gameData.getScoreAtLevelStart());
        this.savedGameIndex = savedGameIndex;
    }
    
    private void startLevel(int level) {
        startingNewLevel = true;
        currentLevel = level;
        spawns = 0;
        enemiesGone = 0;
        enemiesPerSpawn = Constants.enemiesPerSpawn[level - 1];
        playerShip.setHealth(100);

        enemiesPerSpawn = Constants.enemiesPerSpawn[currentLevel - 1];
        timeUntilNextEnemy = Constants.firstEnemySpawnTime / Constants.timerPeriod;
        gameData.setScoreAtLevelStart(playerShip.getPoints());
    }

    public GameData getGameData() {
        return gameData;
    }

    public int getSavedGameIndex() {
        return savedGameIndex;
    }

    public int getPoints() {
        return playerShip.getPoints();
    }
    
    public int getCurrentLevel() {
        return currentLevel;
    }
    
    public boolean isNotPaused() {
        return !paused;
    }
    
    public boolean isOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    } 

    public boolean startingNewLevel() {
        return startingNewLevel;
    }

    public boolean playerWon() {
        return playerWon;
    }

    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
    }

    public boolean playerQuit() {
        return playerQuit;
    }

    public void saveGameData(boolean playerWon) {
        gameData.setLevel(currentLevel);
        gameData.setScore(playerShip.getPoints());
        gameData.setPlayerWon(playerWon);
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

    public void keyPressed(int key) {
        if (key == KeyEvent.VK_SPACE) {
            if (!paused) {
                paused = true;
            } else {
                paused = false;
            }
        }

        if (key == KeyEvent.VK_ESCAPE) {
            if (paused) {
                playerQuit = true;
                setGameOver(true);
                playerWon = false;
                setPlayerWon(playerWon);
                saveGameData(playerWon);
            }
        }

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D) {
            playerShip.setMoveDirection(key);
        }

        if (key == KeyEvent.VK_UP) {
            PlayerLaser laser = playerShip.shootLaser();
            playerLasers.add(laser);
        }
    }

    public void keyReleased(int key) {
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D) {
            playerShip.stopMoveDirection(key);
        }
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
        if (timeUntilNextEnemy == 0 && spawns < Constants.spawnsPerLevel) {
            startingNewLevel = false;
            int minShootPeriod = Constants.minEnemyShootPeriod[currentLevel - 1];
            for (int i = 0; i < enemiesPerSpawn; i++) {
                int health = Constants.enemyHealth[currentLevel - 1];
                EnemyShip enemyShip = new EnemyShip(health, minShootPeriod);
                enemyShips.add(enemyShip);
            }
            spawns += 1;
            timeUntilNextEnemy = enemySpawnPeriod;
        }
    }

    private void removeOutOfRangeSprites() {
        playerLasers.removeIf(laser -> laser.isOutOfRange());
        enemyLasers.removeIf(laser -> laser.isOutOfRange());

        int enemiesOutOfRange = (int) enemyShips.stream().filter(enemyShip -> enemyShip.isOutOfRange()).count();
        enemiesGone += enemiesOutOfRange;
        enemyShips.removeIf(enemy -> enemy.isOutOfRange());
        int points = playerShip.getPoints();
        playerShip.setPoints(points - Constants.enemyPassPenalty * enemiesOutOfRange);
    }

    private void removeInactiveSprites() {
        playerLasers.removeIf(laser -> laser.isInactive());
        enemyLasers.removeIf(laser -> laser.isInactive());

        int explodedEnemies = (int) enemyShips.stream().filter(enemyShip -> enemyShip.hasExploded()).count();
        enemiesGone += explodedEnemies;
        enemyShips.removeIf(enemy -> enemy.isInactive());

        int points = playerShip.getPoints();
        playerShip.setPoints(points + Constants.pointsPerKill * explodedEnemies);
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

        if (playerShip.isExploding())
            playerShip.stepExplosionState();
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

        if (enemiesGone == Constants.spawnsPerLevel * enemiesPerSpawn) {
            if (currentLevel < 10) {
                startLevel(currentLevel + 1);
            } else {
                gameOver = true;
                playerWon = true;
                saveGameData(playerWon);
            }
        }

        if (playerShip.hasExploded()) {
            gameOver = true;
            playerWon = false;
            saveGameData(playerWon);
        }
    }
}
