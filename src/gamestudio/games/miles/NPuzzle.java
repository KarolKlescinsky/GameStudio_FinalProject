package gamestudio.games.miles;

import java.sql.SQLException;

import java.util.Random;
import gamestudio.services.ScoreServicesMethods;
import gamestudio.usefullmethods.ReadLine;

public class NPuzzle {

	static long start = System.currentTimeMillis();
	long elapsedTimeMillis = 0;
	private GameState state = GameState.PLAYING;
	private int sirka, dlzka;

	public NPuzzle(int sirka, int dlzka) {
		this.dlzka = dlzka;
		this.sirka = sirka;
	}

	public NPuzzle() {
		this(4, 4);
	}

	public int getSirka() {
		return sirka;
	}

	public int getDlzka() {
		return dlzka;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public int[] generate(int[] mojePole) {

		for (int i = 0; i < getDlzka() * getSirka(); i++) {
			mojePole[i] = i + 1;
			mojePole[(getDlzka() * getSirka()) - 1] = 0;
		}
		shuffleArray(mojePole);
		return mojePole;
	}

	public int[][] createPuzzle(int[][] moje2Pole, int[] mojePole) {

		int iteration = 0;
		for (int x = 0; x < getSirka(); x++) {
			for (int y = 0; y < getDlzka(); y++) {
				moje2Pole[x][y] = mojePole[iteration];
				iteration++;
			}
		}
		return moje2Pole;
	}

	public void printNPuzzle(int[][] moje2Pole) {

		for (int x = 0; x < getSirka(); x++) {
			for (int y = 0; y < getDlzka(); y++) {
				if (moje2Pole[x][y] < 10) {
					System.out.print(" " + moje2Pole[x][y] + "  ");
				} else {
					System.out.print(moje2Pole[x][y] + "  ");
				}
				if (y == getDlzka() - 1) {
					System.out.println();
				}
			}
		}
	}

	private int[] shuffleArray(int[] mojePole) {

		int index, temp;
		Random random = new Random();
		for (int i = mojePole.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = mojePole[index];
			mojePole[index] = mojePole[i];
			mojePole[i] = temp;
		}
		return mojePole;
	}

	private int[][] update(int[][] moje2Pole) {
		getStartTime();
		printNPuzzle(moje2Pole);
		System.out.println("Enter 'UP' or 'DOWN' or 'LEFT' or 'RIGHT' .");
		System.out.println("Or if you are lazy... Enter 'W' or 'A' or 'S' or 'D' for movement.");
		System.out.println("Or if you are really really really lazy... Enter 'exit'.");
		System.out.println("Enter 'new' if you want to start a new game.");
		String input = new ReadLine().readLine().toUpperCase();

		try {
			input = input.toUpperCase();
		} catch (NumberFormatException e) {
			// It never happens
		}

		for (int x = 0; x < 4; x++) {
			boolean change = false;
			for (int y = 0; y < 4; y++) {

				if (moje2Pole[x][y] == 0) {

					if (input.equals("W") || input.equals("UP")) {
						moveUp(moje2Pole, x, y);
						break;
					} else {

						if (input.equals("S") || input.equals("DOWN")) {
							moveDown(moje2Pole, x, y);
							break;
						} else {

							if (input.equals("A") || input.equals("LEFT")) {
								moveLeft(moje2Pole, x, y);
								break;
							} else {

								if (input.equals("D") || input.equals("RIGHT")) {
									moveRight(moje2Pole, x, y);
									break;
								} else {
									exitAndNewGame(input);
								}
								if (change) {
									break;
								}
							}
						}
					}
				}
			}
		}
		return moje2Pole;
	}

	public void exitAndNewGame(String input) {
		if (input.equals("EXIT")) {
			System.out.println("Your time was: " + getStartTime() + "seconds");
			System.exit(0);
		} else {
			if (input.equals("NEW")) {
				StartGame();
			}
		}
	}

	public void moveRight(int[][] moje2Pole, int length, int width) {
		if (width == 0) {
			System.out.println("Cant move there.");
		} else {
			moje2Pole[length][width] = moje2Pole[length][width - 1];
			moje2Pole[length][width - 1] = 0;
		}
	}

	public void moveLeft(int[][] moje2Pole, int length, int width) {
		if (width == getDlzka() - 1) {
			System.out.println("Cant move there.");
		} else {
			moje2Pole[length][width] = moje2Pole[length][width + 1];
			moje2Pole[length][width + 1] = 0;
		}
	}

	public void moveDown(int[][] moje2Pole, int length, int width) {
		if (length == 0) {
			System.out.println("Cant move there.");
		} else {
			moje2Pole[length][width] = moje2Pole[length - 1][width];
			moje2Pole[length - 1][width] = 0;
		}
	}

	public void moveUp(int[][] moje2Pole, int length, int width) {
		if (length == getDlzka() - 1) {
			System.out.println("Cant move there. Try again.");
		} else {
			moje2Pole[length][width] = moje2Pole[length - 1][width];
			moje2Pole[length + 1][width] = 0;
		}
	}

	public boolean isSorted(int moje2Pole[][]) {

		boolean help = false;
		for (int i = 0; i < moje2Pole.length; i++) {
			for (int j = 0; j < moje2Pole.length; j++) {
				if (moje2Pole[i][j] < moje2Pole[i][j + 1]) {
					help = true;
				} else {
					if (moje2Pole[getDlzka() - 1][getSirka() - 1] == 0 && help) {
						help = true;
					} else {
						help = false;
					}
				}
			}
		}

		if (help) {
			setState(GameState.SOLVED);
		}

		return help;
	}

	public void Game(int[][] moje2Pole) {

		do {
			update(moje2Pole);
			// wisSorted(moje2Pole);
			if (getState() == GameState.SOLVED) {
				System.out.println("CONGRATULATIONS! YOU WON THE GAME!");
				System.out.println("Your time was: " + getStartTime() + "seconds");
				System.exit(0);
			}
		} while (true);
	}

	private static void StartGame() {

		NPuzzle generate = new NPuzzle();

		int[] mojePole = new int[generate.getDlzka() * generate.getSirka()];
		int[][] moje2Pole = new int[generate.getDlzka()][generate.getSirka()];

		System.out.println("Game started!");
		System.out.println("Clue: Character '0' represents blank space.");
		generate.generate(mojePole);
		NPuzzle generate1 = new NPuzzle();
		generate1.createPuzzle(moje2Pole, mojePole);
		NPuzzle start = new NPuzzle();
		start.Game(moje2Pole);
	}

	public static long getStartTime() {

		long elapsedTimeMillis = (System.currentTimeMillis() - start) / 1000;
		System.out.println("Time: " + elapsedTimeMillis + " seconds.");
		return elapsedTimeMillis;
	}

	public void startMiles() throws SQLException {
		String Game_name = "Miles";
		new ScoreServicesMethods().printScoreboard(Game_name);
		StartGame();
	}

	public int sendScore() {
		int userScore = 0;
		// TODO: if(isSorted(moje2Pole)){
		userScore = (int) getStartTime();
		// }
		return userScore;
	}

}
