import java.util.Scanner;
import java.util.ArrayList;

public class MorraGameManager {
	Scanner sc = new Scanner(System.in);
	String play = "";

	//array for storing the game objects
	ArrayList<MorraGame> games = new ArrayList<MorraGame>();

	public MorraGameManager() {
		while(!play.equals("n")) {
			String alreadyPlayed = (this.games.size()>0)?"another":"a"; //if this isn't the first game, ask if they want to play "another" one
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
		MorraGame game = new MorraGame();
		this.games.add(game);
	}

	public void printGamesSummary(){
		int count = 1;
		for(MorraGame mg:this.games) {
			System.out.println("\n\n=== GAME " + count + " SUMMARY ===");
			System.out.println(mg);
			count++;
		}
	}
}