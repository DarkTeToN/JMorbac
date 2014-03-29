/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.evilinc.jmorbac.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author TeToN
 */
public class Fenetre extends JFrame {

    private PlateauJeu _panelPrincipal;
    private Menu _panelHaut;
    private JPanel _panelBas;
    private PanelGauche _panelGauche;
    private JPanel _panelDroite;

    public Fenetre(String nom) {
        super(nom);
        initFenetre();
        initGraphics();

        this.setVisible(true);
    }

    private void initFenetre() {
        
        this.setBounds(300, 300, 640, 480);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(new BorderLayout());
        this._panelPrincipal = new PlateauJeu();
        this._panelBas = new JPanel();
        this._panelHaut = new Menu();
        this._panelGauche = new PanelGauche();
        this._panelDroite = new JPanel();

        _panelBas.setBackground(Color.white);
        _panelBas.setPreferredSize(new Dimension(500,50));
        _panelHaut.setPreferredSize(new Dimension(500,50));
        _panelHaut.setBackground(Color.WHITE);
        _panelGauche.setBackground(Color.WHITE);
        _panelDroite.setBackground(Color.WHITE);
        _panelDroite.setPreferredSize(new Dimension(10,500));

        this.add(_panelPrincipal, BorderLayout.CENTER);
        this.add(_panelBas, BorderLayout.SOUTH);
        this.add(_panelHaut, BorderLayout.NORTH);
        this.add(_panelGauche, BorderLayout.WEST);
        this.add(_panelDroite, BorderLayout.EAST);

    }

    private void initGraphics() {

        _panelPrincipal.setVisible(true);
        this.repaint();
    }

    /*
     * Accesseurs
     */

    public JPanel getPanelBas() {
        return _panelBas;
    }

    public JPanel getPanelDroite() {
        return _panelDroite;
    }

    public JPanel getPanelGauche() {
        return _panelGauche;
    }

    public Menu getPanelHaut() {
        return _panelHaut;
    }

    public PlateauJeu getPanelPrincipal() {
        return _panelPrincipal;
    }
}
