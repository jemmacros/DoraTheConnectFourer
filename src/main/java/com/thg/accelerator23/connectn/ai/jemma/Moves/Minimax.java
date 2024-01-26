package com.thg.accelerator23.connectn.ai.jemma.Moves;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.InvalidMoveException;

public class Minimax {
    private final int ROWS = 10;
    private final int COLS = 8;
    private  final int CONNECT = 4;
    private  final int PLAYER = 1;
    private  final int AI_PLAYER = 2;
    private  Integer MAX_DEPTH = 5;

    private int minimax(Board board, int depth, int alpha, int beta, boolean isMaximizingPlayer, Counter counter) throws InvalidMoveException {
        if (depth == MAX_DEPTH) {
            return evaluate(board);
        }

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int col = 0; col < COLS; col++) {
                if (isValidMove(board, col)) {
                    Board newBoard = new Board(board, col, counter);
                    int eval = minimax(newBoard, depth - 1, alpha, beta, false, counter);
                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, eval);
                    if (beta <= alpha) {
                        break; // Beta cut-off
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int col = 0; col < COLS; col++) {
                if (isValidMove(board, col)) {
                    Board newBoard = new Board(board, col, counter);
                    int eval = minimax(newBoard, depth - 1, alpha, beta, true, counter);
                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, eval);
                    if (beta <= alpha) {
                        break; // Alpha cut-off
                    }
                }
            }
            return minEval;
        }
    }

    private static int evaluate(Board board) {
        int score = 0;

        // Evaluate rows
        score += evaluateDirection(board, 1, 0);

        // Evaluate columns
        score += evaluateDirection(board, 0, 1);

        // Evaluate diagonals
        score += evaluateDirection(board, 1, 1);
        score += evaluateDirection(board, 1, -1);

        return score;
    }

    private int evaluateDirection(Board board, int rowChange, int colChange) {
        int score = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int endRow = row + (CONNECT - 1) * rowChange;
                int endCol = col + (CONNECT - 1) * colChange;
                if (isValidCell(endRow, endCol)) {
                    int countPlayer = 0;
                    int countAI = 0;
                    for (int i = 0; i < CONNECT; i++) {
                        int currentCell = board[row + i * rowChange][col + i * colChange];
                        if (currentCell == PLAYER) {
                            countPlayer++;
                        } else if (currentCell == AI_PLAYER) {
                            countAI++;
                        }
                    }
                    score += evaluateLine(countPlayer, countAI);
                }
            }
        }
        return score;
    }

    private static int evaluateLine(int countPlayer, int countAI) {
        int score = 0;
        if (countPlayer == 0) {
            score = (int) Math.pow(10, countAI);
        } else if (countAI == 0) {
            score = - (int) Math.pow(10, countPlayer);
        }
        return score;
    }

    private static boolean isValidCell(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

    private static boolean isGameOver(Board board) {
        // Implement your game over condition here
        return false;
    }

    private static boolean isValidMove(Board board, int col) {
        // Implement your valid move condition here
        return true;
    }


    private static void printBoard(int[][] board) {
        for (int row = ROWS - 1; row >= 0; row--) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
