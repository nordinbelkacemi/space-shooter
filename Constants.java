public class Constants {


    /* game panel dimensions */
    public static int PANELHEIGHT = 600;
    public static int PANELWIDTH = 600;
    public static int PANELMARGIN = 10;
    public static int HEALTHBARWIDTH = 100;


    /* game icons */
    public static String[] explosionIcons = {
        "explosions/explosion-1.png",
        "explosions/explosion-2.png",
        "explosions/explosion-3.png",
        "explosions/explosion-4.png",
        "explosions/explosion-5.png",
        "explosions/explosion-6.png",
        "explosions/explosion-7.png",
        "explosions/explosion-8.png",
        "explosions/explosion-9.png",
        "explosions/explosion-10.png",
    };
    public static int explosionIconWidth = 40;
    public static int explosionIconHeight = 40;

    public static String spaceShipIcon = "spaceship.png";
    public static int spaceShipIconWidth = 37;
    public static int spaceShipIconHeight = 35;

    public static String enemyShipIcon = "enemy-ship.png";
    public static int enemyShipIconWidth = 35;
    public static int enemyShipIconHeight = 38;

    public static String laserIcon = "laser.png";
    public static int laserIconWidth = 5;
    public static int laserIconHeight = 15;


    /* game constants */
    public static int timerPeriod = 17;             // ~60fps
    public static int enemySpeed = 1;
    public static int playerSpeed = 2;
    public static int laserSpeed = 20;
    public static int enemySpawnPeriod = 2000;      // ~ms if timerPeriod = 17
    public static int firstEnemySpawnTime = 4000;
}