package com.thg.accelerator23.connectn.ai.jemma.Moves;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Position;

import java.util.ArrayList;
import java.util.List;

public class BasicMoves {
    private Board board;
    private Counter counter;

    public BasicMoves(Board board, Counter counter) {
        this.board = board;
        this.counter = counter;
    }

    public boolean checkCenterFree(){
        Position centerPosition = new Position(5, 0);
        boolean centerCounterTaken = board.hasCounterAtPosition(centerPosition);
        return centerCounterTaken;
    }

//    public List<Integer> availableColumns(){
//        List<Integer> availableColumns = new ArrayList<>();
//        Counter[][] counters = board.
//        for (int i = 0; i < 10; i++) {
//            if (i == 0 || board.getCounterPlacements[x][i - 1] != null) {
//                return i;
//            }
//
//        }
//    }
}
