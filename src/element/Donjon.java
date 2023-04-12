package element;

import entities.Ennemy;
import entities.Follower;
import entities.Player;
import entities.Wanderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JPanel;

public class Donjon extends JPanel {
    private static int tailleX;
    private static int tailleY;
    private int nombreSalles;
    private int nbEnnemis;
    private Player joueur;
    private ArrayList<Case> murs;
    private ArrayList<Ennemy> ennemis;
    private ArrayList<Salle> salles;
    private ArrayList<SalleCase> salleCase;

    private ArrayList<CouloirCase> couloirCase;

    private CaseFactory caseFactory;

    public Donjon(int tailleX, int tailleY, int nbEnnemis) {
        Donjon.tailleX = tailleX;
        Donjon.tailleY = tailleY;
        genererEtage();
    }

    public void genererEtage()
    {
        this.ennemis = new ArrayList<>();
        this.murs =new ArrayList<>();

        this.salles =new ArrayList<>();
        this.salleCase =new ArrayList<>();
        this.couloirCase =new ArrayList<>();

        //ennemis = new ArrayList<>();
        genererDonjon();
        genererSalles();
        genererCouloirs();
        genererJoueur();
        genererEscalier();

        for (int i = 0; i < salles.size(); i++)
        {
            Random random = new Random();
            int valeur = random.nextInt(6) + 1;
            if(valeur <= 5)
            {
                genererEnnemie("Wanderer");
            } else {
                genererEnnemie("Follower");
            }
        }
    }
    public Player getJoueur() {
        return joueur;
    }

    public ArrayList<Case> getMurs() {
        return murs;
    }

    public ArrayList<Ennemy> getEnnemis() {
        return ennemis;
    }

    public static int getTailleX() {
        return tailleX;
    }

    public static int getTailleY() {
        return tailleY;
    }

    /*
    public ArrayList<Ennemi> getEnnemis() {
        return ennemis;
    }
*/

    private void genererDonjon() {
        Random rand = new Random();
        int i = 0;
        int j = 0;

        // Remplir toutes les cases de la grille avec des murs
        for (i = 0; i < tailleX; i++) {
            for (j = 0; j < tailleY; j++) {
                if (i == 0 || i == tailleX-1 || j == 0 || j == tailleY-1) {
                    murs.add(CaseFactory.createCase("wall", i, j));
                } else {
                    // Ajouter un mur à chaque case non située sur le bord
                    murs.add(CaseFactory.createCase("wall", i, j));
                }
            }
        }

        // Création du joueur
        int x = rand.nextInt(tailleX);
        int y = rand.nextInt(tailleY);
        joueur = new Player(x, y);
    }

    private void genererSalles() {
        Random rand = new Random();
        nombreSalles = rand.nextInt(4) + 3;
        int i = 0;
        while (i < nombreSalles) {
            Salle salle = new Salle(rand.nextInt(9) + 2, rand.nextInt(9) + 2);
            int nbTentatives = 0;
            boolean generated = false;
            while (!generated || nbTentatives < 10) { // Limite le nombre de tentatives à 10
                nbTentatives++;
                int x = rand.nextInt(tailleX - salle.getTailleX() - 1) + 1;
                int y = rand.nextInt(tailleY - salle.getTailleY() - 1) + 1;
                salle.setPosition(x, y);
                generated = true;
            }
            i++;

            /*
            System.out.println(salle.getPosX()+" / "+salle.getPosY());
            System.out.println(salle.getTailleX()+" / "+salle.getTailleY());
            System.out.println("-----------------------------------------");
             */

            salles.add(salle);
            // Remplacer les Walls par des SalleCases
            for (int Posx = salle.getPosX(); Posx < salle.getPosX() + salle.getTailleX(); Posx++) {
                for (int Posy = salle.getPosY(); Posy < salle.getPosY() + salle.getTailleY(); Posy++) {
                    int index = Posx * tailleX + Posy;
                    murs.set(index, CaseFactory.createCase("salle", Posx, Posy));
                }
            }
        }
    }

    private void genererJoueur() {
        Random rand = new Random();
        boolean joueurPlace = false;

        while (!joueurPlace) {
            int index = rand.nextInt(this.murs.size());
            Case selectedCase = this.murs.get(index);
            if (selectedCase instanceof SalleCase && !selectedCase.isOccupied()) {
                SalleCase salleCase = (SalleCase) selectedCase;

                // Ajoute le joueur sur cette case
                joueur.setPosition(salleCase.getX(), salleCase.getY());
                salleCase.setOccupied(true);
                this.add(joueur.getLabel());
                joueurPlace = true;
            }
        }
    }

    private void genererCouloirs() {
        Random rand = new Random();

        // Relier chaque salle à une autre aléatoirement
        for (int i = 0; i < salles.size(); i++) {
            Salle salleA = salles.get(i);
            Salle salleB = salles.get(rand.nextInt(salles.size()));
            while (salleA.equals(salleB)) {
                salleB = salles.get(rand.nextInt(salles.size()));
            }

            // Créer le couloir
            int startX = salleA.getPosX() + rand.nextInt(salleA.getTailleX());
            int startY = salleA.getPosY() + rand.nextInt(salleA.getTailleY());
            int endX = salleB.getPosX() + rand.nextInt(salleB.getTailleX());
            int endY = salleB.getPosY() + rand.nextInt(salleB.getTailleY());

            // Si les points de départ et d'arrivée sont sur des SalleCase, remplacer par des CouloirCase
            if (murs.get(startX * tailleY + startY) instanceof SalleCase) {
                murs.set(startX * tailleY + startY, CaseFactory.createCase("salle", startX, startY));
            }
            if (murs.get(endX * tailleY + endY) instanceof SalleCase) {
                murs.set(endX * tailleY + endY, CaseFactory.createCase("salle", endX, endY));
            }

            // Ajouter le CouloirCase
            int posX = startX;
            int posY = startY;
            while (posX != endX || posY != endY) {
                if (posX != endX && rand.nextBoolean()) {
                    posX += (posX < endX) ? 1 : -1;
                } else if (posY != endY) {
                    posY += (posY < endY) ? 1 : -1;
                } else {
                    posX += (posX < endX) ? 1 : -1;
                }

                // Si la case n'est pas déjà un CouloirCase, remplacer par un CouloirCase
                if (!(murs.get(posX * tailleY + posY) instanceof CouloirCase)) {
                    murs.set(posX * tailleY + posY, CaseFactory.createCase("couloir", posX, posY));
                }
            }
        }
    }

    public void genererEscalier() {
        Random rand = new Random();

        // Récuperer une instance de "murs" aléatoirement
        boolean selectedCaseIsSalle = false;
        while(!selectedCaseIsSalle)
        {
            // Récupérez la SalleCase correspondante
            int index = rand.nextInt(this.murs.size());
            Case salleCase = this.murs.get(index);

            if (salleCase instanceof SalleCase) {
                selectedCaseIsSalle = true;

                SalleCase salleCaseToDecorate = (SalleCase) salleCase;
                // Traitez la SalleCase ici
                Escalier escalier = new Escalier(salleCaseToDecorate.getX(), salleCaseToDecorate.getY());
                murs.set(index, escalier);
            } else {
                // Traitez le cas où l'instance n'est pas une SalleCase
            }
        }
    }

    private void genererEnnemie(String typeEnnemi) {
        Random rand = new Random();
        boolean ennemiePlace = false;

        while (!ennemiePlace) {
            int index = rand.nextInt(this.murs.size());
            Case selectedCase = this.murs.get(index);
            if (selectedCase instanceof SalleCase && !((SalleCase) selectedCase).isOccupied()) {
                SalleCase salleCase = (SalleCase) selectedCase;

                //Vérifier si l'emplacement est a 2 case ou moins du joueur
                int distance = Math.abs(murs.get(joueur.getPosX() * Donjon.getTailleX() + joueur.getPosY()).getX() - salleCase.getX()) + Math.abs(murs.get(joueur.getPosX() * Donjon.getTailleX() + joueur.getPosY()).getY() - salleCase.getY());
                if (distance <= 2) {
                    continue;
                }

                if(typeEnnemi == "Follower")
                {
                    // Ajoute l'ennemie sur cette case
                    Follower follower = new Follower(salleCase.getX(), salleCase.getY());
                    salleCase.setOccupied(true);
                    this.add(follower.getLabel());

                    ennemiePlace = true;
                    ennemis.add(follower);
                } else if (typeEnnemi == "Wanderer")
                {
                    // Ajoute l'ennemie sur cette case
                    Wanderer wanderer = new Wanderer(salleCase.getX(), salleCase.getY());
                    salleCase.setOccupied(true);
                    this.add(wanderer.getLabel());

                    ennemiePlace = true;
                    ennemis.add(wanderer);
                }
            }
        }
    }
}
