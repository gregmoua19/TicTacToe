import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	
	//use global functions when you need many things to modify something
	//ie keeping score or player position
	static int round = 0;
	static ArrayList<Integer> playerPositions = new ArrayList<>();
	static ArrayList<Integer> computerPositions = new ArrayList<>();
	
	
	public static void main(String[] args) {
	
		char [] [] gameBoard = {{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '}};
		printGameBoard(gameBoard);
		Scanner input = new Scanner(System.in);
		while(true) {
		System.out.println("Please print a number from 1-9");
		int position = input.nextInt();
		while(playerPositions.contains(position) || computerPositions.contains(position)) {
			System.out.println("This position is taken. Please enter a correct position");
			position = input.nextInt();
		}
		
		placement(gameBoard,position,"player");
		
		Random rand = new Random();
		int computerPosition = 0;
		while(playerPositions.contains(computerPosition) || computerPositions.contains(computerPosition)) {
		computerPosition = rand.nextInt(9)+1;
		}
		
		placement(gameBoard,computerPosition,"computer");
		printGameBoard(gameBoard);
		String result = checkWinner(gameBoard);
		System.out.println(result);
		System.out.println("Do you want to play again? Y/N");
		String userChoice = input.nextLine();
		if(userChoice.equalsIgnoreCase("No") || userChoice.equalsIgnoreCase("N")) {
			break;
		}
		}
	}
	
	public static void placement(char[][] gameBoard, int position, String user) {
		round++;
		char symbol = ' ';
		if(user.equalsIgnoreCase("player")) {
			symbol = 'X';
			playerPositions.add(position);
		}else if(user.equals("computer")) {
			symbol = 'O';
			computerPositions.add(position);
		}
		
		switch(position) {
			case 1:
				//[row][column]
				//rows ---------
				//columns
				//|
				//|
				//|
				gameBoard [0][0] = symbol;
				break;
			case 2:
				gameBoard [0][2] = symbol;
				break;
			case 3:
				gameBoard [0][4] = symbol;
				break;
			case 4:
				gameBoard [2][0] = symbol;
				break;
			case 5:
				gameBoard [2][2] = symbol;
				break;
			case 6:
				gameBoard [2][4] = symbol;
				break;
			case 7:
				gameBoard [4][0] = symbol;
				break;
			case 8:
				gameBoard [4][2] = symbol;
				break;
			case 9:
				gameBoard [4][4] = symbol;
			default:
				break;
				
		}
	}
	public static void printGameBoard(char[][] gameBoard) {
		//grabs the first array of a 2d array
				for(char[] row : gameBoard) {
					//grabs each individual char and prints it
					for(char c  : row) {
						System.out.print(c);
					}
					System.out.println();
				}

	}
	
	public static String checkWinner(char[][] gameBoard) {
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);
		List leftDiag = Arrays.asList(1,5,9);
		List rightDiag = Arrays.asList(3,5,7);
		
		//put all the lists into another list to make it more concise
		
		List<List> win = new ArrayList<List>();
		win.add(topRow);
		win.add(midRow);
		win.add(botRow);
		win.add(leftCol);
		win.add(midCol);
		win.add(rightCol);
		win.add(leftDiag);
		win.add(rightDiag);
		
		for(List l : win) {
			if(playerPositions.containsAll(l)) {
				return "Congrats! You won!";
			} else if (computerPositions.containsAll(l)) {
				return "Looks like the computer won this time. Better luck next time";
			} else if(round == 10) {
				return "TIE!";
			}
		}
		return "Turn " + round;
	}
}
