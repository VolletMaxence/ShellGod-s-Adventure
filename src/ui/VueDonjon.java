package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import element.*;
import entities.Ennemy;
import entities.Follower;
import entities.Wanderer;

public class VueDonjon extends JFrame {
	private static VueDonjon instance;
	private Donjon donjon;
	private JPanel panel;
	private BufferedImage playerImage;
	private BufferedImage errorCaseImage;
	private ArrayList<Case> cases;
	private ArrayList<Ennemy> ennemies;
	public VueDonjon(Donjon donjon) {
		this.donjon = donjon;
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // Désactiver la redimension
		setTitle("ShellGod's Adventure");
		chargerImages();

		drowEtage();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP -> donjon.getJoueur().move(0, -1, cases);
					case KeyEvent.VK_DOWN -> donjon.getJoueur().move(0, 1, cases);
					case KeyEvent.VK_LEFT -> donjon.getJoueur().move(-1, 0, cases);
					case KeyEvent.VK_RIGHT -> donjon.getJoueur().move(1, 0, cases);
					case KeyEvent.VK_R -> {
						dispose(); // Fermer la fenêtre VueDonjon
						Menu menu = null;
						try {
							menu = new Menu();
						} catch (IOException ex) {
							throw new RuntimeException(ex);
						}
						menu.setVisible(true); // Afficher la fenêtre Menu
					}
					default -> {
					}
				}
				// vérifie si le joueur est sur un escalier
				Case caseJoueur = cases.get(donjon.getJoueur().getPosX() * Donjon.getTailleX() + donjon.getJoueur().getPosY());
				if (caseJoueur instanceof Escalier) {
					System.out.println("Sur Escalier");
					donjon.genererEtage();
					drowEtage();
				}
				panel.repaint();

				//Vérifier si ennemie est a coté de joueur
				finPartie();
			}
		});

	}


	public static VueDonjon getInstance(Donjon donjon) {
		if (instance == null) {
			instance = new VueDonjon(donjon);
		}
		return instance;
	}

	public void drowEtage()
	{
		// transforme l'ArrayList<Case> en une ArrayList<Wall>
		cases = donjon.getMurs();

		panel = new JPanel() {
			@Override
			protected void paintComponent (Graphics g){
				super.paintComponent(g);
				for (Case element : cases) {
					try {
						if (element instanceof WallCase) {
							g.drawImage(((WallCase) element).getImage(), element.getX() * 32, element.getY() * 32, null);
						} else if (element instanceof SalleCase) {
							g.drawImage(((SalleCase) element).getImage(), element.getX() * 32, element.getY() * 32, null);
						} else if (element instanceof CouloirCase) {
							g.drawImage(((CouloirCase) element).getImage(), element.getX() * 32, element.getY() * 32, null);
						} else {
							g.drawImage(errorCaseImage, element.getX() * 32, element.getY() * 32, null);
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				// dessine l'image du joueur
				g.drawImage(playerImage, donjon.getJoueur().getPosX() * 32, donjon.getJoueur().getPosY() * 32, null);

				ennemies =  donjon.getEnnemis();
				for (Ennemy ennemie : ennemies) {
					g.drawImage(ennemie.getImage(), ennemie.getPosX() * 32, ennemie.getPosY() * 32, null);
				}
				mouvementEnnemie();
			}
		};

		panel.setPreferredSize(new Dimension(Donjon.getTailleX() *32,Donjon.getTailleY()*32));
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void mouvementEnnemie() {
		for (Ennemy ennemie : ennemies) {
			if (ennemie instanceof Follower) {
				ArrayList<Case> cheminVersJoueur = ((Follower) ennemie).goToJoueur(
						cases.get(ennemie.getPosX() * Donjon.getTailleX() + ennemie.getPosY()),
						cases.get(donjon.getJoueur().getPosX() * Donjon.getTailleX() + donjon.getJoueur().getPosY()),
						cases,
						Donjon.getTailleX(),
						Donjon.getTailleY()
				);

				Case prochaineCaseEnnemie = cheminVersJoueur.get(1);
				int changementX = prochaineCaseEnnemie.getX() - ennemie.getPosX();
				int changementY = prochaineCaseEnnemie.getY() - ennemie.getPosY();

				ennemie.move(changementX, changementY, cases);
			} else if (ennemie instanceof Wanderer) {
				Case prochaineCaseEnnemie = ((Wanderer) ennemie).wander(
						cases.get(ennemie.getPosX() * Donjon.getTailleX() + ennemie.getPosY()),
						cases,
						Donjon.getTailleX()
				);
				int changementX = prochaineCaseEnnemie.getX() - ennemie.getPosX();
				int changementY = prochaineCaseEnnemie.getY() - ennemie.getPosY();

				ennemie.move(changementX, changementY, cases);
			}
		}
	}

	private void finPartie() {
		for (Ennemy ennemie : ennemies) {
			//Vérifier si le joueur est à coté de l'ennemie
			ennemie.isJoueurACote(donjon.getJoueur());

			if(ennemie.getaCoteJoueur())
			{
				//Fin de jeu
			}
		}
	}

	private void chargerImages() {
		try {
			playerImage = ImageIO.read(new File("src/asset/Shellos_Kamina.png"));
			errorCaseImage = ImageIO.read(new File("src/asset/Error.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
