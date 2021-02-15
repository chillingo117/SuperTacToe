package tictactoe;

//import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Inputs class contains functions used by the game environment to prompt players for input.
 * Inputs are only accepted after their values are validated.
 */
public class Inputs {

	private static Scanner scanner = new Scanner(System.in);


	/**
	 * Prompts for player to input integer and checks for validity. Recurses until input is valid.
	 * @param xy
	 * @return a number, 0, 1, or 2
	 */
	private int getNumberInput(String xy) {
		System.out.printf("Enter %s coordinate (0, 1, 2)", xy);
		String temp = scanner.nextLine();
		if (temp.equals("0") || temp.equals("1") || temp.equals("2")) {
			return (Integer.parseInt(temp));
		}
		else {
			System.out.println("Please input a valid number");
			return getNumberInput(xy);
		}

	}

	/**
	 * returns a pair of x y values inputted by the player
	 * @return a pair of xy coords
	 */
	public int[] getCoordInput(String message) {
		System.out.println(message);
		int[] coords = new int[2];
		coords[0] = this.getNumberInput("X");
		coords[1] = this.getNumberInput("Y");
		return coords;
	}
	
	/**
	 * Overloads above function to allow for a formatted message
	 * @param message as a string format
	 * @param symbol input to string format
	 * @return a pair of xy coords chosen by player
	 */
	public int[] getCoordInput(String message, String symbol) {
		String fullMessage = String.format(message, symbol);
		return getCoordInput(fullMessage);
	}
	
	/**
	 * Requests user input for a Y/N. Continually asks until valid input is given.
	 * @param symbol Either X or O, represents the player the user is decided to make an AI or not
	 * @return either Y or N
	 */
	public boolean askForAi(String symbol) {
		System.out.printf("is %s an AI? (Y/N)", symbol);
		String ans = scanner.nextLine();
		ans = ans.toUpperCase();
		if (ans.equals("Y")) {
			return true;
		} else if (ans.equals("N")) {
			return false;
		} else {
			System.out.println("invalid input");
			return askForAi(symbol);
		}
	}
	
	
	/**
	 * Requests user input to select an AI, then checks if input was valid. Repeats unti valid input is given.
	 * @param symbol a string inputted by player.
	 * @return the string representing an AI
	 */
	public String selectAi() {
		System.out.println("Choose the Ai");
		System.out.println("M: Monkey Ai");
		String ans = scanner.nextLine();
		ans = ans.toUpperCase();
		if (ans.equals("M")) {
			System.out.println("You have chosen Monkey Ai");
		} else if (ans.equals("M2")){
			System.out.println("You have chosen Monkey Ai 2");
		} else {
			System.out.println("invalid input");
			return selectAi();
		}
		return ans;
	}

}



// ####### BELOW IS PHASED OUT CODE #######
///**
// * Checks if input integer is valid
// * @param num, the input x or y number
// * @return
// */
//private boolean validInt(int num) {
//	if (num == 0 || num == 1 || num == 2) {
//		return true;
//	} else {
//		return false;
//	}
//}
//Old code to remind me of my mistakes XD
///**
// * Prompts for player integer input and checks for validity. It first ensure the input is an integer, then checks if
// * it is a valid integer. Upon failing either of these, it will recurse until a valid number is given.
// * @param x or y for prompting message
// * @return a valid integer
// */
//private int getNumberInput(String xy) {
//	System.out.printf("Enter %s number (0, 1, 2)", xy);
//	int num = 9;
//	boolean success = true;
//	// tries to get a number, if it fails it will recurse
//	try {
//		num = scanner.nextInt();
//	} catch (InputMismatchException e){
//		scanner.nextLine(); //consume \n left from nextInt
//		success = false;
//		System.out.println("Please input a number");
//		num = getNumberInput(xy);
//	}
//	
//	if (success) { //if a number was inputted, it is checked for validity (0,1,2), failure = recurse
//		scanner.nextLine();
//		if (!validInt(num)) {
//			System.out.println("Please input a valid number");
//			num = getNumberInput(xy);
//		}
//	}
//	// result is a number which is 0, 1, or 2
//	return num;
//}


//
//### Below phased out ###
///**
// * Checks if the input symbol is valid
// * @param symbol
// * @return true if symbol is valid, false otherwise
// */
//private boolean validSymbol(String symbol) {
//	symbol = symbol.toUpperCase();
//	if (symbol.equals("X") || symbol.equals("O")) {
//		return true;
//	} else {
//		return false;
//	}
//}
//
///**
// * Prompts for player symbol input and checks for validity.
// * @return a valid input symbol.
// */
//public String getSymbolInput() {
//	System.out.print("Enter player symbol (X or O): ");
//	String symbol = scanner.nextLine();
//	
//	if (!validSymbol(symbol)) {
//		System.out.println("Invalid symbol");
//		symbol = getSymbolInput();
//	}
//	return symbol.toUpperCase();
//}
