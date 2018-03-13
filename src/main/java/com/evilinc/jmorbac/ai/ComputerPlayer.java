/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac.ai;

import com.evilinc.jmorbac.controller.RuleController;
import com.evilinc.jmorbac.enums.EPlayer;

/**
 *
 * @author TeToN
 */
public class ComputerPlayer {

    private final EPlayer player;
    private final EPlayer opponent;
    private final int proof;
    final private int _MIN_EVAL = -100000;
    final private int _MAX_EVAL = 100000;

    /*
     * Constructeurs
     */
    public ComputerPlayer(final EPlayer player) {
        this.proof = 9;
        this.player = player;
        if (player == EPlayer.CIRCLE) {
            opponent = EPlayer.CROSS;
        } else {
            opponent = EPlayer.CIRCLE;
        }
    }

    /*
     * Fonction qui calcule le prochain coup
     */
    public int[] getMove(int[][] grid) {
        int tmp;
        int max = _MIN_EVAL;
        int maxi = -1;
        int maxj = -1;

        //Si la _profondeur est nulle ou la partie est finie,
        //on ne fait pas le calcul
        //On parcourt les cases du morpion
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                //Si la case est vide
                if (RuleController.isMoveLegal(grid, row, column)) {
                    grid = RuleController.playMoveAt(grid, player, row, column);
                    tmp = calculateMin(grid, proof - 1);
                    if (tmp > max) {
                        //On le choisit
                        max = tmp;
                        maxi = row;
                        maxj = column;
                    }
                    grid = RuleController.cancelMove(grid, row, column);
                }
            }
        }
        return new int[]{maxi, maxj};
    }

    /*
     * Fonctions pour le calcul 
     */
    public int calculateMin(int[][] board, int proof) {
        int temp;
        int min = _MAX_EVAL;

        //Si on est à la profondeur voulue, on retourne l'évaluation
        if (proof == 0 || RuleController.isGameFinished(board)) {
            return evaluatePosition(board);
        }

        //On parcourt le plateau de jeu et on le joue si la case est vide
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (RuleController.isMoveLegal(board, row, column)) {
                    board[row][column] = opponent.getGridPawnValue();
                    temp = calculateMax(board, proof - 1);
                    if (temp < min) {
                        min = temp;
                    }
                    RuleController.cancelMove(board, row, column);
                }
            }
        }
        return min;
    }

    public int calculateMax(int[][] board, int proof) {
        int tmp;
        int max = _MIN_EVAL;

        //Si on est à la profondeur voulue, on retourne l'évaluation
        if (proof == 0 || RuleController.isGameFinished(board)) {
            return evaluatePosition(board);
        }

        //On parcourt le plateau de jeu et on le joue si la case est vide
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (RuleController.isMoveLegal(board, row, column)) {
                    //On joue le coup
                    board[row][column] = player.getGridPawnValue();
                    tmp = calculateMin(board, proof - 1);
                    if (tmp > max) {
                        max = tmp;
                    }
                    //On annule le coup
                    RuleController.cancelMove(board, row, column);
                }
            }
        }
        return max;
    }

    /*
     * Fonctions qui évaluent le jeu
     */
    /**
     * ***********************************
     * * Fonction calculeScore(int, int) * ***********************************
     * Pour évaluer une configuration, le programme va regarder le nombre de
     * pions et le nombre de pions de l'ComputerPlayer moins le nombre de pions
     * de l'adversaire pour chaque horizontale, verticale et diagonale. Grâce à
     * ces deux compteurs, nous pourrons définir ce qu'il faut pour calculer la
     * participation à la somme de l'évaluation.
     */
    public int calculeScore(int cntpions, int cntjoueur) {
//
//        // On regarde le nombre de pions
//        switch (cntpions) {
//            case 1:
//                return (10*cntjoueur);
//            case 2:
//                return (30*cntjoueur);
//            default:
//                return 0;
//        }
        return 0;
    }

    public int evaluatePosition(int[][] boardToEvaluate) {
        final int numberOfPawns = getNumberOfPawnsOnTheBoard(boardToEvaluate);
        if (RuleController.isGameFinished(boardToEvaluate)) {
            final EPlayer winner = RuleController.getWinner(boardToEvaluate);
            if (winner == player) {
                return 1000 - numberOfPawns;
            } else if (winner == opponent) {
                return -1000 + numberOfPawns;
            } else {
                return 0;
            }
        }
        final int cpuPlayerSerie = getNumberOfSeries(boardToEvaluate, player);
        final int opponentPlayerSerie = getNumberOfSeries(boardToEvaluate, opponent);
        return cpuPlayerSerie - opponentPlayerSerie;
    }

    private int getNumberOfPawnsOnTheBoard(final int[][] board) {
        int numberOfPawns = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] != 0) {
                    numberOfPawns++;
                }
            }
        }
        return numberOfPawns;
    }

    private int getNumberOfSeries(final int[][] board, final EPlayer player) {
        int counter = 0;
        int series = 0;

        // Checking diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][i] == player.getGridPawnValue()) {
                counter++;
                if (counter == 2) {
                    series++;
                }
            } else {
                counter = 0;
            }
        }

        counter = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][2 - i] == player.getGridPawnValue()) {
                counter++;
                if (counter == 2) {
                    series++;
                }
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < 3; i++) {

            // Checking horizontal lines
            counter = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == player.getGridPawnValue()) {
                    counter++;
                    if (counter == 2) {
                        series++;
                    }
                } else {
                    counter = 0;
                }
            }

            // Checking vertical lines
            counter = 0;
            for (int j = 0; j < 3; j++) {
                if (board[j][i] == player.getGridPawnValue()) {
                    counter++;
                    if (counter == 2) {
                        series++;
                    }
                } else {
                    counter = 0;
                }
            }

        }
        return series;
    }
}
