/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac.controller;

import com.evilinc.jmorbac.ai.ComputerPlayer;
import com.evilinc.jmorbac.enums.EPlayer;
import com.evilinc.jmorbac.interfaces.IGameController;
import com.evilinc.jmorbac.gui.BoardPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author teton
 */
public class GameController implements IGameController {

    private int[][] grid = new int[3][3];
    private static GameController instance;
    private EPlayer currentPlayer;
    private BoardPanel boardPanel;
    private BoardController boardController;
    private MouseAdapter mouseListener;
    private final ComputerPlayer cpuPlayer;

    private GameController() {
        currentPlayer = EPlayer.CROSS;
        cpuPlayer = new ComputerPlayer(EPlayer.CIRCLE);
    }

    private MouseAdapter getMouseListener() {
        if (mouseListener == null) {
            mouseListener = new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        int[] coordinates = boardController.getBoardCoordinates(e.getPoint());
                        if (RuleController.isMoveLegal(grid, coordinates[0], coordinates[1])) {
                            playMoveAt(coordinates[0], coordinates[1]);
                        }
                    }
                }
            };
        }
        return mouseListener;
    }

    public void playMoveAt(final int row, final int column) {
        grid[row][column] = currentPlayer.getGridPawnValue();
        updateDisplay();
        boolean gameEnded = RuleController.isGameFinished(grid);

        if (gameEnded) {
            final EPlayer winner = RuleController.getWinner(grid);
            if (winner == null) {
                JOptionPane.showMessageDialog(boardPanel, "Draw game", " End of the game", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(boardPanel, winner + " has won the game!", currentPlayer.getConsolePawnValue() + " wins", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            changePlayerTurn();
            gameEnded = false;
        }
        if (gameEnded) {
            if (boardPanel != null) {
                boardPanel.removeMouseListener(getMouseListener());
            }
        } else if (currentPlayer == EPlayer.CROSS) {
            int[] cpuMove = cpuPlayer.getMove(grid);
            playMoveAt(cpuMove[0], cpuMove[1]);
        }
    }

    private void changePlayerTurn() {
        if (currentPlayer == EPlayer.CIRCLE) {
            currentPlayer = EPlayer.CROSS;
        } else {
            currentPlayer = EPlayer.CIRCLE;
        }
    }

    private void updateDisplay() {
        if (boardController != null) {
            boardController.updateGameBoard(grid);
        }
    }

    @Override
    public void startNewGame() {
        currentPlayer = EPlayer.CROSS;
        resetGrid();
        updateDisplay();
        boardPanel.addMouseListener(getMouseListener());
        int[] cpuMove = cpuPlayer.getMove(grid);
        playMoveAt(cpuMove[0], cpuMove[1]);
    }

    private void resetGrid() {
        grid = new int[3][3];
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                grid[row][column] = 0;
            }
        }
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        boardController = new BoardController(boardPanel);
        boardPanel.addMouseListener(getMouseListener());
    }

}
