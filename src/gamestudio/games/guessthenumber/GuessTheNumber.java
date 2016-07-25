
package gamestudio.games.guessthenumber;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class GuessTheNumber {

	private static int guessCounter=0;
	private int guessedNumber;
	private int playersGuess;
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
	public GuessTheNumber(int guessedNumber, int playersGuess) {
		this.guessedNumber = guessedNumber;
		this.playersGuess = playersGuess;
	}
	
	

	public GuessTheNumber() {
	}



	public int getGuessedNumber() {
		return guessedNumber;
	}
	public void setGuessedNumber(int guessedNumber) {
		this.guessedNumber = guessedNumber;
	}
	public int getPlayersGuess() {
		return playersGuess;
	}
	public void setPlayersGuess(int playersGuess) {
		this.playersGuess = playersGuess;
	}

	private boolean isGuessed() {
		if (getPlayersGuess() == getGuessedNumber()) {
			System.out.println("Congratulations, your guess is correct.");
			return true;
		}
		if (getPlayersGuess() > getGuessedNumber()) {
			System.out.println("Bad guess. The number you are trying to guess is lower.");
		}
		if (getPlayersGuess() < getGuessedNumber()) {
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
			setPlayersGuess(Integer.parseInt(readLine()));
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
	
	public int randomNumberGeneratoion() {
		return guessedNumber = (int) (Math.random() * 1000);
	}

	public void startGuessTheNumber() throws SQLException {
		String Game_name = "GuessTheNumber";
	//	new ScoreServicesMethods().printScoreboard(Game_name);
		new GuessTheNumber(guessedNumber, guessedNumber).guessTheNumberUI();
//		GuessTheNumber gameStart = new GuessTheNumber();
//		gameStart.guessTheNumberUI();
	}
}
