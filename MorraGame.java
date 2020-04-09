
/**
 *
 * @author Niall Deely, Tudor George Pascu, Alexandre Zurcher
 * PBL for Software development, Morra Game
 email Niall: x19216025@student.ncirl.ie
 email Tudor: x19209690@student.ncirl.ie
 email Alex: x19224338@student.ncirl.ie
 * 
 */
import java.util.Scanner;

public class MorraGame {
    
        //declaring variables
	Scanner sc = new Scanner(System.in);
	private int playerTeam; //0 for even, 1 for odd
	private int playerScore = 0, compScore = 0; //total points
	private int roundWin = 0;
	private int playerBonus = 0, compBonus = 0; //bonus points
	private int[] playerMoves = new int[6];//no more than 6 moves logically required to reach the target number
	private int[] compMoves = new int[6];
	private String winner = "";
	private int moveCount = 0;

        
        //Core method, calls the chose Team method and define the 3 main structural options of the game, win, draw, loose
	public MorraGame() {
		chooseTeam();
		int count = 1;
		while(playerScore < 12 && compScore < 12) {
			System.out.println("\n\n=== ROUND " + count + " ===");
			playRound();
			count++;
		}
		if(playerScore > compScore) {
			this.winner = "You are the winner of the game :) !!!";
		} else if (playerScore == compScore) {
			this.winner = "Nobody win, is a draw!";
		} else {
			this.winner = "The computer has won this game :( !";
		}
		System.out.println(this.gameEnd());
	}
	
	//stores the player's chosen team, and confirms it to the player
	public void chooseTeam() {
		int team = 0;
		boolean iError = true; //input error
		while(iError) {
                    System.out.println("Please choose your team. Type:  (1 for odds, 2 for evens)");
                    if(sc.hasNextInt()) {
                            team = sc.nextInt();
                            if(team == 1 || team == 2 ) {
                                    iError = false;
                            } else {
                                    System.out.println("Invalid number, please try again");
                            }
                    } else {
                            System.out.println("That's not a number");
                            sc.reset();
                            sc.next();
                    }
		}
		setPlayerTeam(team);
		String chosenTeam = "";
		if (team == 2) {
			chosenTeam += "evens";
		} else {
			chosenTeam += "odds";
		}
		System.out.println("You have chosen to play as " + chosenTeam + "!");
	}

	//plays a round in the game
        //Take player's move, take computer's move, and evaluate the round
	public void playRound() {
		int playerMove = 11;
		boolean iError = true;
		while(iError) {
		    System.out.println("Please enter your move (1 - 10)");
		    if(sc.hasNextInt()) {
			    playerMove = sc.nextInt();
			    if(playerMove > 0 && playerMove < 11) {
				    iError = false;
			    } else {
				    System.out.println("Invalid number");
			    }
		    } else {
			    System.out.println("That's not a number");
			    sc.reset();
			    sc.next();
		    }
		}
		System.out.println("You chose: " + playerMove);
		int compMove = (int)(Math.random() * 10 + 1);
		System.out.println("Computer chose: " + compMove);
		this.playerMoves[this.moveCount] = playerMove;
		this.compMoves[this.moveCount] = compMove;
		    moveCount++;
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

	//when game ends, displays the winner and move it to the history
	public String gameEnd() {
		String moveSummary = "\n\n" + this.winner;
		moveSummary += "\nMoves";
		moveSummary += "\nPlayer - Computer";
		for(int count = 0; count < this.moveCount; count++) {
			moveSummary += "\n  " + playerMoves[count] + "    -    " + compMoves[count];
		}
		return moveSummary;
	}

	//prints the game in a string format
	//includes rounds won and lost, even/odd number count for both players, bonus points received by both
	public String toString() {
		int playerEvenCount = 0, compEvenCount = 0;
		for (int count = 0; count < this.moveCount; count++) {
			if(this.playerMoves[count]%2 == 0) {playerEvenCount++;}
			if(this.compMoves[count]%2 == 0) {compEvenCount++;}
		}

		String gameString = this.winner;
		gameString += "\nRounds Won: " + this.roundWin;
		gameString += "\nRounds Lost: " + (this.moveCount - this.roundWin);
		String grammar1 = "", grammar2 = "";
		if (playerEvenCount == 1) { //special "if" to catch grammar errors
			grammar1 = "number";    
		} else {
			grammar1 = "numbers";
		}
		if (this.moveCount - playerEvenCount == 1) {
			grammar2 = "number";
		} else {
			grammar2 = "numbers";
		}
		//even/odd number count for player with fixed grammar
		gameString += "\nYou chose " + playerEvenCount + " even " + grammar1 + ", and " + (this.moveCount - playerEvenCount) + " odd " + grammar2 + ".";
		if (compEvenCount == 1) {
			grammar1 = "number";
		} else {
			grammar1 = "numbers";
		}
		if (this.moveCount - compEvenCount == 1) {
			grammar2 = "number";
		} else {
			grammar2 = "numbers";
		}
		//even/odd number count for computer with fixed grammar
		gameString += "\nThe computer chose " + compEvenCount + " even " + grammar1 + ", and " + (this.moveCount - compEvenCount) + " odd " + grammar2 + ".";
		gameString += "\nYou received " + this.playerBonus + " bonus points, and the computer received " + this.compBonus + " bonus points.";
		return gameString;
	}
}

