package gamestudio.games.minesweeper;

import java.sql.SQLException;

import gamestudio.games.minesweeper.consoleui.ConsoleUI;
import gamestudio.games.minesweeper.consoleui.UserInterface;
import gamestudio.games.minesweeper.core.Field;
import gamestudio.services.ScoreServicesMethods;

/**
 * Main application class.
 */
public class Minesweeper {
	/** User interface. */
	private UserInterface userInterface;
	private long startMillis;
	private BestTimes bestTimes = new BestTimes();
	private static Minesweeper instance;

	/**
	 * Constructor.
	 */

	public int getPlayingSeconds() {
		int timePlayed = (int) (System.currentTimeMillis() - startMillis);
		return timePlayed;
	}

	public static Minesweeper getInstance() {
		return instance;
	}

	private Minesweeper() {
		instance = this;
		startMillis = System.currentTimeMillis();
		userInterface = new ConsoleUI();
		Field field = new Field(9, 9, 10);
		userInterface.newGameStarted(field);
	}

	public BestTimes getBestTimes() {
		return bestTimes;
	}

	/**
	 * Main method.
	 * @param game_name 
	 * 
	 * @param args
	 *            arguments
	 * @return 
	 * @throws SQLException 
	 */
	public static int startMinesweeper() throws SQLException {
		String Game_name="Minesweeper";
		new ScoreServicesMethods().printScoreboard(Game_name);
		new Minesweeper();
		int userScore;
		return userScore=10;
	}
}
