package ui;

import element.Donjon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame {
    private static Menu instance;
    public Menu() throws IOException {

        // Charger l'image de fond
        BufferedImage backgroundImage = ImageIO.read(new File("src/asset/LogoSGA.png"));

        // Créer un JPanel personnalisé pour afficher l'image de fond
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Créer le bouton pour lancer le jeu
        JButton jouer = new JButton("Jouer");
        jouer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                launchGame();
            }
        });

        // Créer le conteneur pour les boutons et les images
        JPanel contentPane = new JPanel(new BorderLayout());

        //Afficher Images Emmenie
        // Chargement des images
        BufferedImage image1 = ImageIO.read(new File("src/asset/Follower.png"));
        BufferedImage image2 = ImageIO.read(new File("src/asset/Wanderer.png"));

        // Création de deux JLabel pour afficher les images
        JLabel label1 = new JLabel(new ImageIcon(image1));
        JLabel label2 = new JLabel(new ImageIcon(image2));

        // Ajout des labels au panel
        contentPane.add(label1, BorderLayout.WEST);
        contentPane.add(label2, BorderLayout.EAST);

        //Ajouter bouton
        jouer.setPreferredSize(new Dimension(200, 200));
        JPanel panelBouton = new JPanel(new GridBagLayout());
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

        // Changer la couleur de fond
        getContentPane().setBackground(new Color(22, 22, 29));
        // Changer la couleur du texte
        setForeground(Color.WHITE);
    }

    public static Menu getInstance() throws IOException {
        if (instance == null) {
            instance = new Menu();
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
