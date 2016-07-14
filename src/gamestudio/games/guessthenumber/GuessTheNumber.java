package gamestudio.games.guessthenumber;

import java.sql.SQLException;
import java.util.Random;

public class GuessTheNumber {

	private int guessedNumber;
	private int playersGuess;
	
	private int randomNumberGeneratoion(){
		Random ran = new Random();
		return guessedNumber= ran.nextInt(6) + 5;
		}
	
	public static void startGuessTheNumber() throws SQLException {
		
		String Game_name="GuessTheNumber";
		gamestudio.services.ScoreServicesMethods.connectToDatabase(Game_name);
		System.out.println("Not implemented yet :( ");
	}
}
