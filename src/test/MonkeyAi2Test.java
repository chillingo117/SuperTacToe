package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ai.MonkeyAi2;
import tictactoe.*;


class MonkeyAi2Test {

	private GameEnvironment env;
    private MonkeyAi2 m2;
	
	@BeforeEach
	public void initialise()
	{
		env = new GameEnvironment();
        m2 = new MonkeyAi2(env, "X");
	}
	/**
	 * The following tests test:
     * blocking
	 * winning
	 * 
	 * moves is implicit
	 */
	@Test
	void blockingTest() {
		System.out.println("MonkeyAi2 test blocking");
        String[] row2 = {"O"," "," "};
		String[] row1 = {"O"," "," "};
		String[] row0 = {" "," "," "};
		int[] correctMove = {0, 0};
		env.getCurrentMinorBoard().customState(row2, row1, row0);
        assertEquals(Arrays.equals(m2.takeTurn(), correctMove), true);
	}
	@Test
	void winTest() {
		System.out.println("MonkeyAi2 test winning");

        String[] row2 = {"X"," "," "};
		String[] row1 = {"X"," "," "};
		String[] row0 = {" "," "," "};
		int[] correctMove = {0, 0};
		env.getCurrentMinorBoard().customState(row2, row1, row0);
        assertEquals(Arrays.equals(m2.takeTurn(), correctMove), true);
	}
	

}
