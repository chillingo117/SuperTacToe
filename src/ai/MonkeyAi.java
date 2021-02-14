package ai;

import java.util.Random;

import tictactoe.GameEnvironment;

public class MonkeyAi extends Ai{
	private GameEnvironment env;
	private Random randomiser = new Random();
	
	public MonkeyAi(GameEnvironment gameEnv) {
		env = gameEnv;
	}

	@Override
	public int[] takeTurn() {
		int x = randomiser.nextInt() * (3);
		int y = randomiser.nextInt() * (3);

		System.out.print("Monkey tried:");
		System.out.printf(" (%d,%d)", x, y);

		while(!env.minorIsEmpty(x, y)) {
			System.out.printf(" (%d,%d)", x, y);
			x = randomiser.nextInt() * (3);
			y = randomiser.nextInt() * (3);
		}
		int[] coords = {x, y};
		System.out.println();
		return coords;
	}

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
