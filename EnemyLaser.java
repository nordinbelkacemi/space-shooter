public class EnemyLaser extends Laser {
    public EnemyLaser(int x, int y) {
        super(x, y);
        setImage(Constants.enemyLaserIcon, Constants.laserIconWidth, Constants.laserIconHeight);
        this.speed = Constants.enemyLaserSpeed;
    }

    protected void giveDamage(SpaceShip spaceShip) {
        spaceShip.takeDamage(Constants.laserDamageTimeout);
    }
}