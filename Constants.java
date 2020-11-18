public class Constants {


    /* game panel dimensions */
    public static int PANELHEIGHT = 600;
    public static int PANELWIDTH = 600;
    public static int PANELMARGIN = 10;
    public static int HEALTHBARWIDTH = 100;


    /* game icons */
    public static String[] explosionIcons = {
        "images/explosions/explosion-1.png",
        "images/explosions/explosion-2.png",
        "images/explosions/explosion-3.png",
        "images/explosions/explosion-4.png",
        "images/explosions/explosion-5.png",
        "images/explosions/explosion-6.png",
        "images/explosions/explosion-7.png",
        "images/explosions/explosion-8.png",
        "images/explosions/explosion-9.png",
        "images/explosions/explosion-10.png",
    };
    public static int explosionIconWidth = 40;
    public static int explosionIconHeight = 40;

    public static String spaceShipIcon = "images/player-ship.png";
    public static String redSpaceShipIcon = "images/player-ship-red.png";
    public static int spaceShipIconWidth = 37;
    public static int spaceShipIconHeight = 35;

    public static String enemyShipIcon = "images/enemy-ship.png";
    public static String[] redEnemyShipIcons = {
        "images/enemy-ship-80hp.png",
        "images/enemy-ship-60hp.png",
        "images/enemy-ship-40hp.png",
        "images/enemy-ship-20hp.png",
    };
    public static int enemyShipIconWidth = 35;
    public static int enemyShipIconHeight = 38;

    public static String playerLaserIcon = "images/blue-laser.png";
    public static String enemyLaserIcon = "images/orange-laser.png";
    public static int laserIconWidth = 5;
    public static int laserIconHeight = 15;


    /* game constants */
    public static int timerPeriod = 17;                     // ~60fps
    public static int enemySpeed = 1;
    public static int playerSpeed = 2;
    public static int playerLaserSpeed = -20;
    public static int enemyLaserSpeed = 10;
    public static int firstEnemySpawnTime = 4000;
    public static int enemySpawnPeriod = 2000;              // level dependent
    public static int minEnemyShootPeriod = 100;            // level dependent
    public static int shipDamageTimeout = 600;
    public static int laserDamageTimeout = 200;
    
    public static int damageEffectTime = 500;               // ~ms at 60fps
}