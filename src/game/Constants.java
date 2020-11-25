package game;

import java.awt.event.*;

public class Constants {


    /* game panel */
    public static int PANELHEIGHT = 600;
    public static int PANELWIDTH = 600;
    public static int PANELMARGIN = 10;
    public static int HEALTHBARWIDTH = 100;
    public static int[] controlKeys = {
        KeyEvent.VK_W,                      // UP
        KeyEvent.VK_A,                      // LEFT
        KeyEvent.VK_S,                      // DOWN    
        KeyEvent.VK_D,                      // RIGHT
        KeyEvent.VK_SPACE,                  // PAUSE
        KeyEvent.VK_UP,                     // SHOOT
        KeyEvent.VK_ESCAPE                  // QUIT
    };


    /* images */
    public static String[] explosionIcons = {
        "/images/explosion-1.png",
        "/images/explosion-2.png",
        "/images/explosion-3.png",
        "/images/explosion-4.png",
        "/images/explosion-5.png",
        "/images/explosion-6.png",
        "/images/explosion-7.png",
        "/images/explosion-8.png",
        "/images/explosion-9.png",
        "/images/explosion-10.png",
    };
    public static int explosionIconWidth = 40;
    public static int explosionIconHeight = 40;

    public static String playerShipIcon = "/images/player-ship.png";
    public static String redSpaceShipIcon = "/images/player-ship-red.png";
    public static int playerShipIconWidth = 37;
    public static int playerShipIconHeight = 35;

    public static String enemyShipIcon = "/images/enemy-ship.png";
    public static String[] redEnemyShipIcons = {
        "/images/enemy-ship-80hp.png",
        "/images/enemy-ship-60hp.png",
        "/images/enemy-ship-40hp.png",
        "/images/enemy-ship-20hp.png",
    };
    public static int enemyShipIconWidth = 35;
    public static int enemyShipIconHeight = 38;

    public static String playerLaserIcon = "/images/blue-laser.png";
    public static String enemyLaserIcon = "/images/orange-laser.png";
    public static int laserIconWidth = 5;
    public static int laserIconHeight = 15;

    public static String pauseScreen = "/images/pause-screen.png";
    public static String gameOverScreen = "/images/game-over-screen.png";
    public static String menuBackground = "/images/menu-background.png";
    public static String[] levels = {
        "/images/level-1.png",
        "/images/level-2.png",
        "/images/level-3.png",
        "/images/level-4.png",
        "/images/level-5.png",
        "/images/level-6.png",
        "/images/level-7.png",
        "/images/level-8.png",
        "/images/level-9.png",
        "/images/level-10.png"
    };
    public static int levelLabelWidth = 225;
    public static int levelLabelHeight = 50;
    public static String pointsFont = "/fonts/00TT.TTF";


    /* game constants */
    public static int timerPeriod = 17;                     // ~60fps
    public static int enemySpeed = 1;
    public static int playerSpeed = 5;
    public static int playerLaserSpeed = -20;
    public static int enemyLaserSpeed = 10;
    public static int firstEnemySpawnTime = 5000;
    public static int enemySpawnPeriod = 2000;
    public static int[] minEnemyShootPeriod = {150, 125, 100, 150, 125, 100, 150, 125, 100, 150};
    public static int[] enemiesPerSpawn = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4};
    public static int[] enemyHealth = {60, 80, 100, 60, 80, 100, 60, 80, 100, 60};
    public static int shipDamageTimeout = 600;
    public static int laserDamageTimeout = 200;
    public static int spawnsPerLevel = 5;
    public static int pointsPerKill = 20;
    public static int pointsPerDamage = 5;
    public static int enemyPassPenalty = 19;
    public static int hitPenalty = 3;
    public static int playerDamage = 20;
    public static int enemyDamage = 20;
    
    public static int damageEffectTime = 500;               // ~ms at 60fps

    public static String saveGamePath = "saved-games.dat";

}
