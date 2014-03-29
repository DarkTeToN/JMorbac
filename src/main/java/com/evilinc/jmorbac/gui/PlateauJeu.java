/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac.gui;

import com.evilinc.jmorbac.ia.IA;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import com.evilinc.jmorbac.jmorbac.Main;
import com.evilinc.jmorbac.jmorbac.TypeJoueur;
import com.evilinc.jmorbac.objet.Grille;

/**
 *
 * @author TeToN
 */
public class PlateauJeu extends JPanel implements MouseListener {

    public PlateauJeu() {
        super();

        // Ajout du listener pour les actions de la souris
        this.addMouseListener(this);
        this.setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

            // Tracé des lignes verticales
            g.drawLine(this.getWidth() / 3, 0, this.getWidth() / 3, this.getHeight());
            g.drawLine(((int) (2 * this.getWidth() / 3)), 0, ((int) (2 * this.getWidth() / 3)), this.getHeight());
            /*
            try {
            g.drawImage(ImageIO.read(new File("src/img/ligneVerticaleHaut.png")),
            this.getWidth() / 3,
            0,
            50,
            this.getHeight(),
            this);
            g.drawImage(ImageIO.read(new File("src/img/ligneVerticaleBas.png")),
            2* this.getWidth() / 3,
            0,
            50,
            this.getHeight(),
            this);
            // Tracé des lignes horizontales
            g.drawImage(ImageIO.read(new File("src/img/ligneHorizontaleGauche.png")),
            0,
            this.getHeight()/3,
            this.getWidth(),
            50,
            this);
            g.drawImage(ImageIO.read(new File("src/img/ligneHorizontaleDroite.gif")),
            0,
            2 * this.getHeight()/3,
            this.getWidth(),
            50,
            this);
            } catch (IOException ex) {
            Logger.getLogger(PlateauJeu.class.getName()).log(Level.SEVERE, null, ex);
            }
             */
            

        g.drawLine(0, this.getHeight() / 3, this.getWidth(), this.getHeight() / 3);
        g.drawLine(0, ((int) 2 * this.getHeight() / 3), this.getWidth(), ((int) 2 * this.getHeight() / 3));

        int[][] tableau = Main._grille.getTableau();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (tableau[i][j]) {
                    case -1:
                        try {
                            g.drawImage(ImageIO.read(new File("src/main/java/com/evilinc/jmorbac/img/X.png")),
                                        ((j*this.getWidth()/3)+((int)(0.1*((float)this.getWidth()/3)))),
                                        ((i*this.getHeight()/3)+((int)(0.1*((float)this.getHeight()/3)))),
                                        ((int)(0.8*((float)this.getWidth()/3))),
                                        ((int)(0.8*((float)this.getHeight()/3))),
                                        this);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            
                            g.drawImage(ImageIO.read(new File("src/main/java/com/evilinc/jmorbac/img/O.png")),
                                        ((j*this.getWidth()/3)+((int)(0.1*((float)this.getWidth()/3)))),
                                        ((i*this.getHeight()/3)+((int)(0.1*((float)this.getHeight()/3)))),
                                        ((int)(0.8*((float)this.getWidth()/3))),
                                        ((int)(0.8*((float)this.getHeight()/3))),
                                        this);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        
    }

    public void mouseClicked(MouseEvent e) {
        if (!Main._finDePartie) {
            double x = e.getX();
            double y = e.getY();

            if (x < (double) (this.getWidth() / 3)) {
                x = 0;
            } else if (x > (double) (2 * this.getWidth() / 3)) {
                x = 2;
            } else {
                x = 1;
            }

            if (y < (double) (this.getHeight() / 3)) {
                y = 0;
            } else if (y > (double) (2 * this.getHeight() / 3)) {
                y = 2;
            } else {
                y = 1;
            }

            //System.out.println("X : " + x + " Y : " + y);

            if (!Main._grille.joueCoup((int) y, (int) x)) {
                // JOptionPane.showMessageDialog(this, "Veuillez rejouer");
            }

            Main._grille.afficheGrilleConsole();
            this.repaint();

            /*if (Main._joueurDevantJouer.getTypeDeJoueur() != TypeJoueur.Humain) {
                
                Main._cpu.calculeIA(new Grille(Main._grille), 9);
            }*/
        }

    }

    public void mousePressed(MouseEvent e) {
        // System.out.println("Press");
    }

    public void mouseReleased(MouseEvent e) {
        // System.out.println("Realease");
    }

    public void mouseEntered(MouseEvent e) {
        // System.out.println("Entered");
    }

    public void mouseExited(MouseEvent e) {
        // System.out.println("Exit");
    }
}
