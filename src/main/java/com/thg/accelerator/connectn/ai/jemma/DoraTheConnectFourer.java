package com.thg.accelerator.connectn.ai.jemma;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thg.accelerator.connectn.ai.jemma.Moves.BasicMoves;

import java.util.Random;


public class DoraTheConnectFourer extends Player {
  public DoraTheConnectFourer(Counter counter) {
    super(counter, DoraTheConnectFourer.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    Counter dorasCounter = getCounter();
    BasicMoves basicMoves = new BasicMoves(board, dorasCounter);
    Counter[][] counterPositions = board.getCounterPlacements();


    if(basicMoves.checkCenterFree()) {
      return 4;
    }else{
      return basicMoves.randomMove(counterPositions);
    }
  }
}
