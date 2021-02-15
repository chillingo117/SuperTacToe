package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tictactoe.SuperBoard;

class SuperBoardTest {
	
	private SuperBoard testBoard;
	@BeforeEach
	public void initialise()
	{
		testBoard = new SuperBoard();
		String[] row2 = {"X","O","X"};
		String[] row1 = {"X","O","O"};
		String[] row0 = {" ","X","X"};
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				testBoard.getTrueBoard()[i][j].customState(row2, row1, row0);
			}
		}
	}

	@Test
	void noWin() {
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				testBoard.getTrueBoard()[i][j].mark("O", 0, 0);
			}
		}
		int[] temp = {1,1};
		assertEquals(false, testBoard.checkWin("O", temp));
		assertEquals(true, testBoard.checkFull());
	}
}
