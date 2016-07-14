package gamestudio.games.minesweeper.consoleui;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gamestudio.games.minesweeper.Minesweeper;
import gamestudio.games.minesweeper.core.Clue;
import gamestudio.games.minesweeper.core.Field;
import gamestudio.games.minesweeper.core.GameState;
import gamestudio.games.minesweeper.core.Mine;
import gamestudio.games.minesweeper.core.Tile;
import gamestudio.games.minesweeper.core.Tile.State;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.
	 * Field)
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		do {
			update();
			processInput();

			if (field.getState() == GameState.SOLVED) {
				System.out.println("CONGRATULATIONS! YOU WON THE GAME!");
				System.exit(0);
			}

			if (field.getState() == GameState.FAILED) {
				System.out.println("HAHA! NOOB! YOU LOST!");
				System.exit(0);
			}

		} while (true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
	@Override
	public void update() {

		char rowIndex = 'A';
		int i = 1;

		System.out.println("Number of remaining mines: " + field.getRemainingMineCount());
		System.out.print("  ");

		for (i = 0; i < field.getRowCount(); i++) {
			System.out.print(i + " ");
		}

		System.out.println();

		for (int row = 0; row < field.getRowCount(); row++) {

			System.out.print(rowIndex + " ");
			rowIndex++;

			for (int column = 0; column < field.getColumnCount(); column++) {

				if (field.getTile(row, column).getState() == State.CLOSED) {
					System.out.printf("-");
					System.out.print(" ");
				} else {
					if (field.getTile(row, column).getState() == State.MARKED) {
						System.out.printf("M ");
					} else {
						if (field.getTile(row, column).getState() == State.OPEN) {

							if (field.getTile(row, column) instanceof Mine) {
								System.out.printf("X ");
							} else {
								Tile tempTile = field.getTile(row, column);
								Clue tempClue = (Clue) tempTile;
								tempClue.getValue();
								System.out.print(tempClue.getValue() + " ");
							}
						}
					}
				}
			}
			System.out.println();
		}

	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {

		System.out.println("Your time: " + (Minesweeper.getInstance().getPlayingSeconds()) / 1000 + " seconds");

		System.out.println(
				"Please insert: X for end of game... or MA1 – selection of tile in row A and column 1... or OB4 – uncover tile in row B and column 4");

		try {

			String myInputString = readLine();
			handleInput(myInputString);

		} catch (WrongFormatException e) {
			System.out.println(e.getMessage());
		}

	}

	void handleInput(String myInputString) throws WrongFormatException {

		myInputString = myInputString.toUpperCase();
		Pattern pattern2 = Pattern.compile("X");
		Matcher matcher2 = pattern2.matcher(myInputString);

		if (matcher2.matches()) {
			field.setState(GameState.FAILED);
			System.out.println("Game over!");
		}

		Pattern pattern = Pattern.compile("O([A-Ia-i])([0-8])");
		Matcher matcher = pattern.matcher(myInputString);
		Pattern pattern1 = Pattern.compile("M([A-Ia-i])([0-8])");
		Matcher matcher1 = pattern1.matcher(myInputString);

		if (matcher.matches()) {

			int w = Integer.parseInt(matcher.group(2));
			char riadok = matcher.group(1).charAt(0);
			field.openTile(Character.getNumericValue(riadok) - 10, w);

		} else {
			if (matcher1.matches()) {

				int w = Integer.parseInt(matcher1.group(2));
				char riadok = matcher1.group(1).charAt(0);
				field.markTile(Character.getNumericValue(riadok) - 10, w);

			} else {
				throw new WrongFormatException("Wrong input baby!");
			}
		}
	}

}