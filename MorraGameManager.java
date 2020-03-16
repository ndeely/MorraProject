import java.util.Scanner;
import java.util.ArrayList;

public class MorraGameManager {
	public MorraGameManager() {
		Scanner sc = new Scanner(System.in);
		String play = "";

		//array for storing the game objects
		ArrayList<MorraGame> games = new ArrayList<MorraGame>();

		while(!play.equals("n")) {
			String alreadyPlayed = (games.size()>0)?"another":"a"; //if this isn't the first game, ask if they want to play "another" one
			System.out.println("Would you like to play " + alreadyPlayed + " game of Morra? (y/n)");
			play = sc.next().substring(0,1).toLowerCase();
			if(play.equals("y")) {
				MorraGame game = new MorraGame();
				games.add(game);
			} else if (play.equals("n")) { //if they don't want to continue, print out their game summary
				int count = 1;
				for(MorraGame mg:games) {
					System.out.println("\n\n--- GAME " + count + " SUMMARY ---");
					System.out.println(mg);
					count++;
				}
			} else {
				System.out.println("Invalid selection.");
			}
		}
	}
}