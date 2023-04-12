package entities;

import element.Case;
import element.CouloirCase;
import element.SalleCase;
import ui.Dijkstra;
import ui.Graph;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Wanderer extends Ennemy {

    public Wanderer(int posX, int posY) {
        super(posX, posY, "src/asset/Wanderer.png");
    }

    public Case wander(Case depart,ArrayList<Case> cases, int tailleX) {
        Random random = new Random();
        int valeur = random.nextInt(4) + 1;

        Case caseArrive;
        if(valeur == 1)
        {
            caseArrive = cases.get((depart.getX()+1) * tailleX + depart.getY());
        } else if (valeur == 2) {
            caseArrive = cases.get((depart.getX()-1) * tailleX + depart.getY());
        } else if (valeur == 3) {
            caseArrive = cases.get(depart.getX() * tailleX + (depart.getY() + 1));
        } else {
            caseArrive = cases.get(depart.getX() * tailleX + (depart.getY() - 1));
        }

        return caseArrive;
    }
}
