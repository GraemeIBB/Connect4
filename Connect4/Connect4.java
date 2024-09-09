import java.util.Scanner;

public class Connect4 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int[][] gameState = new int[6][7]; // (y,x)
		char[][] display = new char[6][7];
		int count = 0;

		displayBoard(gameState, display);
		while(count < 21) {
			beginTurn(scan, 1, gameState);
			displayBoard(gameState, display);
			if(ruleCheck(gameState) != 0)
				break;
			beginTurn(scan, 2, gameState);
			displayBoard(gameState, display);
			if(ruleCheck(gameState) != 0)
				break;
			count++;
		}
		if(count == 21)
			System.out.println("no more spots");
		System.out.println("player " + ruleCheck(gameState) + " wins");
		
		
	}

	public static void beginTurn(Scanner scan, int player, int[][] gameState) {
		System.out.println("Enter move (1-7) -- player " + player);

		int temp = scan.nextInt() - 1;
		
		while ((temp > 6 || temp < 0) || gameState[5][temp] != 0) {
			System.out.println("Unavailable move, try again");
			System.out.println("Enter move (1-7) -- player " + player);
			temp = scan.nextInt() - 1;
		}
		processMove(gameState, temp, player);

	}

	public static void processMove(int[][] gameState, int move, int player) {

		for (int i = 0; i < gameState[0].length; i++) {
			if (gameState[i][move] != 0) {
				continue;
			} else {
				gameState[i][move] = player;
				break;
			}
		}
	}

	public static void displayBoard(int[][] gameState, char[][] display) {

		updateDisplay(gameState, display);

		System.out.println("<1  2  3  4  5  6  7>");
		System.out.println(" ___________________");
		System.out.println(" |"+display[5][0]+" "+display[5][1]+" "+display[5][2]+" "+display[5][3]+" "+display[5][4]+" "+display[5][5]+" "+display[5][6]+"|");
		System.out.println(" |"+display[4][0]+" "+display[4][1]+" "+display[4][2]+" "+display[4][3]+" "+display[4][4]+" "+display[4][5]+" "+display[4][6]+"|");
		System.out.println(" |"+display[3][0]+" "+display[3][1]+" "+display[3][2]+" "+display[3][3]+" "+display[3][4]+" "+display[3][5]+" "+display[3][6]+"|");
		System.out.println(" |"+display[2][0]+" "+display[2][1]+" "+display[2][2]+" "+display[2][3]+" "+display[2][4]+" "+display[2][5]+" "+display[2][6]+"|");
		System.out.println(" |"+display[1][0]+" "+display[1][1]+" "+display[1][2]+" "+display[1][3]+" "+display[1][4]+" "+display[1][5]+" "+display[1][6]+"|");
		System.out.println(" |"+display[0][0]+" "+display[0][1]+" "+display[0][2]+" "+display[0][3]+" "+display[0][4]+" "+display[0][5]+" "+display[0][6]+"|");
		System.out.println(" --------------------");// ⚫⚪⛔
	}

	public static void updateDisplay(int[][] gameState, char[][] display) {
		for (int i = 0; i < gameState.length; i++) {
			for (int j = 0; j < gameState[0].length; j++) {

				switch (gameState[i][j]) {
				case 0:
					display[i][j] = '⚪';
					break;
				case 1:
					display[i][j] = '⚫';
					break;
				case 2:
					display[i][j] = '⛔';
					break;
				}
			}
		}
	}
	
	public static int ruleCheck(int[][] gameState) { 
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < gameState[0].length; j++) {
				
				if((gameState[i][j] != 0) && (gameState[i][j] == gameState[i+1][j]) && (gameState[i][j] == gameState[i+2][j]) && (gameState[i][j] == gameState[i+3][j])) {
					return gameState[i][j];
				}
			}
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < gameState.length; j++) {
				if((gameState[j][i] != 0) && (gameState[j][i] == gameState[j][i+1]) &&(gameState[j][i] == gameState[j][i+2]) && (gameState[j][i] == gameState[j][i+3])) {
					return gameState[j][i];
				}
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				if((gameState[i][j] != 0) && (gameState[i][j] == gameState[i+1][j+1]) && (gameState[i][j] == gameState[i+2][j+2]) && (gameState[i][j] == gameState[i+3][j+3])) {
					return gameState[i][j];
				}
			}
		}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				if((gameState[i+3][j] != 0) && (gameState[i+3][j] == gameState[i+2][j+1]) && (gameState[i+3][j] == gameState[i+1][j+2]) && (gameState[i+3][j] == gameState[i][j+3])) {
					return gameState[i+3][j];
				}
			}
		}
		return 0;
		
	}
}