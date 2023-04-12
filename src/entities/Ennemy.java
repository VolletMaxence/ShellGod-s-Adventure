package entities;

import element.Case;

import javax.swing.*;
import java.awt.*;

public class Ennemy extends Entity {
    protected Image image;
    protected String imagePath;
    protected Boolean aCoteJoueur = false;
    public Ennemy(int posX, int posY, String imagePath) {
        super(posX, posY, new JLabel(imagePath));
        this.imagePath = imagePath;
        setImage(this.imagePath);
    }

    public void setImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        image = icon.getImage();
        image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        label.setIcon(icon);
    }

    public Image getImage() {
        return image;
    }

    public Boolean getaCoteJoueur() {
        return aCoteJoueur;
    }

    public void isJoueurACote(Player joueur) {
        int joueurX = joueur.getPosX();
        int joueurY = joueur.getPosY();

        int distanceX = Math.abs(posX - joueurX);
        int distanceY = Math.abs(posY - joueurY);

        if ((distanceX <= 1 && distanceY == 0) || (distanceX == 0 && distanceY <= 1))
        {
            aCoteJoueur = true;
        }
    }
}