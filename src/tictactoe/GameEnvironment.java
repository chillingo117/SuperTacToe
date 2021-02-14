package tictactoe;

import ai.*;
/**
 * Contains the main function which initiates the game. 
 * A GameEnvironment is instantiated when the game starts.
 * 
 */

public class GameEnvironment {


	private boolean finish = false; //Whether the game has finished or not
	private String winner = " "; //Until someone wins, the winner is " "
	private Inputs input = new Inputs(); //init the inputs object
	private SuperBoard board = new SuperBoard(); //init the superBoard
	private BoardPrinter printer = new BoardPrinter(); //init the printer
	private String currentPlayer = "X"; //Sets the current player as X, X always goes first
	private int[] currentMinorBoard = {1, 1}; //inits the starting board as the center board
	
	private boolean xIsAi = false; //bool for if X is an AI
	private boolean oIsAi = false; //bool for if O is an AI
	private Ai aiX; //the X AI
	private Ai aiO; //the O AI

	public boolean isFinished(){
		return finish;
	}
	
	/**
	 * Sets up the game environment prior to beginning game.
	 */
	public void setup(){
		//Ask user to setup X and O's status as AI and what AI
		xIsAi = input.askForAi("X");
		if (xIsAi) {
			String chosenAi = input.selectAi();
			aiX = summonAi(chosenAi, this);
		}
		oIsAi = input.askForAi("O");
		if (oIsAi) {
			String chosenAi = input.selectAi();
			aiO = summonAi(chosenAi, this);
		}
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
	private MinorBoard getCurrentMinorBoard() {
		return board.getMinorBoard(currentMinorBoard[0], currentMinorBoard[1]);
	}
	
	/**
	 * Checks if a pair of coordinates is valid for marking
	 * @param x 
	 * @param y
	 * @return true if the coordinates are empty
	 */
	public boolean minorIsEmpty(int x, int y) {
		String[][] openMinBoard = getCurrentMinorBoard().getBoard();
		boolean empty = openMinBoard[y][x].equals(" ");
		return (empty);
	}
	
	/**
	 * Checks if a pair of coordinates is valid for choosing a new board
	 * @param x 
	 * @param y
	 * @return true if the minor board hasn't finished
	 */
	public boolean superIsEmpty(int x, int y) {
		MinorBoard minorBoard = board.getMinorBoard(x, y);
		return !minorBoard.isFinished();
	}
	
	/**
	 * uses the Inputs object to prompt for player input to choose the next minorBoard of play
	 */
	private void playerChooseBoard() {
		int[] boardCoords = input.getCoordInput("Input coordinates to choose next board");
		
		while(!superIsEmpty(boardCoords[0], boardCoords[1])) {
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

		
		while(!minorIsEmpty(coords[0], coords[1])) {
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
	 * Called when the game ends.
	 * It prints the board one final time, and displays a message stating the winner.
	 */
	private void gameFinish(){
		finish = true;
		printer.printBoard(board);
		//USE LOGGER
		System.out.printf("%s is the winner!", winner);
	}

	/**
	 * Called when a minor board has been won. 
	 * It checks to see if the currentPlayer, by winning the minor board, has won the overall game.
	 * If not, this function prompts the NEXT player to choose a new board.
	 */
	private void minorHasBeenWon(){
		if(board.checkWin(currentPlayer, currentMinorBoard)) {
			gameFinish();
			winner = getCurrentMinorBoard().getWinner();
		} else if (board.checkFull()) {
			gameFinish();
			winner = "Noone";
		} else {
			chooseNewBoard();
		}
	}
	/**
	 * processes a single game turn
	 */
	public void turn() {
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
		 *  Else...
		 */
		if (getCurrentMinorBoard().isFinished()) {
			printer.printBoard(board);
			minorHasBeenWon();
		} else {
			//Else check if the new board is empty, otherwise prompt NEXT player to 
			// choose a new board.
			if(!superIsEmpty(coords[0], coords[1])) {
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
