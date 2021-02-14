package tictactoe;
/*
 * This class prints a given superBoard
 */
public class BoardPrinter {
	
	private String spacer = "==========================";
	private String minRowTemplate = "|%s|%s|%s|";
	

	/*
	 * Decompose converts a superBoard into a nested array of strings. 
	 */
	private String[][] decompose(MinorTacBoard[][] superBoard) {
		String[][] fullDecomp = new String[27][3];
		int decompNext = 0;
		
		MinorTacBoard[] tempSup = superBoard[2];
		//Switches the top and bottom minorBoards to print in the right order
		//(Top boards need to be printed first)
		superBoard[2] = superBoard[0];
		superBoard[0] = tempSup;
		
		//Unpack the superBoard into a nested array of strings
		for (MinorTacBoard[] superRow : superBoard) {
			for (MinorTacBoard minorBoard : superRow) {
				String[][] minDecomp = minorBoard.getBoard();
				//Switches the top and bottom row of each minorBoard in prep for printing
				String[] temp = minDecomp[2];
				minDecomp[2] = minDecomp[0];
				minDecomp[0] = temp;
				for (String[] row : minDecomp) {
					fullDecomp[decompNext] = row;
					decompNext++;
				}
			}
		}
		
		String[][] organisedDecomp = new String[27][3];
		int current = 0;
		int[] order = {0,3,6};
		int target = 0;
		//Reorganises the nested strings so that they represent the superBoard from left to right
		for (int supLayer = 0; supLayer < 3; supLayer++) {
			for (int minorLayer = 0; minorLayer < 3; minorLayer++) {
				for (int cycle = 0; cycle < 3; cycle++) {
					target = order[cycle] + minorLayer + supLayer*9;
					organisedDecomp[current] = fullDecomp[target];
					current ++;
				}
			}
		}
		return organisedDecomp;
	}
	

	/*
	 * printBoard prints the superBoard to console for player view
	 * Utilises the decompose function, then prints the board.
	 */
	public void printBoard(SuperTacBoard board) {
		MinorTacBoard[][] superBoard = board.getSuperBoard();
		String[][] toPrint = decompose(superBoard);
		System.out.println(spacer);
		int index = 0;
		for (int superRow = 0; superRow < 3; superRow ++) {
			for (int minorRow = 0; minorRow < 3; minorRow ++) {
				System.out.print("|");
				for (int cycle = 0; cycle < 3; cycle ++) {
					String[] row = toPrint[index];
					System.out.printf(minRowTemplate, row[0], row[1], row[2]);
					index++;
				}
				System.out.println("|");
			}
			System.out.println(spacer);
		}
	}
}
