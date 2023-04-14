package ui;

import element.Donjon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuV2 extends JFrame {
    private static MenuV2 instance;
    private MenuV2() throws IOException {
        // Charger l'image de fond
        BufferedImage backgroundImage = ImageIO.read(new File("src/asset/LogoSGA.png"));

        // Créer le bouton pour lancer le jeu
        JButton jouer = new JButton("Jouer");
        jouer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                launchGame();
            }
        });

        // Créer le conteneur pour les boutons et les images
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setLayout(new BorderLayout());
        //Ajouter bouton
        jouer.setPreferredSize(new Dimension(150, 75));

        //Création du JPanel pour le boutton, on y ajoute la backgroundImage  pour pouvoir l'afficher
        JPanel panelBouton = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panelBouton.setOpaque(false);

        //Placement du boutton
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        panelBouton.add(Box.createVerticalGlue(), gbc);
        gbc.gridy++;
        panelBouton.add(jouer, gbc);
        gbc.gridy++;
        panelBouton.add(Box.createVerticalStrut(75), gbc);
        gbc.gridy++;
        panelBouton.add(Box.createVerticalGlue(), gbc);
        contentPane.add(panelBouton, BorderLayout.CENTER);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null); // Centrer l'écran
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false); // Désactiver la redimension
        setTitle("ShellGod's Adventure");
    }

    public static MenuV2 getInstance() throws IOException {
        if (instance == null) {
            instance = new MenuV2();
        }
        return instance;
    }

    public void launchGame() {
        // Configurer la nouvelle instance de Donjon
        Donjon donjon = new Donjon(20, 20, 0);
        VueDonjon vueDonjon = new VueDonjon(donjon);
        // Configurer la VueDonjon existante pour afficher le nouveau Donjon
        vueDonjon.setVisible(true);
        // Fermer l'écran de menu
        dispose();
    }
}
