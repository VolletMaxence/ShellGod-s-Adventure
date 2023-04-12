package element;

import javax.swing.*;
import java.awt.*;

public class WallCase implements Case {
    protected int X;
    protected int Y;
    protected JLabel label;
    protected String imagePath;
    protected Image image;
    protected boolean Occupied = false;

    public WallCase(int X, int Y, String imagePath) {
        setPosition(X, Y);
        this.imagePath = imagePath;
        this.label = new JLabel();
        setImage(this.imagePath);
    }

    @Override
    public void CaseCreation(int X, int Y, String imagePath) {
        setPosition(X, Y);
        this.imagePath = imagePath;
        this.label = new JLabel();
        setImage(this.imagePath);
    }

    @Override
    public void setPosition(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    @Override
    public void setImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        image = icon.getImage();
        image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        label.setIcon(icon);
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Image getImage() {
        return image;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean isOccupied() {
        return Occupied;
    }

    @Override
    public void setOccupied(boolean b) {
        this.Occupied = b;
    }
}
