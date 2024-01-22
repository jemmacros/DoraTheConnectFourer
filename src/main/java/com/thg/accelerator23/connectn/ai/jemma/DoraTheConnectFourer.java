package com.thg.accelerator23.connectn.ai.jemma;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;

import java.util.Random;


public class DoraTheConnectFourer extends Player {
  public DoraTheConnectFourer(Counter counter) {
    super(counter, DoraTheConnectFourer.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    Random rand = new Random();
    int int_random = rand.nextInt(10);
    return int_random;
  }
}
