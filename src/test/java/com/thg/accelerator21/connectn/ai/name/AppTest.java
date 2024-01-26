package com.thg.accelerator21.connectn.ai.name;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;
import com.thg.accelerator23.connectn.ai.jemma.Moves.BasicMoves;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {


  @Test
  public void testGetWinningMove() {
    //set counter placements
    Counter[][] almostWonCounters = new Counter[10][8];
    almostWonCounters[5][1] = Counter.X;
    almostWonCounters[5][2] = Counter.X;
    almostWonCounters[5][3] = Counter.X;

    // Create a sample board with a winning move for the current player (Counter.X)
    Board board = new Board(almostWonCounters, new GameConfig(10,8,4));

    // Create an instance of the class that contains the methods
    BasicMoves moves = new BasicMoves(board, Counter.X);

    // Call the getWinningMove method
    int winningMove = moves.getWinningMove();

    // Validate the result
    assertEquals( 4, winningMove);
  }
}

