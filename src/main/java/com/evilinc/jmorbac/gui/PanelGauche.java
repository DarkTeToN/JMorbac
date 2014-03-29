/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author TeToN
 */
public class PanelGauche extends JPanel {

    private ButtonGroup _menuRadio;
    private JRadioButton _menuRadio1;
    private JRadioButton _menuRadio2;

    public PanelGauche() {
        super();
        initialisePanelGauche();
    }

    public void initialisePanelGauche() {

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setPreferredSize(new Dimension(155, 500));

        this._menuRadio = new ButtonGroup();
        this._menuRadio1 = new JRadioButton("Joueur VS Ordinateur");
        this._menuRadio2 = new JRadioButton("Joueur VS Joueur");

        this._menuRadio1.setBackground(Color.white);
        this._menuRadio2.setBackground(Color.white);
        this._menuRadio1.setEnabled(false);
        this._menuRadio1.setVisible(true);
        this._menuRadio2.setSelected(true);

        this._menuRadio.add(_menuRadio1);
        this._menuRadio.add(_menuRadio2);

        this.add(_menuRadio1);
        this.add(_menuRadio2);

    }
}
