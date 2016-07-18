package gamestudio.menu;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;

import gamestudio.games.guessthenumber.GuessTheNumber;
import gamestudio.games.hangman.HangMan;
import gamestudio.games.miles.NPuzzle;
import gamestudio.games.minesweeper.Minesweeper;
import gamestudio.services.CommentServicesMethods;
import gamestudio.services.RatingServicesMethods;
import gamestudio.services.ScoreServicesMethods;
import gamestudio.usefullmethods.ReadLine;

public class ConsoleUI {

	public void run() throws IOException, ParseException, SQLException {
		while (true) {
			switch (showMenu()) {

			case Minesweeper:
				minesweeper();
				break;
			case Miles:
				miles();
				break;
			case GuessTheNumber:
				guessTheNumber();
				break;
			case Hangman:
				hangman();
				break;
			case EXIT:
				return;
			}
		}
	}

	private void hangman() throws SQLException {
		String gameName = "Hangman";
		String userName = System.getProperty("user.name");
		// new CommentServiceJpa().addCommentToDatabase(new CommentJPA(userName,
		// gameName, "Jpa test"));
		new HangMan().startHangMan();
		new CommentServicesMethods().writeComment(gameName, userName);
		new ScoreServicesMethods().addScore(gameName, new HangMan().sendScore());
		new RatingServicesMethods().writeRating(gameName);
	}

	private void guessTheNumber() throws SQLException {
		String gameName = "GuessTheNumber";
		String userName = System.getProperty("user.name");
		new GuessTheNumber().startGuessTheNumber();
		new CommentServicesMethods().writeComment(gameName, userName);
		new ScoreServicesMethods().addScore(gameName, new GuessTheNumber().sendScore());
		new RatingServicesMethods().writeRating(gameName);
	}

	private void miles() throws SQLException {
		String gameName = "Miles";
		String userName = System.getProperty("user.name");
		new NPuzzle().startMiles();
		new CommentServicesMethods().writeComment(gameName, userName);
		new ScoreServicesMethods().addScore(gameName, new NPuzzle().sendScore());
		new RatingServicesMethods().writeRating(gameName);
	}

	private void minesweeper() throws SQLException {
		String gameName = "Minesweeper";
		String userName = System.getProperty("user.name");
		new Minesweeper().startMinesweeper();
		new CommentServicesMethods().writeComment(gameName, userName);
		new ScoreServicesMethods().addScore(gameName, new Minesweeper().sendScore());
		new RatingServicesMethods().writeRating(gameName);
	}

	private Option showMenu() throws SQLException {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			// System.out.printf("%d. %s\t\t\t\t\t\t", option.ordinal() + 1,
			// option,(option.toString().length() <= 11 ? "\t" : "\t\t"));
			if (option.toString().length() <= 11) {
				System.out.printf("%d. %s\t\t\t\t\t\t", option.ordinal() + 1, option);
			} else {
				System.out.printf("%d. %s\t\t\t\t\t", option.ordinal() + 1, option);
			}
			String Game_name = option.toString();
			new RatingServicesMethods().averageRating(Game_name);
		}
		System.out.println();
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(new ReadLine().readLine());
		} while (selection <= 0 || selection > Option.values().length);
		return Option.values()[selection - 1];
	}

}
