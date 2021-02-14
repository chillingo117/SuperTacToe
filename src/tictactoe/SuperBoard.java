package tictactoe;

import java.util.Arrays;

import tictactoe.MinorBoard;

public class SuperBoard {
	private MinorBoard[] row2 = {new MinorBoard(), new MinorBoard(), new MinorBoard()};
	private MinorBoard[] row1 = {new MinorBoard(), new MinorBoard(), new MinorBoard()};
	private MinorBoard[] row0 = {new MinorBoard(), new MinorBoard(), new MinorBoard()};
	
	private MinorBoard[][] board = {row0, row1, row2}; // Bind all rows to form the minor tictactoe game

	/**
	 * returns the state of the super board as a copy
	 * @return a copy of the superBoard state
	 */
	public MinorBoard[][] getSuperBoard () {
		MinorBoard[][] boardCopy = Arrays.copyOf(board, 3); 
		return boardCopy;
	}
	
	/**
	 * Returns the board as nested array of minorBoards.
	 * @return the board as a nested array of minorBoards.
	 */
	public MinorBoard[][] getTrueBoard() {
		return board;
	}

	
	/**
	 * returns the state of a minor board specified by coordinates
	 * @param x coord of the chosen minor board
	 * @param y coord of the chosen minor board
	 * @return the state of the chosen minor board
	 */
	public MinorBoard getMinorBoard (int x, int y) {
		return board[y][x];
	}
	
	/**
	 * Checks if the superBoard is full
	 * @return boolean, true if superBoard is full.
	 */
	public boolean checkFull() {
		boolean full = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!board[i][j].isFinished()) {
					full = false;
				}
			}
		}
		return full;
	}
	
	/**
	 * Checks if a player has won the superBoard
	 * @param the Player. This function checks if this player has won
	 * @param currentMinorBoard the currentMinorBoard in play
	 * @return true if the player has won
	 */
	public boolean checkWin(String player, int[] currentMinorBoard) {
	
		// Check if mark has completed a row
		boolean win = true;
		for (int row = 0; row < 3; row++) {
			MinorBoard toCheck = board[currentMinorBoard[1]][row];
			if (!toCheck.getWinner().equals(player)) {
				win = false;
			}
		}
		if (win) {
			return true;
		}

		win = true;
		for (int col = 0; col < 3; col++) {
			MinorBoard toCheck = board[col][currentMinorBoard[0]];
			if (!toCheck.getWinner().equals(player)) {
				win = false;
			}
		}
		if (win) {
			return true;
		}
		
		win = true;
		
		// Check if mark has completed a diagonal
		String center = board[1][1].getWinner();
		
		String topLeft = board[2][0].getWinner();
		String topRight = board[2][2].getWinner();
		String botLeft = board[0][0].getWinner();
		String botRight = board[0][2].getWinner();

		if(center.equals(player)) {
			if((topLeft.equals(center) && center.equals(botRight)) || (botLeft.equals(center) && center.equals(topRight))) {
				return true;
			}
		}


		// if we reach here, then noone has won yet 
		return false;
	
	}
	
}
