package game;
import models.*;

import java.awt.event.*;
import java.util.ArrayList;

public class Game {
    /**
     * A játék játékos űrhajója
     */
    private PlayerShip playerShip;

    /**
     * A játékpanelen lévő ellenség űrhajók.
     */
    private ArrayList<EnemyShip> enemyShips;

    /**
     * A játékpanelen lévő játékos lézerek.
     */
    private ArrayList<PlayerLaser> playerLasers;

    /**
     * A játékpanelen lévő ellenség lézerek.
     */
    private ArrayList<EnemyLaser> enemyLasers;

    /**
     * Ellenség spawn-ok között eltelt idő, időzítő periódusban kifejezve. Ha ez 2, akkor 2 időzítő
     * periódus telik el két spawn között.
     */
    private int enemySpawnPeriod = Constants.enemySpawnPeriod / Constants.timerPeriod;
    
    /**
     * Ellenség spawn-ok között eltelt idő, időzítő periódusban kifejezve. Ha ez 2, akkor 2 időzítő
     * periódus telik el két spawn között.
     */
    private int timeUntilNextEnemy;
    
    /**
     * Tárolja, hogy játék szünetelve van-e.
     */
    private boolean paused;

    /**
     * Tárolja, hogy kilépett e a játékos a játékból.
     */
    private boolean playerQuit;
    
    /**
     * A játékállásban a szint száma.
     */
    private int currentLevel;

    /**
     * Az eddigi spawnok száma. Lenullázódik minden szint elején.
     */
    private int spawns;

    /**
     * Hány ellenségnek kell megjelennie egy spawnkor. A játék minden szint elején átállítja
     * ezt az értéket
     */
    private int enemiesPerSpawn;

    /**
     * Azon ellenségek száma amelyeket vagy megesmmisített, vagy elengedett a játékos.
     */
    private int enemiesGone;

    /**
     * Tárolja, hogy a játék véget ért-e.
     */
    private boolean gameOver;

    /**
     * Tárolja, hogy a éppen új szint kezdödik-e. Ilyenkor a játékpanelen egy adott időre megjelenik
     * az új szint felirata (pl "LEVEL 1")
     */
    private boolean startingNewLevel;

    /**
     * Tárolja, hogy nyert-e a játékos
     */
    private boolean playerWon;

    /**
     * A fájlba írandó játékadatokat tárolja.
     */
    private GameData gameData;

    /**
     * A mentett játékadatokat tároló tömb indexe, ahol az index azonosítja a betöltött játékot.
     * Ha az index -1, akkor új játékról van szó.
     */
    private int savedGameIndex;

    /** Default konstruktor
     * 
     * Új játékot kezd és annak megfelelően inicializálja az adatokat
     * 
     */
    public Game() {
        init();
        loadGameData(new GameData(), -1);
        startLevel(1);
    }

    /** Konstruktor
     * 
     * Egy betöltöt játékkal inicializálja az adatokat, és folytatja a játékmenetet az utolsó
     * elért szinttöl
     * @param gameData A folytatandó játék adatai
     * @param savedGameIndex A mentett játék indexe a mentett játékok tömbben.
     * 
     */
    public Game(GameData gameData, int savedGameIndex) {
        init();
        loadGameData(gameData, savedGameIndex);
        startLevel(gameData.getLevel());
    }

    /** Játék inicializáló függvény
     * 
     * Beállítja a játék alapadatait.
     * 
     */
    private void init() {
        playerShip = new PlayerShip();
        enemyShips = new ArrayList<EnemyShip>();
        playerLasers = new ArrayList<PlayerLaser>();
        enemyLasers = new ArrayList<EnemyLaser>();

        spawns = 0;
        enemiesGone = 0;
        gameOver = false;
    }
    
    /** Játékadatot betöltő függvény
     * 
     * Betölti az adott játék adatokat.
     * @param gameData A betöltendő játék adatai
     * @param savedGameIndex A mentett játék indexe a mentett játékok tömbben.
     * 
     */
    public void loadGameData(GameData gameData, int savedGameIndex) {
        this.gameData = gameData;
        playerShip.setPoints(gameData.getScoreAtLevelStart());
        this.savedGameIndex = savedGameIndex;
    }
    
    /** Szintet kezdő függvény
     * 
     * Elkezdi a játékot egy szinten.
     * @param level játék szint
     * 
     */
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

    public PlayerShip getPlayerShip() {
        return playerShip;
    }

    public ArrayList<EnemyShip> getEnemyShips() {
        return enemyShips;
    }

    public ArrayList<PlayerLaser> getPlayerLasers() {
        return playerLasers;
    }

    public ArrayList<EnemyLaser> getEnemyLasers() {
        return enemyLasers;
    }

    /** 
     * Betölti az adott játék adatokat.
     * @param gameData A betöltendő játék adatai
     * @param savedGameIndex A mentett játék indexe a mentett játékok tömbben.
     * 
     */
    public void saveGameData(boolean playerWon) {
        gameData.setLevel(currentLevel);
        gameData.setScore(playerShip.getPoints());
        gameData.setPlayerWon(playerWon);
    }

    /** 
     * Összegyüjti a játék panelen lévő összes sprite-ot.
     * @return a játék panelen lévő összes sprite
     */
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

    /**
     * Kezeli a billentyu lenyomasokat
     * @param key A lenyomott billentyühez tartozó int típusú érték.
     * 
     */
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

    /**
     * Kezeli a billentyu elengedéseket
     * @param key Az elengedett billentyühez tartozó int típusú érték.
     * 
     */
    public void keyReleased(int key) {
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D) {
            playerShip.stopMoveDirection(key);
        }
    }

    /**
     * Mozgatja a játékpanelen lévő sprite-okat
     * 
     */
    public void moveSprites() {
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

    /**
     * Új ellenség űrhajókat hoz létre. A létrehozott űrhajók véletlenszerű x koordinátát kapnak.
     * 
     */
    public void spawnEnemyShips() {
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

    /**
     * Törli az osztály példányának a playerLasers, enemyLasers és enemyShips tömbjeiböl azokat
     * az elemeket, amelyeknek a koordinátái a játékpanelen kívülre esnek.
     * 
     */
    public void removeOutOfRangeSprites() {
        playerLasers.removeIf(laser -> laser.isOutOfRange());
        enemyLasers.removeIf(laser -> laser.isOutOfRange());
        
        int enemiesOutOfRange = (int) enemyShips.stream().filter(enemyShip -> enemyShip.isOutOfRange()).count();
        enemiesGone += enemiesOutOfRange;
        enemyShips.removeIf(enemy -> enemy.isOutOfRange());
        int points = playerShip.getPoints();
        playerShip.setPoints(points - Constants.enemyPassPenalty * enemiesOutOfRange);
    }
    
    
    /**
     * Törli a playerLasers, enemyLasers és enemyShips tömbökből azokat az elemeket, amelyek
     * inaktívak.
     * 
     */
    public void removeInactiveSprites() {
        playerLasers.removeIf(laser -> laser.isInactive());
        enemyLasers.removeIf(laser -> laser.isInactive());
        
        int explodedEnemies = (int) enemyShips.stream().filter(enemyShip -> enemyShip.hasExploded()).count();
        enemiesGone += explodedEnemies;
        enemyShips.removeIf(enemy -> enemy.isInactive());
        
        int points = playerShip.getPoints();
        playerShip.setPoints(points + Constants.pointsPerKill * explodedEnemies);
    }
    
    
    /**
     * Kezeli a játékos lézerek ütközését ellenségekkel.
     * 
     */
    public void handlePlayerLaserCollisions() {
        for (int i = 0; i < playerLasers.size(); i++) {
            PlayerLaser laser = playerLasers.get(i);
            laser.handleCollisionWith(enemyShips);
        }
    }
    
    /**
     * Kezeli az ellenség lézerek ütközését a játékossal.
     * 
     */
    public void handleEnemyLaserCollisions() {
        for (int i = 0; i < enemyLasers.size(); i++) {
            EnemyLaser laser = enemyLasers.get(i);
            laser.handleCollisionWith(playerShip);
        }
    }
    
    /**
     * Kezeli az ellenség lézerek ütközését a játékossal.
     * 
     */
    public void handleSpaceShipCollisions() {
        playerShip.handleCollisionWith(enemyShips);
    }

    /**
     * Kezeli az ütközéseket a játékban.
     * 
     */
    public void handleCollisions() {
        handlePlayerLaserCollisions();
        handleEnemyLaserCollisions();
        handleSpaceShipCollisions();
    }

    /**
     * Kezeli az űrhajók felrobbanását
     *  
     */
    public void handleExplosions() {
        for (int i = 0; i < enemyShips.size(); i++) {
            EnemyShip enemyShip = enemyShips.get(i);
            if (enemyShip.isExploding())
            enemyShip.stepExplosionState();
        }
        
        if (playerShip.isExploding())
        playerShip.stepExplosionState();
    }
    
    /**
     * Lépteti a játékos damage timeout-ját.
     *  
     */
    public void handleDamageTimeout() {
        playerShip.stepDamageTimeout();
    }

    /**
     * Lövésre kényszeríti azokat az ellenség űrhajókat amik nem várnak a következö lövésre.
     *  
     */
    public void makeEnemyShipsShoot() {
        for (int i = 0; i < enemyShips.size(); i++) {
            EnemyShip enemyShip = enemyShips.get(i);
            enemyShip.stepWaitTime();
            if (enemyShip.isNotWaiting() && !enemyShip.isExploding()) {
                EnemyLaser laser = enemyShip.shootLaser();
                enemyLasers.add(laser);
            }
        }
    }

    /**
     * Lépteti a játék adatait
     * 
     */
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
