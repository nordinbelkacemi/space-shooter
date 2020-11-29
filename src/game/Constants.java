package game;

import java.awt.event.*;

public class Constants {
    /**
     * A panel magassága
     */ 
    public static int PANELHEIGHT = 600;

    /**
     * A panel szélessége
     */ 
    public static int PANELWIDTH = 600;

    /**
     * A margó: ezen belül marad a játékos űrhajója
     */ 
    public static int PANELMARGIN = 10;

    /**
     * A panel magassága
     */ 
    public static int HEALTHBARWIDTH = 100;

    /**
     * Az űrhajó irányítására használt billentyük.
     */ 
    public static int[] controlKeys = {
        KeyEvent.VK_W,                      // UP
        KeyEvent.VK_A,                      // LEFT
        KeyEvent.VK_S,                      // DOWN    
        KeyEvent.VK_D,                      // RIGHT
        KeyEvent.VK_SPACE,                  // PAUSE
        KeyEvent.VK_UP,                     // SHOOT
        KeyEvent.VK_ESCAPE                  // QUIT
    };

    /**
     * Egy űrhajó felrobbanásakor megjelenö képeknek az elérési útja
     */ 
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
    /**
     * Egy űrhajó felrobbanásakor megjelenö képeknek a szélessége
     */ 
    public static int explosionIconWidth = 40;

    /**
     * Egy űrhajó felrobbanásakor megjelenö képeknek a magassága
     */ 
    public static int explosionIconHeight = 40;

    /**
     * A játékos űrhajó képének elérési útja.
     */ 
    public static String playerShipIcon = "/images/player-ship.png";

    /**
     * A játékos űrhajó piros képének elérési útja.
     */ 
    public static String redSpaceShipIcon = "/images/player-ship-red.png";

    /**
     * A játékos űrhajó képének a szélessége.
     */ 
    public static int playerShipIconWidth = 37;

    /**
     * A játékos űrhajó képének a magassága.
     */
    public static int playerShipIconHeight = 35;

    /**
     * Egy ellenség űrhajó képének az elérési útja.
     */
    public static String enemyShipIcon = "/images/enemy-ship.png";

    /**
     * Egy ellenség űrhajó különbözö piros képeinek az elérési útja.
     */
    public static String[] redEnemyShipIcons = {
        "/images/enemy-ship-80hp.png",
        "/images/enemy-ship-60hp.png",
        "/images/enemy-ship-40hp.png",
        "/images/enemy-ship-20hp.png",
    };

    /**
     * Egy ellenség űrhajó képének a szélessége.
     */
    public static int enemyShipIconWidth = 35;

    /**
     * Egy ellenség űrhajó képének a magassága.
     */
    public static int enemyShipIconHeight = 38;

    /**
     * Egy játékos lézer képének az elérési útja.
     */
    public static String playerLaserIcon = "/images/blue-laser.png";

    /**
     * Egy ellenség lézer képének az elérési útja.
     */
    public static String enemyLaserIcon = "/images/orange-laser.png";

    /**
     * Egy lézer képnek a szélessége.
     */
    public static int laserIconWidth = 5;

    /**
     * Egy lézer képnek a magassága.
     */
    public static int laserIconHeight = 15;

    /**
     * Egy lézer képnek a szélessége.
     */
    public static String pauseScreen = "/images/pause-screen.png";

    /**
     * A főmenu kép elérési útja
     */
    public static String menuBackground = "/images/menu-background.png";

    /**
     * Az egyes szintek kezdetekor megjelenő képek elérési útja
     */
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

    /**
     * Az egyes szintek kezdetekor megjelenő képek szélessége
     */
    public static int levelLabelWidth = 225;

    /**
     * Az egyes szintek kezdetekor megjelenő képek magassága
     */
    public static int levelLabelHeight = 50;

    /**
     * A pontszám megjelenítésére használt betűtípus
     */
    public static String pointsFont = "/fonts/00TT.TTF";


    /**
     * Időzítő periódusa ms-ban: hány ms-onként rajzoljuk újra a játék komponenseit a játékpanelen
     * 17 ms ~ 60 fps
     */
    public static int timerPeriod = 17;

    /**
     * Az ellenség űrhajók sebessége
     */
    public static int enemySpeed = 1;

    /**
     * A játékos űrhajójának sebessége
     */
    public static int playerSpeed = 5;

    /**
     * A játékos lézereinek sebessége. Negatív, mert felfele haladnak
     */
    public static int playerLaserSpeed = -20;

    /**
     * Az ellenség lézereinek sebessége
     */
    public static int enemyLaserSpeed = 10;

    /**
     * Az első ellenség űrhajó(k) megjelenéséig/spawn-ig eltelt idő (kb. ms-ban)
     */
    public static int firstEnemySpawnTime = 5000;

    /**
     * Két ellenség spawn között eltelt idő (kb. ms-ban). Egy spawn alatt egyszerre tobb ellenség is * megjelenhet.
     */
    public static int enemySpawnPeriod = 2000;

    /**
     * Egy ellenség űrhajó két egymás utáni lövés között eltelt minimális idö (szintenként más)
     */
    public static int[] minEnemyShootPeriod = {150, 125, 100, 150, 125, 100, 150, 125, 100, 150};

    /**
     * Hány ellenség űrhajó jelenik meg egy spawn-ban (szintenként más)
     */
    public static int[] enemiesPerSpawn = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4};

    /**
     * Egy ellenség űrhajó kezdö életpontjai (szintenként más)
     */
    public static int[] enemyHealth = {60, 80, 100, 60, 80, 100, 60, 80, 100, 60};

    /**
     * A játékos két egymás utáni - ellenség űrhajóval való ütközés miatti - sebesülése között eltelt
     * minimális idő. Ha egy időpillanatban a játékos ütközik egy ellenséggel és csökken a játékos
     * HP-ja (életpontjai), akkor csak shipDamageTimeout-nyi miliszekundum után csökkenhet le megint. 
     */
    public static int shipDamageTimeout = 600;

    /**
     * A játékos két egymás utáni - ellenség lézer által okozott - sebesülése között eltelt
     * minimális idő. Ha egy időpillanatban a játékost eltalálja egy ellenség és csökken a játékos
     * HP-ja (életpontjai), akkor csak laserDamageTimeout-nyi miliszekundum után csökkenhet le
     * megint. 
     */
    public static int laserDamageTimeout = 200;

    /**
     * A spawnok száma egy szint alatt.
     */
    public static int spawnsPerLevel = 5;

    /**
     * Egy ellenség űrhajó megsemmisítéséért szerzett pontszám.
     */
    public static int pointsPerKill = 20;

    /**
     * Egy ellenség űrhajó megsemmisítéséért szerzett pontszám.
     */
    public static int pointsPerDamage = 5;

    /**
     * Egy ellenség elengedéséért járó pontszám levonás.
     */
    public static int enemyPassPenalty = 19;

    /**
     * Annak a pontszám levonásnak a mértéke, amikor lézerrel eltalálja a játékost egy ellenség.
     */
    public static int hitPenalty = 3;

    /**
     * Játékos életpontjaiból való pont levonás. Ez két esetben vonódhat le: ha lézerrel eltalálja a
     * játékost egy ellenség űrhajó, vagy ha egy ellenség űrhajóval ütközik a játékos.
     */
    public static int playerDamage = 20;

    /**
     * Ellenség űrhajó életpontjaiból való pont levonás. Ez csak akkor vonódhat le, ha a játékos
     * lézerrel eltalálja az adott ellenséget.
     */
    public static int enemyDamage = 20;
    
    /**
     * A mentett játékadatokat tároló fájl elérési útja.
     */
    public static String saveGamePath = "saved-games.dat";
}
