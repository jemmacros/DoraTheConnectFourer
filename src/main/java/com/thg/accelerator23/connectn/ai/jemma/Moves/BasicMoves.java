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

    public boolean checkCenterFree() {
        Position centerPosition = new Position(4, 0);
        return !board.hasCounterAtPosition(centerPosition);
    }

    public List<Integer> availableColumns(Counter[][] counterPositions) {
        List<Integer> availableColumns = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            Counter counter = counterPositions[i][7];
            if (counter == null) {
                availableColumns.add(i);
            }
        }
        return availableColumns;
    }

    public int randomMove(Counter[][] counterPositions) {
        List<Integer> availableColumns = availableColumns(counterPositions);

        Random rand = new Random();

        int randomIndex = rand.nextInt(availableColumns.size());
        return availableColumns.get(randomIndex);
    }

    public int getMove() {
        Counter opponentCounter = counter.getOther();
        int width = board.getConfig().getWidth();
        int height = board.getConfig().getHeight();

        // Check horizontally, vertically, and diagonally
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width - 3; col++) {
                if (checkSequence(board, row, col, 1, 0, counter, false)) {
                    return col + 3; // Win
                } else if (checkSequence(board, row, col, 1, 0, opponentCounter, true)) {
                    return col + 3; // Block
                }
            }
        }

        for (int row = 0; row < height - 3; row++) {
            for (int col = 0; col < width; col++) {
                if (checkSequence(board, row, col, 0, 1, counter, false)) {
                    return col + 3; // Win
                } else if (checkSequence(board, row, col, 0, 1, opponentCounter, true)) {
                    return col + 3; // Block
                }
            }
        }

        for (int row = 0; row < height - 3; row++) {
            for (int col = 0; col < width - 3; col++) {
                if (checkSequence(board, row, col, 1, 1, counter, false)) {
                    return col + 3; // Win
                } else if (checkSequence(board, row, col, 1, 1, opponentCounter, true)) {
                    return col + 3; // Block
                }
                if (checkSequence(board, row, col + 3, 1, -1, counter, false)) {
                    return col + 3; // Win
                } else if (checkSequence(board, row, col + 3, 1, -1, opponentCounter, true)) {
                    return col + 3; // Block
                }
            }
        }

        return -1;
    }

    private boolean checkSequence(Board board, int startRow, int startCol, int rowChange, int colChange, Counter targetCounter, boolean isBlocking) {
        for (int i = 0; i < 4; i++) {
            int newRow = startRow + i * rowChange;
            int newCol = startCol + i * colChange;

            if (board.getCounterAtPosition(new Position(newCol, newRow)) != targetCounter) {
                if (isBlocking && newRow - rowChange >= 0 && board.getCounterAtPosition(new Position(newCol, newRow - rowChange))==null) {
                    return true; // Block
                }
                return false;
            }
        }
        return true;
    }
}