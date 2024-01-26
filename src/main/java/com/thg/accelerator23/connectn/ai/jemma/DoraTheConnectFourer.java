package com.thg.accelerator23.connectn.ai.jemma;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thg.accelerator23.connectn.ai.jemma.Moves.BasicMoves;

import java.util.List;
import java.util.Random;


public class DoraTheConnectFourer extends Player {
  public DoraTheConnectFourer(Counter counter) {
    super(counter, DoraTheConnectFourer.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    Counter dorasCounter = getCounter();
    com.thg.accelerator23.connectn.ai.jemma.Moves.BasicMoves moves = new BasicMoves(board, dorasCounter);
    Counter[][] counterPlacements = board.getCounterPlacements();

    List<Integer> availableColumns = moves.availableColumns(counterPlacements);

    int winningMove = moves.getWinningMove();//winning move
    int blockingMove = moves.getBlockingMove();//blocking move
    if(moves.checkCenterFree()){  //go for center if free
      return 4;
    } else if (winningMove != -1 && availableColumns.contains(winningMove)) {
      return winningMove;
    } else if (blockingMove != -1 && availableColumns.contains(blockingMove)) {
        return blockingMove;
    }

    int randomColumn = moves.randomMove(counterPlacements);
    return randomColumn;
  }
}
