package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tictactoe.GameEnvironment;
import tictactoe.MinorBoard;

public class MonkeyAi2 extends Ai{
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

        visionBoard.customState(Arrays.copyOf(boardState[2], 3), Arrays.copyOf(boardState[1], 3), Arrays.copyOf(boardState[0], 3), " ", false);
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

                if (visionBoard.validateCoords(x, y)){
                    visionBoard.mark(symbol, x, y);

                    if(visionBoard.getWinner().equals(symbol)) {
                        winnable = true;
                        winningMoves.add(Arrays.copyOf(move, 2));
                    }

                    updateBoard();
                    visionBoard.mark(enemySymbol, x, y);

                    if (visionBoard.getWinner().equals(enemySymbol)) {
                        blockable = true;
                        blockingMoves.add(Arrays.copyOf(move, 2));
                    }

                    if (!winnable && !blockable){

                        moves.add(Arrays.copyOf(move, 2));
                    }
                }   
            }         
        }
        int index;

        System.out.printf("MonkeyAi2 %s identified:", symbol);
        System.out.println();
        System.out.print("Winning Moves:");
        for (int[] coord : winningMoves){
            System.out.print(Arrays.toString(coord));
        }
        System.out.println();
        System.out.print("Blocking Moves:");
        for (int[] coord : blockingMoves){
            System.out.print(Arrays.toString(coord));
        }
        System.out.println();
        System.out.print("Standard Moves:");
        for (int[] coord : moves){
            System.out.print(Arrays.toString(coord));
        }
        System.out.println();


        int[] chosenMove = {-1, -1};

        if(winnable){
            index = randomiser.nextInt(winningMoves.size());
            chosenMove = winningMoves.get(index);
        } else if (blockable) {
            index = randomiser.nextInt(blockingMoves.size());
            chosenMove = blockingMoves.get(index);
        } else {
            index = randomiser.nextInt(moves.size());
            chosenMove = moves.get(index);
        }
        return chosenMove;
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
