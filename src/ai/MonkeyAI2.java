package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tictactoe.GameEnvironment;
import tictactoe.MinorBoard;

;public class MonkeyAI2 extends Ai{
    private GameEnvironment env;
	private Random randomiser = new Random();
    private MinorBoard visionBoard = new MinorBoard();
    private String symbol;
	
	public MonkeyAI2(GameEnvironment gameEnv, String symbol) {
		env = gameEnv;
        this.symbol = symbol;
	}

    private void updateBoard() {
        String[][] boardState = env.getCurrentMinorBoard().getBoard();
        visionBoard.customState(boardState[2], boardState[1], boardState[0]);
    }

	@Override
	public int[] takeTurn() {

        ArrayList<int[]> winningMoves = new ArrayList<>();
        ArrayList<int[]> blockingMoves = new ArrayList<>();
        ArrayList<int[]> moves = new ArrayList<>();

        Boolean winnable = false;
        Boolean blockable = false;

        int x = -1;
        int y = -1;
        int[] move = {x, y};

        for (x = 0; x < 3; x++) {
            for (y= 0; y < 3; y++) {
                updateBoard();

                move[0] = x;
                move[1] = y;
                if (visionBoard.validateCoords(x, y)){
                    visionBoard.mark(symbol, x, y);
                    if (visionBoard.isFinished()){
                        blockingMoves.add(Arrays.copyOf(move, 2));
                        blockable = true;
                        if (visionBoard.getWinner().equals(symbol)) {
                            winningMoves.add(Arrays.copyOf(move, 2));
                            winnable = true;
                        }
                    } else {
                        moves.add(Arrays.copyOf(move, 2));
                    }
                }
            }
        }

        int index;

        if(winnable){
            index = randomiser.nextInt(winningMoves.size());
            return (winningMoves.get(index));
        } else if (blockable) {
            index = randomiser.nextInt(blockingMoves.size());
            return (blockingMoves.get(index));
        } else {
            index = randomiser.nextInt(moves.size());
            return (moves.get(index));
        }

	}

	@Override
	public int[] aiChooseBoard() {
		int x = randomiser.nextInt(3);
		int y = randomiser.nextInt(3);

		while(!env.superIsEmpty(x, y)) {
			System.out.printf("%d,%d", x, y);

			x = randomiser.nextInt(3);
			y = randomiser.nextInt(3);
		}
		int[] coords = {x, y};
		return coords;
	}
}
