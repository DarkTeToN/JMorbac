/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac.jmorbac;

import com.evilinc.jmorbac.gui.Fenetre;
import com.evilinc.jmorbac.ia.IA;
import javax.swing.JOptionPane;
import com.evilinc.jmorbac.objet.Grille;
import com.evilinc.jmorbac.objet.Joueur;

/**
 *
 * @author TeToN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static Joueur _joueurDevantJouer;
    public static Joueur _joueur1 = new Joueur('O', TypeJoueur.Humain);
    public static Joueur _joueur2 = new Joueur('X', TypeJoueur.Difficile);
    public static boolean _finDePartie = false;
    public static int _nombreDeCoups = 0;
    public static Fenetre _fenetreDeJeu;
    public static Grille _grille;
    public static IA _cpu = new IA(Main._joueur2);

    public static void main(String[] args) {
        // Affichage de la fenêtre
        _joueurDevantJouer = _joueur1;
        _grille = new Grille();
        _fenetreDeJeu = new Fenetre("JMorbac 1.0 by TeToN");

        _grille.afficheGrilleConsole();
        while (true) {
            while (!_finDePartie) {
            }

            String messageDeFin;

            if (_grille.verifieAlignement()) {
                messageDeFin = "BRAVO ! Le joueur " + _joueurDevantJouer.getCouleur() + " a gagné !\n\nSouhaitez-vous rejouer ?";
            } else {
                messageDeFin = "Egalité\n\nUne autre partie ?";
            }
            //JOptionPane.showOptionDialog(_fenetreDeJeu, messageDeFin, null, _nombreDeCoups, _nombreDeCoups, null, args, _grille);
            Object[] options = { "Rejouer", "Quitter" };
            int choix = JOptionPane.showOptionDialog(null, messageDeFin, "Fin de la partie",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
            if (choix == 0) {
                _grille.initialiseTableau();
                _finDePartie = false;
                _fenetreDeJeu.getPanelPrincipal().repaint();
                _nombreDeCoups = 0;
            } else {
                System.exit(0);
            }
        }


    }

    public static void finDuTour() {
        if (!_grille.verifieAlignement()) {
            if (_joueur1.equals(_joueurDevantJouer)) {
                _joueurDevantJouer = _joueur2;
            } else {
                _joueurDevantJouer = _joueur1;
            }
            _nombreDeCoups++;

            if (_nombreDeCoups == 9) {
                _finDePartie = true;
                //JOptionPane.showMessageDialog(_fenetreDeJeu, "Egalité !");
            }
        } else {
            _finDePartie = true;
            JOptionPane.showMessageDialog(_fenetreDeJeu, "Félicitations ! Le joueur avec les " + _joueurDevantJouer.getCouleur() + " a gagné !");
        }


    }

    public static void joueCoupRetenu(int i, int j) {
        _grille.joueCoup(i, j);
    }
}
