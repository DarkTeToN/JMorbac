/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import jmorbac.Main;
import objet.Grille;
import objet.Joueur;

/**
 *
 * @author TeToN
 */
public class IA {

    private Joueur _joueurCourant;
    private int _profondeur;
    final private int _MIN_EVAL = -100000;
    final private int _MAX_EVAL = 100000;

    /*
     * Constructeurs
     */
    public IA() {
    }

    public IA(Joueur joueurCourant) {
        this._joueurCourant = joueurCourant;

        switch (_joueurCourant.getTypeDeJoueur()) {
            case Débutant:
                _profondeur = 3;
                break;
            case Moyen:
                _profondeur = 5;
                break;
            case Difficile:
                _profondeur = 9;
                break;
            default:
                _profondeur = 0;
                break;
        }
    }

    /*
     * Accesseurs
     */
    public Joueur getJoueurCourant() {
        return _joueurCourant;
    }

    public void setJoueurCourant(Joueur _joueurControleParOrdinateur) {
        this._joueurCourant = _joueurControleParOrdinateur;
    }

    /*
     * Fonction qui calcule le prochain coup
     */
    public void calculeIA(Grille jeu, int profondeur) {
        int tmp;
        int max = _MIN_EVAL;
        int maxi = -1;
        int maxj = -1;

        this._joueurCourant = jeu.getJoueurCourant();

        //Si la _profondeur est nulle ou la partie est finie,
        //on ne fait pas le calcul
        if ((profondeur != 0) || (!jeu.isJeuTermine())) {
            //On parcourt les cases du morpion
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //Si la case est vide
                    if (jeu.isCaseVide(i, j)) {
                        //On simule qu'on joue cette case
                        jeu.joue(i, j);
                        //On appelle la fonction calcMin pour lancer l'IA
                        tmp = calculeMin(jeu, profondeur - 1);
                        //Si ce score est plus grand

                        if (tmp > max || ((tmp == max) && ((int) Math.random() == 0))) {
                            //On le choisit
                            max = tmp;
                            maxi = i;
                            maxj = j;
                        }
                        //On annule le coup
                        System.out.println("Coup avant annulation :");
                        jeu.afficheGrilleConsole();
                        jeu.annuleCoup(i, j);
                        System.out.println("Coup annule :");
                        jeu.afficheGrilleConsole();
                    }
                }
            }
        }
        //On jouera le coup maximal
        Main.joueCoupRetenu(maxi, maxj);

    }

    /*
     * Fonctions pour le calcul 
     */
    public int calculeMin(Grille jeu, int profondeur) {

        int tmp;
        int min = _MAX_EVAL;

        //Si on est à la profondeur voulue, on retourne l'évaluation
        if (profondeur == 0) {
            return evalue(jeu);
        }

        //Si la partie est finie, on retourne l'évaluation de jeu
        if (jeu.isJeuTermine()) {
            return evalue(jeu);
        }

        //On parcourt le plateau de jeu et on le joue si la case est vide
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (jeu.isCaseVide(i, j)) {
                    //On joue le coup
                    jeu.joue(i, j);
                    tmp = calculeMax(jeu, profondeur - 1);
                    if (tmp < min) {
                        min = tmp;
                    }
                    //On annule le coup
                    jeu.annuleCoup(i, j);
                }
            }
        }
        return min;
    }

    public int calculeMax(Grille jeu, int profondeur) {
        int tmp;
        int max = _MIN_EVAL;

        //Si on est à la profondeur voulue, on retourne l'évaluation
        if (profondeur == 0) {
            return evalue(jeu);
        }

        //Si la partie est finie, on retourne l'évaluation de jeu
        if (jeu.isJeuTermine()) {
            return evalue(jeu);
        }

        //On parcourt le plateau de jeu et on le joue si la case est vide
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (jeu.isCaseVide(i, j)) {
                    //On joue le coup
                    jeu.joue(i, j);
                    tmp = calculeMax(jeu, profondeur - 1);
                    if (tmp > max) {
                        max = tmp;
                    }
                    //On annule le coup
                    jeu.annuleCoup(i, j);
                }
            }
        }
        return max;
    }

    /*
     * Fonctions qui évaluent le jeu
     */

    /*
     * ***********************************
     * * Fonction calculeScore(int, int) *
     * ***********************************
     * Pour évaluer une configuration, le programme va regarder le nombre
     * de pions et le nombre de pions de l'IA moins le nombre de pions de
     * l'adversaire pour chaque horizontale, verticale et diagonale.
     * Grâce à ces deux compteurs, nous pourrons définir ce qu'il faut
     * pour calculer la participation à la somme de l'évaluation.
     */
    public int calculeScore(int cntpions, int cntjoueur) {

        // On regarde le nombre de pions
        switch (cntpions) {
            case 1:
                return (10*cntjoueur);
            case 2:
                return (30*cntjoueur);
            default:
                return 0;
        }
    }

    public int evalue(Grille jeu) {

        int score = 0;
        int cntpion;
        int cntjoueur;
   
        //Si le jeu est fini
        if (jeu.isJeuTermine()) {
            // Si l'IA a gagné, on retourne 1000 - le nombre de pions
            if (jeu.getJoueurGagnant() == _joueurCourant) {
                return 1000 - comptePions(jeu);
            } else if (jeu.getJoueurGagnant() == null) {
                // Egalite -> on retourne 0
                return 0;
            } else {
                // Si l'IA a perdu, on retourne -1000 + le nombre de pions
                return -1000 + comptePions(jeu);
            }
        }

        cntpion = 0;
        cntjoueur = 0;

        //On regarde chaque case
        // Pour la première diagonale
        for (int i = 0; i < 3; i++) {
            //Si la case n'est pas vide
            if (!jeu.isCaseVide(i, i)) {
                //On incrémente d'un pion
                cntpion++;
                //Si c'est le même type du joueur courant
                if (_joueurCourant == jeu.getCase(i, i)) {
                    //On incrémente le compteur
                    cntjoueur++;
                } else {
                    //On décrémente le compteur
                    cntjoueur--;
                }
            }
        }

        //On ajoute au score cette nouvelle participation
        score += calculeScore(cntpion, cntjoueur);

        // Pour la deuxième diagonale
        cntpion = 0;
        cntjoueur = 0;
        for (int i = 0; i < 3; i++) {
            //Si la case n'est pas vide
            if (!jeu.isCaseVide(i, 2-i)) {
                //On incrémente d'un pion
                cntpion++;
                //Si c'est le même type du joueur courant
                if (_joueurCourant == jeu.getCase(i, 2-i)) {
                    //On incrémente le compteur
                    cntjoueur++;
                } else {
                    //On décrémente le compteur
                    cntjoueur--;
                }
            }
        }

        //On ajoute au score cette nouvelle participation
        score += calculeScore(cntpion, cntjoueur);

        // Pour les lignes
        for (int i = 0; i < 3; i++) {
            cntpion = 0;
            cntjoueur = 0;
            for (int j = 0; j < 3; j++) {
                //Si la case n'est pas vide
                if (!jeu.isCaseVide(i, j)) {
                    //On incrémente d'un pion
                    cntpion++;
                    //Si c'est le même type du joueur courant
                    if (_joueurCourant == jeu.getCase(i, j)) {
                        //On incrémente le compteur
                        cntjoueur++;
                    } else {
                        //On décrémente le compteur
                        cntjoueur--;
                    }
                }
            }
            //On ajoute au score cette nouvelle participation
            score += calculeScore(cntpion, cntjoueur);
        }

        // Pour les colonnes
        for (int i = 0; i < 3; i++) {
            cntpion = 0;
            cntjoueur = 0;
            for (int j = 0; j < 3; j++) {
                //Si la case n'est pas vide
                if (!jeu.isCaseVide(i, j)) {
                    //On incrémente d'un pion
                    cntpion++;
                    //Si c'est le même type du joueur courant
                    if (_joueurCourant == jeu.getCase(j, i)) {
                        //On incrémente le compteur
                        cntjoueur++;
                    } else {
                        //On décrémente le compteur
                        cntjoueur--;
                    }
                }
            }
            //On ajoute au score cette nouvelle participation
            score += calculeScore(cntpion, cntjoueur);
        }

        return score;
    }

    public int comptePions(Grille jeu) {
        int cntpions = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!jeu.isCaseVide(i, j)) {
                    cntpions++;
                }
            }
        }
        return cntpions;
    }
}
