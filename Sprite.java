import javax.swing.*;
import java.awt.Image;

public abstract class Sprite {
    protected ImageIcon img;

    protected int x;
    protected int y;

    public void setImage(String imagePath) {
        ImageIcon img = new ImageIcon(imagePath);
        this.img = img;
    }

    public Image getImage() {
        return img.getImage();
    }

    public void setPos(int px, int py) {
        x = px;
        y = py;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void move();
}