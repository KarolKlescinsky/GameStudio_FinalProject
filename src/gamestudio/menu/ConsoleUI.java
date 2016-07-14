package gamestudio.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import gamestudio.games.guessthenumber.GuessTheNumber;
import gamestudio.games.hangman.HangMan;
import gamestudio.games.miles.NPuzzle;
import gamestudio.games.minesweeper.Minesweeper;

public class ConsoleUI {
	
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	private enum Option {
		MINESWEEPER, MILES, GUESSTHENUMBER, HANGMAN, EXIT
	};

	public void run() throws IOException, ParseException {
		while (true) {
			switch (showMenu()) {

			case MINESWEEPER:
				minesweeper();
				break;
			case MILES:
				miles();
				break;
			case GUESSTHENUMBER:
				guessTheNumber();
				break;
			case HANGMAN:
				hangman();
				break;
			case EXIT:
				return;
			}
		}
	}
	
	private void hangman() {
		HangMan.startHangMan();
	}

	private void guessTheNumber() {
		GuessTheNumber.startGuessTheNumber();
	}

	private void miles() {
		NPuzzle.startMiles();
	}

	private void minesweeper() {
		Minesweeper.startMinesweeper();
	}

	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}

	private String readLine() {

		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
	
}
