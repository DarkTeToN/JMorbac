/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac.controller;

import com.evilinc.jmorbac.gui.BoardPanel;
import java.awt.Point;

/**
 *
 * @author teton
 */
public class BoardController {

    private final BoardPanel boardPanel;

    public BoardController(final BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public int[] getBoardCoordinates(final Point clickedPoint) {
        final int[] coordinates = new int[2];
        coordinates[0] = (int) Math.ceil(3 * clickedPoint.x / boardPanel.getWidth());
        coordinates[1] = (int) Math.ceil(3 * clickedPoint.y / boardPanel.getHeight());
        return coordinates;
    }
    
    public void updateGameBoard(final int[][] newGrid) {
        boardPanel.updateGameBoard(newGrid);
    }

}
