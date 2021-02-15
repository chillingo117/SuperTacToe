package ai;

import java.util.Arrays;
import java.util.Random;

import tictactoe.GameEnvironment;

public class MonkeyAi extends Ai{
	private GameEnvironment env;
	private String symbol;
	private Random randomiser = new Random();
	
	public MonkeyAi(GameEnvironment gameEnv, String symbol) {
		env = gameEnv;
		this.symbol = symbol;
	}

	@Override
	public int[] takeTurn() {
		int x = randomiser.nextInt(3);
		int y = randomiser.nextInt(3);

		System.out.printf("Monkey %s tried:", symbol);
		System.out.printf(" (%d,%d)", x, y);

		while(!env.minorIsEmpty(x, y)) {
			x = randomiser.nextInt(3);
			y = randomiser.nextInt(3);
			System.out.printf(" (%d,%d)", x, y);
		}
		int[] coords = {x, y};
		System.out.println();
		System.out.printf("Monkey %s chose:", symbol);
		System.out.println(Arrays.toString(coords));
		return coords;
	}

	@Override
	public int[] aiChooseBoard() {
		int x = randomiser.nextInt(3);
		int y = randomiser.nextInt(3);
		System.out.printf("Monkey %s tried:", symbol);
		System.out.printf(" (%d,%d)", x, y);
		while(!env.superIsEmpty(x, y)) {

			x = randomiser.nextInt(3);
			y = randomiser.nextInt(3);
			System.out.printf(" (%d,%d)", x, y);
		}
		int[] coords = {x, y};

		System.out.println();
		System.out.printf("Monkey %s chose:", symbol);
		System.out.println(Arrays.toString(coords));
		return coords;
	}
}
