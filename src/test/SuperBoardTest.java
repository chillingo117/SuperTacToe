package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import tictactoe.BoardPrinter;
import tictactoe.SuperBoard;

class SuperBoardTest {
	
	private SuperBoard testBoard;
	//private BoardPrinter printer;
	@BeforeEach
	public void initialise()
	{
		//printer = new BoardPrinter();
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
//
//	@Test
//	void colWin() {
//		for (int i = 0; i < 3; i ++) {
//			testBoard.getTrueBoard()[i][0].mark("X", 0, 0);
//		}
//		int[] temp = {0,0};
//		assertEquals(true, testBoard.checkWin("X", temp));
//		assertEquals(false, testBoard.checkFull());
//	}
//	
//	@Test
//	void rowWin() {
//		for (int i = 0; i < 3; i ++) {
//			testBoard.getTrueBoard()[0][i].mark("X", 0, 0);
//		}
//		int[] temp = {0,0};
//
//		assertEquals(true, testBoard.checkWin("X", temp));
//		assertEquals(false, testBoard.checkFull());
//	}
//	
//	@Test
//	void diagWin() {
//		for (int i = 0; i < 3; i ++) {
//			testBoard.getTrueBoard()[i][i].mark("X", 0, 0);
//		}
//		int[] temp = {1,1};
//
//		assertEquals(true, testBoard.checkWin("X", temp));
//		assertEquals(false, testBoard.checkFull());
//	}
//	
	
}
