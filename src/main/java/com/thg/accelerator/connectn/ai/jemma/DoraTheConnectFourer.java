package com.thg.accelerator.connectn.ai.jemma;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thg.accelerator.connectn.ai.jemma.Moves.BasicMoves;

import java.util.List;
import java.util.Random;


public class DoraTheConnectFourer extends Player {
  public DoraTheConnectFourer(Counter counter) {
    super(counter, DoraTheConnectFourer.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    Counter dorasCounter = getCounter();
    BasicMoves moves = new BasicMoves(board, dorasCounter);
    Counter[][] counterPlacements = board.getCounterPlacements();

    List<Integer> availableColumns = moves.availableColumns(counterPlacements);

    int winningMove = moves.getWinningMove();    //winning move
    if (winningMove != -1 && availableColumns.contains(winningMove)) {
      return winningMove;
    }else{
      int blockingMove = moves.getBlockingMove();   //blocking move
      if (blockingMove != -1 && availableColumns.contains(winningMove)) {
        return blockingMove;
      }
    }

    Random random = new Random();
    int randomIndex = random.nextInt(availableColumns.size()); //random move where column is available
    return availableColumns.get(randomIndex);

  }
}
