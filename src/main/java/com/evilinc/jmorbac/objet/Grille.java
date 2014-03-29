/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac.objet;

import javax.swing.JOptionPane;
import com.evilinc.jmorbac.jmorbac.Main;

/**
 *
 * @author TeToN
 */
public class Grille implements Cloneable {

    private int[][] _tableau;
    private boolean _jeuTermine;
    private Joueur _joueurGagnant;
    private Joueur _joueurCourant;


    /*
     * Constructeurs
     */
    public Grille() {
        initialiseTableau();
        initialiseIA();
        this._joueurCourant = Main._joueurDevantJouer;
    }

    public Grille (Grille grille) {
        this._tableau = new int[3][3];

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                this._tableau[i][j] = grille.getTableau()[i][j];
            }
        }
        this._jeuTermine = grille.isJeuTermine();
        this._joueurCourant = grille.getJoueurCourant();
        this._joueurGagnant = grille.getJoueurGagnant();
    }

    /*
     * Getters
     */
    public int[][] getTableau() {
        return _tableau;
    }

    public boolean isJeuTermine() {
        return _jeuTermine;
    }

    public void setJeuTermine(boolean _jeuTermine) {
        this._jeuTermine = _jeuTermine;
    }

    public Joueur getJoueurGagnant() {
        return _joueurGagnant;
    }

    public void setJoueurGagnant(Joueur _joueurGagnant) {
        this._joueurGagnant = _joueurGagnant;
    }

    public Joueur getJoueurCourant() {
        return _joueurCourant;
    }

    public void setJoueurCourant(Joueur _joueurCourant) {
        this._joueurCourant = _joueurCourant;
    }



    /*
     * Méthodes
     */
    public void initialiseTableau() {
        _tableau = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                _tableau[i][j] = 0;
            }
        }
    }

    public void initialiseIA() {
        _jeuTermine = false;
        _joueurGagnant = null;
        _joueurCourant = Main._joueurDevantJouer;
    }

    public boolean verifieAlignement() {

        int resultatTemp = 0;

        // On vérifie la première ligne
        resultatTemp = _tableau[0][0] + _tableau[0][1] + _tableau[0][2];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return true;
        }

        // On vérifie la deuxième ligne
        resultatTemp = _tableau[1][0] + _tableau[1][1] + _tableau[1][2];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return true;
        }

        // On vérifie la troisième ligne
        resultatTemp = _tableau[2][0] + _tableau[2][1] + _tableau[2][2];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return true;
        }

        // On vérifie la première colonne
        resultatTemp = _tableau[0][0] + _tableau[1][0] + _tableau[2][0];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return true;
        }

        // On vérifie la deuxième colonne
        resultatTemp = _tableau[0][1] + _tableau[1][1] + _tableau[2][1];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return true;
        }

        // On vérifie la troisième colonne
        resultatTemp = _tableau[0][2] + _tableau[1][2] + _tableau[2][2];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return true;
        }

        // On vérifie la première diagonale (de haut-gauche à bas-droite)
        resultatTemp = _tableau[0][0] + _tableau[1][1] + _tableau[2][2];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return true;
        }
        // On vérifie la deuxième diagonale (de bas-gauche à haut-droite)
        resultatTemp = _tableau[0][2] + _tableau[1][1] + _tableau[2][0];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return true;
        }

        return false;
    }

    public boolean joueCoup(int x, int y) {

        if (x < 3 && y < 3) {
            if (_tableau[x][y] == 0) {
                if (Main._joueurDevantJouer.equals(Main._joueur1)) {
                    _tableau[x][y] = 1;
                } else {
                    _tableau[x][y] = -1;
                }
            } else {
                JOptionPane.showMessageDialog(null, "ERREUR : Un coup a déjà été joué dans cette case !");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERREUR : Le coup joué est en dehors de la grille");
            return false;
        }
        Main.finDuTour();

        return true;
    }

    public void afficheGrilleConsole() {

        System.out.println("\nNombre de coups : " + Main._nombreDeCoups + "\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (_tableau[i][j] == 0) {
                    System.out.print("- ");
                } else if (_tableau[i][j] == 1) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public boolean isCaseVide(int i, int j) {
        return _tableau[i][j] == 0 ? true : false;
    }

    public Joueur getCase(int i, int j) {
        if (_tableau[i][j] != 0) {
            if (_tableau[i][j] == 1) {
                return Main._joueur1;
            } else {
                return Main._joueur2;
            }
        } else {
            return null;
        }
    }

    public void joue(int i, int j) {
        _tableau[i][j] = _joueurCourant.getCouleurInt();
        _jeuTermine = verifieAlignement();
        if (!_jeuTermine) {
            if (_joueurCourant == Main._joueur1) {
                _joueurCourant = Main._joueur2;
            } else {
                _joueurCourant = Main._joueur1;
            }
        } else {
            _joueurGagnant = _joueurCourant;
        }
    }

    public void annuleCoup(int i, int j) {
        _tableau[i][j] = _joueurCourant.getCouleurInt();
        _joueurCourant = _joueurCourant == Main._joueur1 ? Main._joueur2 : Main._joueur1;
        _jeuTermine = verifieAlignement();
        if (_jeuTermine) {
            _joueurGagnant = _joueurCourant;
        }
    }

    @Override
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return o;
    }


}
