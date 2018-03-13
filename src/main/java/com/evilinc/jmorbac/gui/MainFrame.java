/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac.gui;

import com.evilinc.jmorbac.interfaces.IGameController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author teton
 */
public class MainFrame extends JFrame {

    private BoardPanel boardPanel;
    private IGameController gameController;

    public MainFrame() {
        super("JMorbac v1.0");
        initFrame();
        initComponents();
        addComponents();
    }

    private void initFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800,600));
    }

    private void initComponents() {
        boardPanel = new BoardPanel();
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    private void addComponents() {
        getContentPane().add(getMenu(), BorderLayout.NORTH);
        getContentPane().add(boardPanel, BorderLayout.CENTER);
    }

    private JMenuBar getMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(getNewGameAction());
        fileMenu.addSeparator();
        fileMenu.add(getExitAction());
        
        menuBar.add(fileMenu);

        return menuBar;
    }

    private Action getNewGameAction() {
        final Action newGameAction = new AbstractAction("New game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.startNewGame();
            }
        };
        return newGameAction;
    }

    private Action getExitAction() {
        final Action exitAction = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        return exitAction;
    }

    public void setGameController(IGameController gameController) {
        this.gameController = gameController;
    }
    
}
