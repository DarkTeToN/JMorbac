/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac.controller;

import com.evilinc.jmorbac.enums.EPlayer;

/**
 *
 * @author teton
 */
public class RuleController {

    public static boolean isMoveLegal(final int[][] board, final int row, final int column) {
        if (isGameFinished(board)) {
            return false;
        }

        if (row < 0 || row > 2) {
            return false;
        }

        if (column < 0 || column > 2) {
            return false;
        }

        return board[row][column] == 0;
    }

    public static boolean isGameFinished(final int[][] board) {
        final EPlayer winner = getWinner(board);
        if (winner == null) {
            for (int[] row : board) {
                for (int square : row) {
                    if (square == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static int[][] playMoveAt(final int[][] board, final EPlayer currentPlayer, final int row, final int column) {
        if (isMoveLegal(board, row, column)) {
            board[row][column] = currentPlayer.getGridPawnValue();
        }
        return board;
    }

    public static int[][] cancelMove(final int[][] board, final int row, final int column) {
        board[row][column] = 0;
        return board;
    }

    public static EPlayer getWinner(final int[][] board) {
        final int result = getResult(board);
        if (result == 3) {
            return EPlayer.CIRCLE;
        } else if (result == -3) {
            return EPlayer.CROSS;
        }
        return null;
    }

    public static int getResult(final int[][] board) {

        int resultatTemp = 0;

        // On vérifie la première ligne
        resultatTemp = board[0][0] + board[0][1] + board[0][2];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return resultatTemp;
        }

        // On vérifie la deuxième ligne
        resultatTemp = board[1][0] + board[1][1] + board[1][2];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return resultatTemp;
        }

        // On vérifie la troisième ligne
        resultatTemp = board[2][0] + board[2][1] + board[2][2];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return resultatTemp;
        }

        // On vérifie la première colonne
        resultatTemp = board[0][0] + board[1][0] + board[2][0];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return resultatTemp;
        }

        // On vérifie la deuxième colonne
        resultatTemp = board[0][1] + board[1][1] + board[2][1];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return resultatTemp;
        }

        // On vérifie la troisième colonne
        resultatTemp = board[0][2] + board[1][2] + board[2][2];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return resultatTemp;
        }

        // On vérifie la première diagonale (de haut-gauche à bas-droite)
        resultatTemp = board[0][0] + board[1][1] + board[2][2];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return resultatTemp;
        }
        // On vérifie la deuxième diagonale (de bas-gauche à haut-droite)
        resultatTemp = board[0][2] + board[1][1] + board[2][0];
        if (resultatTemp == 3 || resultatTemp == -3) {
            return resultatTemp;
        }

        return 0;
    }

}
