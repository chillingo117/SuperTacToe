package tictactoe;

import java.util.Arrays;

/**
 * A MinorBoard is an instance of the common 3x3 simple tic tac toe game.
 *
 */

public class MinorBoard {
	
	private String winner = " ";
	private boolean finished = false;

	//Using Arrays instead of ArrayLists because they will be of set size
	
	private String[] row2 = {" ", " ", " "}; // Create row 2
	private String[] row1 = {" ", " ", " "}; // Create row 1
	private String[] row0 = {" ", " ", " "}; // Create row 0
	
	private String[][] board = {row0, row1, row2}; // Bind all rows to form the minor tictactoe game
	/**
	 * Note that the arrays are organised by rows and not columns, so to correctly mark Xs and Os, this is inconvenient because we cannot
	 * access the board by x, y. It must be y, x which feels less intuitive. However, this makes accessing rows much easier, and is needed
	 * when the main game board tries to print the whole super tac toe board (which will be done in rows).
	 * 
	 * However, this makes printing the board a pain, as the board must be printed from top row first (which is row2, not row0).
	 */
	
	/**
	 * sets the board to a given state
	 * @param row2
	 * @param row1
	 * @param row0
	 */
	public void customState(String[] row2, String[] row1, String[] row0) {
		board[0] = Arrays.copyOf(row0, 3);
		board[1] = Arrays.copyOf(row1, 3);
		board[2] = Arrays.copyOf(row2, 3);
	}
	
	/**
	 * Returns whether param coords are valid to play on
	 */
	public boolean validateCoords(int x, int y){
		String symbol = board[y][x];
		return (symbol.equals(" "));
	}

	/**
	 * returns the copied state of this board
	 * @return the copied board state
	 */
	public String[][] getBoard() {
		return Arrays.copyOf(board, 3); 
	}
	
	/**
	 * Places a mark onto the minor board and processes its effects, if any
	 * IE if the mark finished and/or wins the board, this function changes the board's state to match
	 * @param symbol
	 */
	public void mark(String symbol, int x, int y) {
		board[y][x] = symbol;
		checkFinished(symbol, x, y);
		if (finished) {
			transform(winner);
		}
	}
	
	public String getWinner() {
		return winner;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	/**
	 * When a player wins this tac board, this function transforms the marks to represent the player who won it.
	 * If the board was a tie, it fill completely with #
	 * @param winner
	 */
	private void transform(String winner) {
		if (winner.equals("X")) {
			board[0][2] = "#";
			board[0][0] = "#";
			board[1][1] = "#";
			board[2][2] = "#";
			board[2][0] = "#";
			board[1][0] = " ";
			board[1][2] = " ";
			board[0][1] = " ";
			board[2][1] = " ";
		} else if (winner.equals("O")){
			board[0][0] = "#";
			board[0][1] = "#";
			board[0][2] = "#";
			board[1][2] = "#";
			board[2][2] = "#";
			board[2][1] = "#";
			board[2][0] = "#";
			board[1][0] = "#";
			board[1][1] = " ";
		} else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					board[i][j] = "#";
				}
			}
		}
	}
	
	/**
	 * Checks whether the board is full
	 * @return true if bard is full
	 */
	private boolean checkFull() {
		boolean full = true;
		for (String[] row : board) {
			for (String symbol : row) {
				if (symbol.equals(" ")) {
					full = false;
				}
			}
		}
		return full;
	}	
	
	
	/**
	 * Checks if a given symbol, when marking the given x and y grid, finishes the minor board. 
	 * IE completes a row, column, diagonal, or fills the last space. 
	 * @param symbol
	 * @param x
	 * @param y
	 */
	private void checkFinished(String symbol, int x, int y) {		
		String[] row = board[y];
		if (row[0].equals(row[1]) && row[1].equals(row[2])) {
			// Check if mark has completed a row
			winner = symbol;
			finished = true;
			
		} else if (board[0][x].equals(board[1][x]) && board[1][x].equals(board[2][x])) {
			// Check if mark has completed a column
			winner = symbol;
			finished = true;
			
		} else if (board[1][1].equals(symbol)) {
			// Check if mark has completed a diagonal
			if ((board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) || (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))) {
				winner = symbol;
				finished = true;
			}
		}
		
		if (!finished && checkFull()) {
			winner = " ";
			finished = true;
		}

	}
	
}
