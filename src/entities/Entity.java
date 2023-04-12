package entities;

import element.*;

import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;

public class Entity {

    protected int posX;
    protected int posY;
    protected JLabel label;
    public Entity(int posX, int posY, JLabel entityLabel) {
        this.posX = posX;
        this.posY = posY;
        this.label = entityLabel;
        this.label.setBounds(posX*40, posY*40, 40, 40);
    }

    public void move(int dx, int dy, ArrayList<Case> cases) {
        // Stocker l'ancienne case où était positionné le joueur
        Case oldCase = cases.get(posX * Donjon.getTailleX() + posY);

        // Mettre à jour la position du joueur
        int newPosX = posX + dx;
        int newPosY = posY + dy;

        // Vérifier si le nouveau positionnement est dans les limites du donjon
        if (newPosX < 0 || newPosX >= Donjon.getTailleX() || newPosY < 0 || newPosY >= Donjon.getTailleY()) {
            return;
        }

        // Vérifier si la nouvelle case est une salle ou un mur
        Case newCase = cases.get(newPosX * Donjon.getTailleX() + newPosY);
        if (newCase instanceof WallCase) {
            return;
        }

        if (newCase instanceof Case) {
            if (newCase.isOccupied())
            {
                return;
            }
            Case nouvelleCase = (Case) newCase;
            if (oldCase instanceof SalleCase) {
                ((SalleCase) oldCase).setOccupied(false);
            } else if (oldCase instanceof CouloirCase) {
                ((CouloirCase) oldCase).setOccupied(false);
            }
            if (nouvelleCase instanceof SalleCase) {
                ((SalleCase) nouvelleCase).setOccupied(true);
            } else if (nouvelleCase instanceof CouloirCase) {
                ((CouloirCase) nouvelleCase).setOccupied(true);
            }

            posX = newPosX;
            posY = newPosY;
            label.setLocation(posX * 40, posY * 40);
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
        this.label.setLocation(posX*40, posY*40);
    }

    public void setPosY(int posY) {
        this.posY = posY;
        this.label.setLocation(posX*40, posY*40);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        label.setBounds(posX * 40, posY * 40, 40, 40);
    }
}