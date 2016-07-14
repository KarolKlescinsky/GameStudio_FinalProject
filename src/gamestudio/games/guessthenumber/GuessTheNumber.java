package gamestudio.games.guessthenumber;

import java.util.Random;

public class GuessTheNumber {

	private int guessedNumber;
	private int playersGuess;
	
	private int randomNumberGeneratoion(){
		Random ran = new Random();
		return guessedNumber= ran.nextInt(6) + 5;
		}
	
	public static void startGuessTheNumber() {
		System.out.println("Not implemented yet :( ");
	}
}
