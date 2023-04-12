package element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Salle {
    private int posX;
    private int posY;
    private int tailleX;
    private int tailleY;
    private ArrayList<SalleCase> cases;

    public Salle(int tailleX, int tailleY) {
        this.tailleX = Math.max(3, Math.min(tailleX, 10));
        this.tailleY = Math.max(3, Math.min(tailleY, 10));
        this.cases = new ArrayList<SalleCase>();
        genererSalle();
    }

    private void genererSalle() {
        for (int i = 0; i < tailleX; i++) {
            for (int j = 0; j < tailleY; j++) {
                SalleCase nouvelleCase = (SalleCase) CaseFactory.createCase("salle",posX + i, posY + j);
                cases.add(nouvelleCase);
            }
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getTailleX() {
        return tailleX;
    }

    public int getTailleY() {
        return tailleY;
    }

    public List<SalleCase> getCases() {
        return cases;
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
        for (SalleCase salleCase : cases) {
            salleCase.setPosition(x + salleCase.getX() - this.getPosX(), y + salleCase.getY() - this.getPosY());
        }
    }

    public boolean intersects(Salle other) {
        // Vérifier si les bounding boxes des deux salles se chevauchent
        int xMin = Math.min(this.getPosX(), other.getPosX());
        int xMax = Math.max(this.getPosX() + this.getTailleX() - 1, other.getPosX() + other.getTailleX() - 1);
        int yMin = Math.min(this.getPosY(), other.getPosY());
        int yMax = Math.max(this.getPosY() + this.getTailleY() - 1, other.getPosY() + other.getTailleY() - 1);
        if (xMax - xMin >= this.getTailleX() + other.getTailleX() || yMax - yMin >= this.getTailleY() + other.getTailleY()) {
            return false;
        }

        // Vérifier si les deux salles ont des cases communes
        for (SalleCase salleCase : this.cases) {
            for (SalleCase otherCase : other.cases) {
                if (salleCase.getX() == otherCase.getX() && salleCase.getY() == otherCase.getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int manhattanDistance(Salle other) {
        int dx = Math.abs(this.getPosX() - other.getPosX());
        int dy = Math.abs(this.getPosY() - other.getPosY());
        System.out.println("Distance Manhattan : "+ dx+dy);
        return dx + dy;
    }

    public Salle getSallesAdjacentes(List<Salle> salles) {
        Salle sallePlusProche = null;
        int distanceMin = Integer.MAX_VALUE;

        for (Salle s : salles) {
            // Ne pas considérer la salle actuelle
            if (s == this) {
                continue;
            }

            int distance = manhattanDistance(s);
            if (distance < distanceMin) {
                distanceMin = distance;
                sallePlusProche = s;
            }
        }

        return sallePlusProche;
    }

    public SalleCase getSalleCase(int x, int y) {
        return cases.get(1);
    }
}