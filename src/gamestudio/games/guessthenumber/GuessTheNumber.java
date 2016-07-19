package gamestudio.games.guessthenumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import gamestudio.services.ScoreServicesMethods;

public class GuessTheNumber {

	private static int guessCounter=0;
	private int guessedNumber;
	private int playersGuess;
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	private int randomNumberGeneratoion() {
		return guessedNumber = (int) (Math.random() * 1000);
	}

	private boolean isGuessed() {
		if (playersGuess == guessedNumber) {
			System.out.println("Congratulations, your guess is correct.");
			return true;
		}
		if (playersGuess > guessedNumber) {
			System.out.println("Bad guess. The number you are trying to guess is lower.");
		}
		if (playersGuess < guessedNumber) {
			System.out.println("Bad guess. The number you are trying to guess is higher.");
		}
		return false;
	}

	private void guessTheNumberUI() {
		randomNumberGeneratoion();
		System.out.println("Welcome traveler.");
		System.out.println("I guess a number from 0 to 1000.");
		do {
			System.out.println("Write down your guess.");
			playersGuess = Integer.parseInt(readLine());
			guessCounter++;
		} while (!isGuessed());

	}

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
	public int sendScore(){
		int userScore=0;
		if(isGuessed()){
		userScore = 100 - guessCounter;
		}
		return userScore;
	}

	public void startGuessTheNumber() throws SQLException {
		String Game_name = "GuessTheNumber";
		new ScoreServicesMethods().printScoreboard(Game_name);
		new GuessTheNumber().guessTheNumberUI();
//		GuessTheNumber gameStart = new GuessTheNumber();
//		gameStart.guessTheNumberUI();
	}
}
