package com.thg.accelerator.connectn.ai.jemma.Moves;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Position;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;
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
        boolean centerCounterTaken = board.hasCounterAtPosition(centerPosition);
        return centerCounterTaken;
    }

    public List<Integer> availableColumns(Counter[][] counterPositions){
        List<Integer> availableColumns = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            Counter counter = counterPositions[i][7];
            if(counter.getStringRepresentation() != "X" && counter.getStringRepresentation() != "0"){
                availableColumns.add(i);
            }
        }
        return availableColumns;
    }

    public int randomMove(Counter[][] counterPositions){
        List<Integer> availableColumns = availableColumns(counterPositions);

        Random rand = new Random();

        int randomIndex = rand.nextInt(availableColumns.size());
        int randomColumn = availableColumns.get(randomIndex);
        return randomColumn;
    }
}
