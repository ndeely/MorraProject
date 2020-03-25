import java.util.Scanner;

public class MorraGameManager {
	Scanner sc = new Scanner(System.in);
	String play = "";
	static int gameCount = 0;

	//array for storing the game objects
	MorraGame[] games = new MorraGame[100];

	public MorraGameManager() {
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
			} else {
				System.out.println("Invalid selection.");
			}
		}
	}

	public void playGame() {
		if (this.gameCount >= games.length) {
			System.out.println("You have played " + this.gameCount + " games. Maybe you should go outside and get some fresh air?");
			printGamesSummary();
			this.gameCount = 0;
			new MorraGameManager();
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