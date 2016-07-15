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
		HangMan.startHangMan();
		new CommentServicesMethods().writeComment(gameName);

	}

	private void guessTheNumber() throws SQLException {
		String gameName = "GuessTheNumber";
		GuessTheNumber.startGuessTheNumber();
		new CommentServicesMethods().writeComment(gameName);

	}

	private void miles() throws SQLException {
		String gameName = "Miles";
		NPuzzle.startMiles();
		new CommentServicesMethods().writeComment(gameName);

	}

	private void minesweeper() throws SQLException {
		String gameName = "Minesweeper";
		Minesweeper.startMinesweeper();
		new CommentServicesMethods().writeComment(gameName);

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
