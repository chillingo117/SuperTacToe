package ai;

import java.util.ArrayList;

import tictactoe.GameEnvironment;

/**
 * Monkey AI was inspired by the monkey sort (otherwise known as Bogo sort).
 * It functions off of playing randomly. Completely randomly.
 * @author kiwit
 *
 */
public class FasterMonkeyAi extends Ai{
	private GameEnvironment env;
	
	//Monkey ai is constructed with a GameEnvironment as an input. 
	public FasterMonkeyAi(GameEnvironment gameEnv) {
		env = gameEnv;
	}

	/**
	 * The monkey AI makes a turn by choosing a random coord to play on.
	 * If that coord is invalid, it tries again. 
	 * @return a random, but valid, xy coord
	 */
	@Override
	public int[] takeTurn() {
		ArrayList<int[]> tried = new ArrayList<int[]>();
		
		int x = (int) (Math.random() * (3));
		int y = (int) (Math.random() * (3));
		int[] pair = {x, y};
		tried.add(pair);

		System.out.print("Monkey tried:");
		System.out.printf(" (%d,%d)", x, y);

		while(!env.checkMinorCoords(x, y)) {
			while (tried.contains(pair)) {
				x = (int) (Math.random() * (3));
				y = (int) (Math.random() * (3));
			}

			System.out.printf(" (%d,%d)", x, y);

		}
		int[] coords = {x, y};
		System.out.println();
		return coords;
	}

	/**
	 * In a similar fashion to takeTurn, this function make the monkey AI choose a random valid board.
	 * @return a random, but valid, xy coord for choosing a new minorBoard.
	 */
	@Override
	public int[] aiChooseBoard() {
		int x = (int) (Math.random() * (3));
		int y = (int) (Math.random() * (3));

		while(!env.checkSuperCoords(x, y)) {
			System.out.printf("%d,%d", x, y);

			x = (int) (Math.random() * (3));
			y = (int) (Math.random() * (3));
		}
		int[] coords = {x, y};
		return coords;
	}
}
