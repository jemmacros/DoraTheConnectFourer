package com.thg.accelerator23.connectn.ai.jemma.Moves;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasicMoves {
    private Board board;
    private Counter counter;

    public BasicMoves(Board board, Counter counter) {
        this.board = board;
        this.counter = counter;
    }

    public boolean checkCenterFree(){
        Position centerPosition = new Position(4, 0);
        return board.hasCounterAtPosition(centerPosition);
    }

    public List<Integer> availableColumns(Counter[][] counterPositions){
        List<Integer> availableColumns = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            Counter counter = counterPositions[i][7];
            if(counter == null){
                availableColumns.add(i);
            }
        }
        return availableColumns;
    }

    public int randomMove(Counter[][] counterPositions){
        List<Integer> availableColumns = availableColumns(counterPositions);

        Random rand = new Random();

        int randomIndex = rand.nextInt(availableColumns.size());
        return availableColumns.get(randomIndex);
    }

    public int getWinningMove() {
        return getPotentialMove(counter);
    }

    public int getBlockingMove() {
        Counter opponentCounter = counter.getOther();
        return getPotentialMove(opponentCounter);
    }

    public int getPotentialMove(Counter targetCounter) {
        int width = board.getConfig().getWidth();
        int height = board.getConfig().getHeight();

        // Check horizontally and vertically for a potential move
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width - 3; col++) {
                if (checkRow(board, row, col, targetCounter)) {
                    return col + 3;
                }
            }
        }

        // Check diagonally (both directions) for a potential move
        for (int row = 0; row < height - 3; row++) {
            for (int col = 0; col < width - 3; col++) {
                if (checkDiagonal(board, row, col, targetCounter)) {
                    return col + 3;
                }
            }
        }

        return -1;
    }

    public boolean checkRow(Board board, int row, int startCol, Counter targetCounter) {
        for (int i = 0; i < 4; i++) {
            if (board.getCounterAtPosition(new Position(startCol + i, row)) != targetCounter) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiagonal(Board board, int startRow, int startCol, Counter targetCounter) {
        for (int i = 0; i < 4; i++) {
            if (board.getCounterAtPosition(new Position(startCol + i, startRow + i)) != targetCounter) {
                return false;
            }
        }
        return true;
    }

}
