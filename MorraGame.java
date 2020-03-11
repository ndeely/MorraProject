import java.util.Scanner;
import java.util.ArrayList;

public class MorraGame {
	Scanner sc = new Scanner(System.in);
	private int playerTeam; //0 for even, 1 for odd
	private int playerScore = 0, compScore = 0; //total points
	private int roundWin = 0;
	private int playerBonus = 0, compBonus = 0; //bonus points
	private ArrayList<Integer> playerMoves = new ArrayList<Integer>();
	private ArrayList<Integer> compMoves = new ArrayList<Integer>();
	private String winner = "";

	public MorraGame() {
		chooseTeam();
		int count = 1;
		while(playerScore < 12 && compScore < 12) {
			System.out.println("\n\n--- ROUND " + count + " ---");
			playRound();
			count++;
		}
		this.winner = (playerScore>compScore)?"You won the game!":(playerScore==compScore)?"It's a draw!":"The computer won the game!";
		System.out.println(this.gameEnd());
	}

	//stores the player's chosen team, and confirms it to the player
	public void chooseTeam() {
		int team = 2;
		while(team != 0 && team != 1) {
			boolean iError = true; //input error
			while(iError) {
				System.out.println("Please choose your team. (0 for evens, 1 for odds)");
				if(sc.hasNextInt()) {
					team = sc.nextInt();
					iError = false;
				} else {
					System.out.println("Please enter a NUMBER (either 0 or 1)");
					sc.reset();
					sc.next();
				}
			}
		}
		setPlayerTeam(team);
		String chosenTeam = (team==0)?"evens":"odds";
		System.out.println("You have chosen to play as " + chosenTeam + "!");
	}

	//plays a round
	public void playRound() {
		int playerMove = 11;
		while(playerMove < 1 || playerMove > 10) {
			System.out.println("Please enter your move (1 - 10)");
			playerMove = sc.nextInt();
		}
		System.out.println("You chose: " + playerMove);
		int compMove = (int)(Math.random() * 10 + 1);
		System.out.println("Computer chose: " + compMove);
		this.playerMoves.add(playerMove);
		this.compMoves.add(compMove);
		calcScore(playerMove, compMove);
	}

	//calculates and updates the total points, bonus points, rounds won, etc
	public void calcScore(int playerMove, int compMove) {
		int sum = playerMove + compMove;

		//calculate scores (+3 for winner, +2 for closest)
		if(playerTeam%2 == sum%2) { //player team and sum both even/odd
			this.playerScore += 3;
			System.out.println("You won the round!");
			this.roundWin++;
		} else {
			this.compScore += 3;
			System.out.println("The computer won the round!");
		}
		if(Math.abs(sum - compMove) > Math.abs(sum - playerMove)) { //compMove further than playerMove
			this.playerScore += 2;
			System.out.println("You won bonus points!");
			this.playerBonus += 2;
		} else if(Math.abs(sum - playerMove) > Math.abs(sum - compMove)) {
			this.compScore += 2;
			System.out.println("The computer won bonus points!");
			this.compBonus += 2;
		} //no else. nothing happens if they are an equal distance apart

		//print scores
		System.out.println("Your score is now: " + playerScore);
		System.out.println("The computer's score is now: " + compScore);
	}

	//setter for player team
	public void setPlayerTeam(int team) {
		this.playerTeam = team;
	}

	//getter for player team
	public int getPlayerTeam() {
			int team = this.playerTeam;
			return team;
	}

	//when game ends, displays the winner and move history
	public String gameEnd() {
			String moveSummary = "\n\n" + this.winner;
			moveSummary += "\nMoves";
			moveSummary += "\nPlayer - Computer";
			for(int moveCount = 0; moveCount < playerMoves.size(); moveCount++) {
				moveSummary += "\n  " + playerMoves.get(moveCount) + "    -    " + compMoves.get(moveCount);
			}
			return moveSummary;
	}

	//rounds won and lost
	//even/odd number count for both players
	//bonus points received by both
	public String toString() {
		int playerEvenCount = 0, compEvenCount = 0;
		for(int i = 0; i < this.playerMoves.size(); i++) {
			if(this.playerMoves.get(i)%2 == 0) {playerEvenCount++;}
			if(this.compMoves.get(i)%2 == 0) {compEvenCount++;}
		}

		String gameString = this.winner;
		gameString += "\nRounds Won: " + this.roundWin;
		gameString += "\nRounds Lost: " + (this.playerMoves.size() - this.roundWin);
		String grammar1 = (playerEvenCount == 1)?"number":"numbers";
		String grammar2 = ((this.playerMoves.size() - playerEvenCount) == 1)?"number":"numbers";
		gameString += "\nYou chose " + playerEvenCount + " even " + grammar1 + ", and " + (this.playerMoves.size() - playerEvenCount) + " odd " + grammar2 + ".";
		grammar1 = (compEvenCount == 1)?"number":"numbers";
		grammar2 = ((this.playerMoves.size() - compEvenCount) == 1)?"number":"numbers";
		gameString += "\nThe computer chose " + compEvenCount + " even " + grammar1 + ", and " + (this.playerMoves.size() - compEvenCount) + " odd " + grammar2 + ".";
		gameString += "\nYou received " + this.playerBonus + " bonus points, and the computer received " + this.compBonus + " bonus points.";
		return gameString;
	}
}