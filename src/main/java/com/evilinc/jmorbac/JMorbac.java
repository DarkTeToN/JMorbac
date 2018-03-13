/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac;

import com.evilinc.jmorbac.controller.GameController;
import com.evilinc.jmorbac.gui.MainFrame;

/**
 *
 * @author teton
 */
public class JMorbac {

    private static GameController gameController;
    private static MainFrame mainFrame;
    
    public static void main(String[] args) {
        mainFrame = new MainFrame();
        gameController = GameController.getInstance();
        gameController.setBoardPanel(mainFrame.getBoardPanel());
        mainFrame.setGameController(gameController);
        mainFrame.setVisible(true);
    }
    
}
