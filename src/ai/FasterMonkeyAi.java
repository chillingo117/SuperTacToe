package ai;

import java.util.ArrayList;
import java.util.Random;

import tictactoe.GameEnvironment;

/**
 * Monkey AI was inspired by the monkey sort (otherwise known as Bogo sort).
 * It functions off of playing randomly. Completely randomly.
 * @author kiwit
 *
 */
public class FasterMonkeyAi extends Ai{
	private GameEnvironment env;
	private Random randomiser = new Random();
	
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
		
		int x = randomiser.nextInt(3);
		int y = randomiser.nextInt(3);
		int[] pair = {x, y};
		tried.add(pair);

		System.out.print("Monkey tried:");
		System.out.printf(" (%d,%d)", x, y);

		while(!env.minorIsEmpty(x, y)) {
			while (tried.contains(pair)) {
				x = randomiser.nextInt() * (3);
				y = randomiser.nextInt() * (3);
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
		int x = randomiser.nextInt() * (3);
		int y = randomiser.nextInt() * (3);

		while(!env.superIsEmpty(x, y)) {
			System.out.printf("%d,%d", x, y);

			x = randomiser.nextInt() * (3);
			y = randomiser.nextInt() * (3);
		}
		int[] coords = {x, y};
		return coords;
	}
}
