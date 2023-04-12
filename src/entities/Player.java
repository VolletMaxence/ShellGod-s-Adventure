package entities;

import element.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.ArrayList;

public class Player extends Entity{



    public Player(int posX, int posY) {
        super(posX, posY, new JLabel(new ImageIcon("src/asset/Shellos_Kamina.png")));
    }


    /*
    // méthode pour déplacer le joueur
    public void move(int dx, int dy, ArrayList<Case> cases) {
        // Stocker l'ancienne case où était positionné le joueur
        Case oldCase = cases.get(posX * Donjon.getTailleX() + posY);
        //System.out.println("Part de " + oldCase.getClass() + " : X = " + posX + " / Y = " + posY);

        // Mettre à jour la position du joueur
        int newPosX = posX + dx;
        int newPosY = posY + dy;

        // Vérifier si le nouveau positionnement est dans les limites du donjon
        if (newPosX < 0 || newPosX >= Donjon.getTailleX() || newPosY < 0 || newPosY >= Donjon.getTailleY()) {
            //System.out.println("Limite de map");
            return;
        }

        // Vérifier si la nouvelle case est une salle ou un mur
        Case newCase = cases.get(newPosX * Donjon.getTailleX() + newPosY);
        if (newCase instanceof WallCase) {
            //System.out.println("Tu vas droit dans le mur !");
            return;
        }

        if (newCase instanceof Case) {
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
            //System.out.println("Joueur va sur " + nouvelleCase.getClass() + " " + posX + " / Y = " + posY);
        }
    }
    */
}
