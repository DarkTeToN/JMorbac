/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objet;

import jmorbac.TypeJoueur;

/**
 *
 * @author TeToN
 */
public class Joueur {

    private char _couleur;
    private TypeJoueur _typeDeJoueur;
    private int _couleurInt;

    public Joueur(char _couleur) {
        this._couleur = _couleur;
        _couleurInt = _couleur == 'X' ? -1 : 1;
    }

    public Joueur(char _couleur, TypeJoueur _typeDeJoueur) {
        this._couleur = _couleur;
        _couleurInt = _couleur == 'X' ? -1 : 1;
        this._typeDeJoueur = _typeDeJoueur;
    }

    public int getCouleurInt() {
        return _couleurInt;
    }

    public void setCouleurInt(int _couleurInt) {
        this._couleurInt = _couleurInt;
    }

    public TypeJoueur getTypeDeJoueur() {
        return _typeDeJoueur;
    }

    public void setTypeDeJoueur(TypeJoueur _typeDeJoueur) {
        this._typeDeJoueur = _typeDeJoueur;
    }

    public char getCouleur() {
        return _couleur;
    }
    
}
