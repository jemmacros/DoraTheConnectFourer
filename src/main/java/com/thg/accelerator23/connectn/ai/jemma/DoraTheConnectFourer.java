package com.thg.accelerator23.connectn.ai.jemma;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thg.accelerator23.connectn.ai.jemma.Moves.BasicMoves;

import java.util.Random;


public class DoraTheConnectFourer extends Player {
  public DoraTheConnectFourer(Counter counter) {
    super(counter, DoraTheConnectFourer.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    Counter dorasCounter = getCounter();
    BasicMoves basicMoves = new BasicMoves(board, dorasCounter);

    if(basicMoves.checkCenterFree()){
      return 4;
    }else{
      Random rand = new Random();
      int int_random = rand.nextInt(10);
      return int_random;
    }
  }
}
