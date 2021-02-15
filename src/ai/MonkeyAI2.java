package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tictactoe.GameEnvironment;
import tictactoe.MinorBoard;

;public class MonkeyAi2 extends Ai{
    private GameEnvironment env;
	private Random randomiser = new Random();
    private MinorBoard visionBoard = new MinorBoard();
    private String symbol;
    private String enemySymbol;
	
	public MonkeyAi2(GameEnvironment gameEnv, String symbol) {
		env = gameEnv;
        this.symbol = symbol;

        if (this.symbol.equals("O")){
            enemySymbol = "X";
        } else {
            enemySymbol = "O";
        }
	}

    /**
     * Refreshes the monkey's version of the current minor board
     */
    private void updateBoard() {
        String[][] boardState = env.getCurrentMinorBoard().getBoard();
        visionBoard.customState(Arrays.copyOf(boardState[2], 3), Arrays.copyOf(boardState[1], 3), Arrays.copyOf(boardState[0], 3));
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
                move[0] = x;
                move[1] = y;
                updateBoard();
                visionBoard.printBoard();

                if (visionBoard.validateCoords(x, y)){
                    System.out.println("validated");
                    visionBoard.mark(symbol, x, y);
                    winnable = visionBoard.getWinner().equals(symbol);
                    updateBoard();

                    if(winnable) {
                        System.out.println("winnable");

                        winningMoves.add(Arrays.copyOf(move, 2));
                    } else {
                        visionBoard.mark(enemySymbol, x, y);
                        blockable = visionBoard.getWinner().equals(enemySymbol);
                        updateBoard();
                    }

                    if (blockable) {
                        System.out.println("blockable");

                        blockingMoves.add(Arrays.copyOf(move, 2));
                    }

                    if (!winnable && !blockable){
                        System.out.println("move");

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
			x = randomiser.nextInt(3);
			y = randomiser.nextInt(3);
		}
		int[] coords = {x, y};
		return coords;
	}
}
