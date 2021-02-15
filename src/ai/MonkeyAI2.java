package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tictactoe.GameEnvironment;
import tictactoe.MinorBoard;


/**
 * A monkey ai that is smart enough to win boards when it can, and blocks enemy lines. Plays randomly otherwise.
 */
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

    /**
     * Prints all identified moves
     * @param winningMoves Array of winning moves
     * @param blockingMoves Array of blocking moves
     * @param moves Array of other moves
     */
    private void printIdentifiedMoves(ArrayList<int[]> winningMoves, ArrayList<int[]> blockingMoves, ArrayList<int[]> moves) {
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
    }

    /**
     * Chooses a random move, prioritising winning moves, then blocking moves
     * @param winnable boolean for if a winning move is available
     * @param blockable boolean for if a blocking move is available
     * @param winningMoves array of winning moves
     * @param blockingMoves array of blocking moves
     * @param moves array of all moves
     * @return a random best move
     */
    private int[] chooseMove(ArrayList<int[]> winningMoves, ArrayList<int[]> blockingMoves, ArrayList<int[]> moves) {
        int[] chosenMove;
        int index;

        if(!winningMoves.isEmpty()){
            index = randomiser.nextInt(winningMoves.size());
            chosenMove = winningMoves.get(index);
        } else if (!blockingMoves.isEmpty()) {
            index = randomiser.nextInt(blockingMoves.size());
            chosenMove = blockingMoves.get(index);
        } else {
            index = randomiser.nextInt(moves.size());
            chosenMove = moves.get(index);
        }
        return chosenMove;
    }
    /**
     * Adds a move to winning moves if it is a winning move
     * @param x
     * @param y
     * @param winningMoves
     */
    private void addToWinningMoves(int x, int y, ArrayList<int[]> winningMoves) {
        updateBoard();
        visionBoard.mark(symbol, x, y);
        int[] move = {x, y};
        if(visionBoard.getWinner().equals(symbol)) {
            winningMoves.add(Arrays.copyOf(move, 2));
        }
    }

    /**
     * Adds a move to blocking move if it is a blocking move
     * @param x
     * @param y
     * @param blockingMoves
     */
    private void addToBlockingMoves(int x, int y, ArrayList<int[]> blockingMoves){
        updateBoard();
        visionBoard.mark(enemySymbol, x, y);
        int[] move = {x, y};
        if (visionBoard.getWinner().equals(enemySymbol)) {
            blockingMoves.add(Arrays.copyOf(move, 2));
        }
    }

    /**
     * Returns the move the AI wants to make
     */
	@Override
	public int[] takeTurn() {

        ArrayList<int[]> winningMoves = new ArrayList<>();
        ArrayList<int[]> blockingMoves = new ArrayList<>();
        ArrayList<int[]> moves = new ArrayList<>();

        int x = -1;
        int y = -1;
        int[] move = {x, y};

        //For all possible moves, check if they are valid, then add them to the appropriate move arraylists
        for (x = 0; x < 3; x++) {
            for (y= 0; y < 3; y++) {
                move[0] = x;
                move[1] = y;
                updateBoard();

                if (visionBoard.validateCoords(x, y)){
                    addToWinningMoves(x, y, winningMoves);
                    addToBlockingMoves(x, y, blockingMoves);
                    moves.add(Arrays.copyOf(move, 2));
                }   
            }         
        }

        printIdentifiedMoves(winningMoves, blockingMoves, moves);

        int[] chosenMove;
        chosenMove = chooseMove(winningMoves, blockingMoves, moves);
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
