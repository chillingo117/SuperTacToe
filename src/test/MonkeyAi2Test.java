package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ai.MonkeyAi2;
import tictactoe.*;


class MonkeyAi2Test {

	private GameEnvironment env;
	private MinorBoard board;
    private MonkeyAi2 m2X;
    private MonkeyAi2 m2O;
	private String[] blankRow = {" ", " ", " "};
	
	@BeforeEach
	public void initialise()
	{
		env = new GameEnvironment();
		board = env.getCurrentMinorBoard();
        m2X = new MonkeyAi2(env, "X");
        m2O = new MonkeyAi2(env, "O");
	}
	/**
	 * The following tests test:
     * 
	 */
	@Test
	void blockingTest() {
        String[] row2 = {"X"," "," "};
		String[] row1 = {"X"," "," "};
		String[] row0 = {" "," "," "};
		int[] correctMove = {0, 0};
		env.getCurrentMinorBoard().customState(row2, row1, row0);
        assertEquals(m2X.takeTurn(), correctMove);
	}

}
