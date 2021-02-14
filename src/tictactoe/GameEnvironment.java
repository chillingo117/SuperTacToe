package tictactoe;


import tictactoe.Inputs;
import tictactoe.BoardPrinter;
import tictactoe.SuperTacBoard;


import ai.*;
/**
 * Contains the main function which initiates the game. 
 * A GameEnvironment is instantiated when the game starts.
 * 
 */

public class GameEnvironment {


	private static boolean finish = false; //Whether the game has finished or not
	private static String winner = " "; //Until someone wins, the winner is " "
	private static Inputs input = new Inputs(); //init the inputs object
	private static SuperTacBoard board = new SuperTacBoard(); //init the superBoard
	private static BoardPrinter printer = new BoardPrinter(); //init the printer
	private static String currentPlayer = "X"; //Sets the current player as X, X always goes first
	private static int[] currentMinorBoard = {1, 1}; //inits the starting board as the center board
	
	private static boolean xIsAi = false; //bool for if X is an AI
	private static boolean oIsAi = false; //bool for if O is an AI
	private static Ai aiX; //the X AI
	private static Ai aiO; //the O AI
	
	/**
	 * main function that starts and ends the game
	 * @param args
	 */
	public static void main(String[] args) {
		//Creates new game environment and waits for it be finished, then generates win message
		GameEnvironment env = new GameEnvironment();
		
		//Ask user to setup X and O's status as AI and what AI
		xIsAi = input.askForAi("X");
		if (xIsAi) {
			String chosenAi = input.selectAi();
			aiX = summonAi(chosenAi, env);
		}
		oIsAi = input.askForAi("O");
		if (oIsAi) {
			String chosenAi = input.selectAi();
			aiX = summonAi(chosenAi, env);

		}
		//Until the game is finished, keep prompting turns to be made
		while (!finish) {
			env.turn();
		}
		// Once the game is finished, print the final board status and winner
		printer.printBoard(board);
		System.out.printf("%s is the winner!", winner);
	}
	
	/**
	 * Returns an AI 
	 * @param aiCode A string that determines the type of AI created
	 * @param env The game environment that the AI will play on
	 * @return AI determined by aiCode that plays on env
	 */
	private static Ai summonAi(String aiCode, GameEnvironment env) {
		if (aiCode.equals("M")) {
			return new MonkeyAi(env);
		} else {
			return new FasterMonkeyAi(env);
		}
	}
	
	/**
	 * Returns the current minorBoard in play
	 * @return current minorBoard in play
	 */
	private MinorTacBoard getCurrentMinorBoard() {
		return board.getMinorBoard(currentMinorBoard[0], currentMinorBoard[1]);
	}
	
	/**
	 * Checks if a pair of coordinates is valid for marking
	 * @param x 
	 * @param y
	 * @return true if the coordinates are empty
	 */
	public boolean checkMinorCoords(int x, int y) {
		String[][] openMinBoard = getCurrentMinorBoard().getBoard();
		if (openMinBoard[y][x] != " ") {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Checks if a pair of coordinates is valid for choosing a new board
	 * @param x 
	 * @param y
	 * @return true if the minor tac board hasn't been won
	 */
	public boolean checkSuperCoords(int x, int y) {
		MinorTacBoard minorBoard = board.getMinorBoard(x, y);
		if (minorBoard.isFinished()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * uses the Inputs object to prompt for player input to choose the next minorBoard of play
	 */
	private void playerChooseBoard() {
		int[] boardCoords = input.getCoordInput("Input coordinates to choose next board");
		
		while(!checkSuperCoords(boardCoords[0], boardCoords[1])) {
			boardCoords = input.getCoordInput("Board is finished, choose another");
		}
		currentMinorBoard[0] = boardCoords[0];
		currentMinorBoard[1] = boardCoords[1];
	}
	
	/**
	 * prompts the AI to make a move
	 * @param ai
	 */
	private void aiChooseBoard(Ai ai) {
		int[] boardCoords = ai.aiChooseBoard();
		String aiPlay = String.format("Ai selected board %d, %d", boardCoords[0], boardCoords[1]);
		System.out.println(aiPlay);
		currentMinorBoard[0] = boardCoords[0];
		currentMinorBoard[1] = boardCoords[1];
	}
	
	/**
	 * Prompts player to choose a new minor board to play on. Is called when previous player wins or finished a board.
	 * Note that this prompts the NEXT player to choose the new board. This is intentional.
	 */
	private void chooseNewBoard() {
		if (currentPlayer.equals("X") && oIsAi) {
			aiChooseBoard(aiO);
		} else if (currentPlayer.equals("O") && xIsAi) {
			aiChooseBoard(aiX);
		} else {
			playerChooseBoard();
		}
	}
	
	/**
	 * Prompts the player to make a turn. Returns a int pair for x and y coords chosen by player
	 * @return X and Y chosen by player
	 */
	private int[] playerTurn() {
		System.out.printf("You are at %d, %d %n", currentMinorBoard[0], currentMinorBoard[1]);

		int[] coords = input.getCoordInput("Player %s, input coordinates to place your mark", currentPlayer);

		
		while(!checkMinorCoords(coords[0], coords[1])) {
			coords = input.getCoordInput("Invalid coordinates, coords already marked");
		}
		return coords;
	}
	
	/**
	 * Returns the coords the given AI plays at
	 * @param ai the AI object to make a turn
	 * @return the X Y coords chosen by AI
	 */
	private int[] aiTurn(Ai ai) {
		int[] coords = ai.takeTurn();
		String aiPlay = String.format("The Ai played at %d, %d", coords[0], coords[1]);
		System.out.println(aiPlay);
		return coords;
	}
	
	/**
	 * processes a single game turn
	 */
	private void turn() {
		// Begin by printing the board
		printer.printBoard(board);

		int[] coords;
		
		/*
		 *  Check who's turn it is and prompt them to make their turn.
		 *  Remember the X Y coords chosen
		 */
		if (currentPlayer.equals("O") && oIsAi) {
			coords = aiTurn(aiO);
		} else if (currentPlayer.equals("X") && xIsAi) {
			coords = aiTurn(aiX);
		} else {
			coords = playerTurn();
		}

		// Processes the player's move
		getCurrentMinorBoard().mark(currentPlayer, coords[0], coords[1]);

		/*
		 *  If the minorBoard is finished, check if a player has won the game.
		 *  Otherwise prompt the NEXT player to choose a new board.
		 */
		if (getCurrentMinorBoard().isFinished()) {
			printer.printBoard(board);
			if(board.checkWin(currentPlayer, currentMinorBoard)) {
				finish = true;
				winner = getCurrentMinorBoard().getWinner();
			} else if (board.checkFull()) {
				finish = true;
				winner = "Noone";
			} else {
				chooseNewBoard();
			}
		} else {

			if(!checkSuperCoords(coords[0], coords[1])) {
				chooseNewBoard();
			} else {
				currentMinorBoard[0] = coords[0];
				currentMinorBoard[1] = coords[1];
			}
		}
		
		// Change the currentPlayer to the next player
		if (currentPlayer.equals("X")) {
			currentPlayer = "O";
		} else {
			currentPlayer = "X";
		}
	}


}
