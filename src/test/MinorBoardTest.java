package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tictactoe.MinorTacBoard;


class MinorBoardTest {

	private MinorTacBoard testBoard;
	private String[] blankRow = {" ", " ", " "};
	
	@BeforeEach
	public void initialise()
	{
		testBoard = new MinorTacBoard();
	}
	/**
	 * The following tests test:
	 * board wins on rows, cols, and diags
	 * implicitly tests marking
	 * 
	 * Does NOT test:
	 * transform
	 */
	@Test
	void noWin() {
		String[] row2 = {"X"," "," "};
		String[] row1 = {"X"," "," "};
		String[] row0 = {" "," "," "};
		testBoard.customState(row2, row1, row0);
		testBoard.mark("O", 0, 0);
		assertEquals(" ", testBoard.getWinner());
		assertEquals(false, testBoard.isFinished());
	}
	
	@Test
	void noWin2() {
		String[] row2 = {"X","O","X"};
		String[] row1 = {"X","X","O"};
		String[] row0 = {" ","X","O"};
		testBoard.customState(row2, row1, row0);
		testBoard.mark("O", 0, 0);
		assertEquals(" ", testBoard.getWinner());
		assertEquals(true, testBoard.isFinished());
	}
	
	@Test
	void rowWin() {
		String[] row2 = {"X","X"," "};
		testBoard.customState(row2, blankRow, blankRow);
		testBoard.mark("X", 2, 2);
		assertEquals("X", testBoard.getWinner());
		assertEquals(true, testBoard.isFinished());
	}
	
	@Test
	void colWin() {
		String[] row2 = {"O"," "," "};
		String[] row1 = {"O"," "," "};
		String[] row0 = {" "," "," "};
		testBoard.customState(row2, row1, row0);
		testBoard.mark("O", 0, 0);
		assertEquals("O", testBoard.getWinner());
		assertEquals(true, testBoard.isFinished());
	}

	@Test
	void diagWin() {
		String[] row2 = {"X"," "," "};
		String[] row1 = {" ","X"," "};
		String[] row0 = {" "," "," "};
		testBoard.customState(row2, row1, row0);
		testBoard.mark("X", 2, 0);
		assertEquals("X", testBoard.getWinner());
		assertEquals(true, testBoard.isFinished());
	}
	
	@Test
	void draw() {
		String[] row2 = {"X","O","O"};
		String[] row1 = {"O","X","X"};
		String[] row0 = {"X","O"," "};
		testBoard.customState(row2, row1, row0);
		testBoard.mark("O", 2, 0);
		assertEquals(" ", testBoard.getWinner());
		assertEquals(true, testBoard.isFinished());
	}
}
