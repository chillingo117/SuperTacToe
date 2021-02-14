package ai;

import tictactoe.GameEnvironment;

public class MonkeyAi extends Ai{
	private GameEnvironment env;
	
	public MonkeyAi(GameEnvironment gameEnv) {
		env = gameEnv;
	}

	@Override
	public int[] takeTurn() {
		int x = (int) (Math.random() * (3));
		int y = (int) (Math.random() * (3));

		System.out.print("Monkey tried:");
		System.out.printf(" (%d,%d)", x, y);

		while(!env.checkMinorCoords(x, y)) {
			System.out.printf(" (%d,%d)", x, y);
			x = (int) (Math.random() * (3));
			y = (int) (Math.random() * (3));
		}
		int[] coords = {x, y};
		System.out.println();
		return coords;
	}

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
