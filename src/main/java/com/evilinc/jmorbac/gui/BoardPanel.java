/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author teton
 */
public class BoardPanel extends JPanel {

    private int[][] gameBoard;

    public BoardPanel() {
        initPanel();
    }
    
    private void initPanel() {
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the lines
        g.drawLine(this.getWidth() / 3, 0, this.getWidth() / 3, this.getHeight());
        g.drawLine(((int) (2 * this.getWidth() / 3)), 0, ((int) (2 * this.getWidth() / 3)), this.getHeight());
        g.drawLine(0, this.getHeight() / 3, this.getWidth(), this.getHeight() / 3);
        g.drawLine(0, ((int) 2 * this.getHeight() / 3), this.getWidth(), ((int) 2 * this.getHeight() / 3));

        if (gameBoard != null) {
            drawPawns(g);
        }

    }

    private void drawPawns(final Graphics g) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (gameBoard[j][i]) {
                    case -1:
                        try {
                            g.drawImage(ImageIO.read(new File("src/main/java/com/evilinc/jmorbac/img/X.png")),
                                    ((j * this.getWidth() / 3) + ((int) (0.1 * ((float) this.getWidth() / 3)))),
                                    ((i * this.getHeight() / 3) + ((int) (0.1 * ((float) this.getHeight() / 3)))),
                                    ((int) (0.8 * ((float) this.getWidth() / 3))),
                                    ((int) (0.8 * ((float) this.getHeight() / 3))),
                                    this);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 1:
                        try {

                            g.drawImage(ImageIO.read(new File("src/main/java/com/evilinc/jmorbac/img/O.png")),
                                    ((j * this.getWidth() / 3) + ((int) (0.1 * ((float) this.getWidth() / 3)))),
                                    ((i * this.getHeight() / 3) + ((int) (0.1 * ((float) this.getHeight() / 3)))),
                                    ((int) (0.8 * ((float) this.getWidth() / 3))),
                                    ((int) (0.8 * ((float) this.getHeight() / 3))),
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

    public void updateGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
        repaint();
    }
}
