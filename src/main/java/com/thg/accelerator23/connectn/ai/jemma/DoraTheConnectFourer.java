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
    Counter[][] counterPlacements = board.getCounterPlacements();
    com.thg.accelerator23.connectn.ai.jemma.Moves.BasicMoves moves = new BasicMoves(board, dorasCounter, counterPlacements);

    if(moves.checkCenterFree()){  //go for center if free
      return 4;
    }

    return moves.randomMove();
  }
}
