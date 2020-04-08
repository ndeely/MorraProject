import java.util.Scanner;

public class MorraGameManager {
	Scanner sc = new Scanner(System.in);
	private String play = "";
	private int gameCount = 0;

	//array for storing the game objects
	MorraGame[] games = new MorraGame[10];

	//shows welcome code and options menu
	public MorraGameManager() {
		showWelcome();
		boolean active = true;
		int option = 0;
		while(active) {
			boolean jError = true; //used jError to avoid conflict with iError
			while(jError) {
				System.out.println("\n=== Please choose an option ===");
				System.out.println("1: Show game description\n2: Play\n3: Quit");
				if(sc.hasNextInt()) {
					option = sc.nextInt();
					if(option == 1 || option == 2 || option == 3) {
						switch(option) {
							case 1: printGameDesc();
									break;
							case 2: this.play = "y";
									runMainLoop();
									break;
							case 3: jError = false;
									active = false;
									break;
						}
					} else {
						System.out.println("Invalid number");
					}
				} else {
					System.out.println("That's not a number");
					sc.reset();
					sc.next();
				}
			}
		}
	}

	//prints welcome menu
	public void showWelcome() {
		//welcome menu print screen
		System.out.println("=============================");
		System.out.println("\n===       Welcome to      ===");
		System.out.println("\n===         MORRA         ===");
        System.out.println("=============================");
	}

	//prints a description of the game
	public void printGameDesc() {
		String gameDesc = "MORRA is a hand game usually played for entertainment or to settle a disagreement.\n";
		gameDesc += "The game has many variations and can be played by two or more players.\n\n";
		gameDesc += "This variation of the game is a two-player game, where one player is going to be\nthe 'Odds' player and the other player is the 'Evens' player. ";
		gameDesc += "In each round of the\ngame, the players will simultaneously hold out between 1 and 10 fingers.\n";
		gameDesc += "The winner of the round is decided based on the sum of fingers shown by both players,\n";
		gameDesc += "namely if the sum is an even number then the 'Evens' player wins, otherwise if the\nsum is an odd number then the 'Odds' player wins.\n\n";
		gameDesc += "The winner of the round receives three points. In addition, the player whose number\nof fingers is closer to the sum, ";
		gameDesc += "receives two extra points.\n\nThe winner of the game is the first player who accumulates 12 points.";
		System.out.println(gameDesc);
	}

	public void runMainLoop() {
		while(!play.equals("n")) {
			//if this isn't the first game, ask if they want to play "another" one
			String alreadyPlayed = "";
			if(this.gameCount>0) {
				alreadyPlayed += "another";
			}
			else {
				alreadyPlayed += "a";
			}
			System.out.println("Would you like to play " + alreadyPlayed + " game of Morra? (y/n)");
			play = this.sc.next().substring(0,1).toLowerCase();
			if(play.equals("y")) {
				playGame();
			} else if (play.equals("n")) { //if they don't want to continue, print out their game summary
				printGamesSummary();
				this.gameCount = 0;
			} else {
				System.out.println("Invalid selection.");
			}
		}
	}

	public void playGame() {
		//checks players mental health after 10 games
		if (this.gameCount >= games.length) {
			System.out.println("\n\nYou have played " + this.gameCount + " games. Maybe you should go outside and get some fresh air?\n\n");
			//increase size of games array by 10
			MorraGame[] games2 = new MorraGame[this.games.length + 10];
			for(int count = 0; count < this.gameCount; count++) {
				games2[count] = this.games[count];
				this.games = games2;
			}
		} else {
			MorraGame game = new MorraGame();
			this.games[this.gameCount] = game;
			this.gameCount++;
		}
	}

	public void printGamesSummary(){
		for(int count = 0; count < this.gameCount; count++) {
			System.out.println("\n\n=== GAME " + (count+1) + " SUMMARY ===");
			System.out.println(games[count]);
		}
	}
}