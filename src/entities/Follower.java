package entities;

import element.Case;
import element.CouloirCase;
import element.SalleCase;
import ui.Dijkstra;
import ui.Graph;

import javax.swing.*;
import java.util.ArrayList;

public class Follower extends Ennemy{

    public Follower(int posX, int posY) {
        super(posX, posY, "src/asset/Follower.png");
    }

    public ArrayList<Case> goToJoueur(Case depart, Case arrivee, ArrayList<Case> cases, int tailleX, int tailleY) {
        // Créer un graphe à partir des salles et couloirs
        Graph<Case> graph = new Graph<>();
        for (int i = 0; i < cases.size(); i++) {
            Case c = cases.get(i);
            if (c instanceof SalleCase || c instanceof CouloirCase) {
                graph.addVertex(c);
                int x = c.getX();
                int y = c.getY();

                if (x > 0 && (cases.get((x-1)*tailleY+y) instanceof SalleCase || cases.get((x-1)*tailleY+y) instanceof CouloirCase)) {
                    graph.addEdge(c, cases.get((x-1)*tailleY+y));
                }
                if (x < tailleX-1 && (cases.get((x+1)*tailleY+y) instanceof SalleCase || cases.get((x+1)*tailleY+y) instanceof CouloirCase)) {
                    graph.addEdge(c, cases.get((x+1)*tailleY+y));
                }
                if (y > 0 && (cases.get(x*tailleY+(y-1)) instanceof SalleCase || cases.get(x*tailleY+(y-1)) instanceof CouloirCase)) {
                    graph.addEdge(c, cases.get(x*tailleY+(y-1)));
                }
                if (y < tailleY-1 && (cases.get(x*tailleY+(y+1)) instanceof SalleCase || cases.get(x*tailleY+(y+1)) instanceof CouloirCase)) {
                    graph.addEdge(c, cases.get(x*tailleY+(y+1)));
                }
            }
        }
        // Trouver le chemin le plus court avec l'algorithme de Dijkstra
        Dijkstra<Case> dijkstra = new Dijkstra<>(graph);
        ArrayList<Case> chemin = dijkstra.getPath(depart, arrivee);
        return chemin;
    }

}
