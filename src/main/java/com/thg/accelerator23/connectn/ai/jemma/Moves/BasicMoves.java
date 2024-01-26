package com.thg.accelerator23.connectn.ai.jemma.Moves;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasicMoves {
    Board board;
    Counter dorasCounter;
    Counter opponentCounter;
    Counter[][] counterPositions;


    public BasicMoves(Board board, Counter dorasCounter, Counter[][] counterPositions) {
        this.board = board;
        this.dorasCounter = dorasCounter;
        this.counterPositions = counterPositions;
        this.opponentCounter = dorasCounter.getOther();
    }

    public boolean checkCenterFree() {
        Position centerPosition = new Position(4, 0);
        return !board.hasCounterAtPosition(centerPosition);
    }

    public List<Integer> availableColumns() {
        List<Integer> availableColumns = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            Counter counter = counterPositions[i][7];
            if (counter == null) {
                availableColumns.add(i);
            }
        }
        return availableColumns;
    }

    public int randomMove() {
        List<Integer> availableColumns = availableColumns();

        Random rand = new Random();

        int randomIndex = rand.nextInt(availableColumns.size());
        return availableColumns.get(randomIndex);
    }

    public int checkDiagonal(Counter counter){
        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 8; row++) {
                if (counterPositions[row][col] != null &&
                        counterPositions[row][col].equals(counter) &&
                        counterPositions[row + 1][col + 1] != null &&
                        counterPositions[row + 1][col + 1].equals(counter) &&
                        counterPositions[row + 2][col + 2] != null &&
                        counterPositions[row + 2][col + 2].equals(counter) &&
                        counterPositions[row + 3][col + 3] == null &&
                        counterPositions[row + 3][col + 2] != null) {
                    return col + 3;
                }
            }
        }
        return -1;
    }

    public int checkHorizontal(Counter counter){
        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 8; row++) {
                if (counterPositions[row][col] != null &&
                        counterPositions[row][col].equals(counter) &&
                        counterPositions[row][col + 1] != null &&
                        counterPositions[row][col + 1].equals(counter) &&
                        counterPositions[row][col + 2] != null &&
                        counterPositions[row][col + 2].equals(counter) &&
                        counterPositions[row][col + 3] == null){
                    return col + 3;
                }
            }
        }
        return -1;
    }

    public int checkVertical(Counter counter){
        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 8; row++) {
                if (counterPositions[row][col] != null &&
                    counterPositions[row][col].equals(counter) &&
                    counterPositions[row + 1][col] != null &&
                    counterPositions[row + 1][col].equals(counter) &&
                    counterPositions[row + 2][col] != null &&
                    counterPositions[row + 2][col].equals(counter) &&
                    counterPositions[row + 3][col] == null){
                    return col;
                }
            }
        }
        return -1;
    }

    public int movePosition(Counter counter){
        int vertical = checkVertical(counter);
        int horizontal = checkHorizontal(counter);
        int diagonal = checkDiagonal(counter);

        if(vertical != -1 && vertical < 10){
            return vertical;
        }else if(horizontal != -1 && horizontal < 10){
            return horizontal;
        }else if(diagonal != -1 && diagonal <10){
            return diagonal;
        }

        return -1;
    }

    public int makeMove(){
        if(movePosition(dorasCounter) != -1){
            return movePosition(dorasCounter); //win move
        }else if(movePosition(opponentCounter) != -1){
            return movePosition(opponentCounter); //block move
        }
        return -1;
    }

}