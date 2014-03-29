/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.evilinc.jmorbac.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.evilinc.jmorbac.jmorbac.Main;

/**
 *
 * @author TeToN
 */
public class Menu extends JPanel {

    JMenuBar _barreDeMenu;
    JMenu _menuFichier;
    JMenuItem _menuFichierNouvellePartie;
    JMenuItem _menuFichierQuitter;
    JMenu _menuOptions;
    JMenuItem _menuOptionsDifficulté;
    JMenuItem _menuOptionsAPropos;

    public Menu() {
        super();

        initialiseMenu();
    }

    private void initialiseMenu() {
        this.setLayout(new BorderLayout());

        // Instanciation des menus
        this._barreDeMenu = new JMenuBar();
        _barreDeMenu.setBackground(Color.white);
        this._menuFichier = new JMenu("Fichier");
        this._menuFichierNouvellePartie = new JMenuItem("Nouvelle Partie");
        this._menuFichierQuitter = new JMenuItem("Quitter");
        this._menuOptions = new JMenu("Options");
        this._menuOptionsDifficulté = new JMenuItem("Difficulté");
        this._menuOptionsAPropos = new JMenuItem("A propos de JMorbac...");

        // Ajout des menus
        _menuFichier.add(_menuFichierNouvellePartie);
        _menuFichier.add(_menuFichierQuitter);
        _menuOptions.add(_menuOptionsDifficulté);
        _menuOptions.add(_menuOptionsAPropos);

        _barreDeMenu.add(_menuFichier);
        _barreDeMenu.add(_menuOptions);

        // Initialisation des actions des menus
        _menuFichierQuitter.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        _menuOptionsAPropos.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "JMorbac v1.0\nCréé par TeToN");
            }
        });

        _menuFichierNouvellePartie.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Main._finDePartie = false;
                Main._grille.initialiseTableau();
                Main._fenetreDeJeu.getPanelPrincipal().repaint();
                Main._nombreDeCoups = 0;
            }
        });

        _menuOptionsDifficulté.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "En cours de réalisation...");
            }
        });

        this.add(_barreDeMenu, BorderLayout.NORTH);
    }
}
