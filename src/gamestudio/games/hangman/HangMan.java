package gamestudio.games.hangman;

import java.sql.SQLException;
import java.util.*;

import gamestudio.services.ScoreServicesMethods;

public class HangMan {

	public void startHangMan() throws SQLException {

		char name[] = {};
		int life = 13, win = 0;

		String Game_name = "Hangman";
		new ScoreServicesMethods().printScoreboard(Game_name);

		System.out.println("Enter the word by characters");

		List<String> list = new ArrayList<String>();
		@SuppressWarnings("resource")
		Scanner stdin = new Scanner(System.in);

		do {
			System.out.println("Current list is " + list);
			System.out.println("Add more? (y/n)");
			if (stdin.next().startsWith("y")) {
				System.out.println("Enter : ");
				list.add(stdin.next());
			} else {
				break;
			}
		} while (true);

		System.out.println("List is " + list);
		String[] arr = list.toArray(new String[0]);
		System.out.println("Array is " + Arrays.toString(arr));

		while (life > 0) {

			System.out.println("Lifes: " + life);
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter a character: ");
			char c = scanner.next().charAt(0);
			System.out.println("You have entered: " + c);

			if (life == 1) {
				System.out.println("You lost!");
				break;
			}

			for (int i = 0; i < name.length; i++) {

				if (c == name[i]) {
					System.out.println("Good job! Your guess " + c + " was right!");
					i = 0;
					win++;

					if (win == name.length) {
						System.out.println("You won!");
						break;
					}

					break;
				} else {
					System.out.println("Bad one! Your guess " + c + " was wrong!");

				}
				if (i == (name.length - 1)) {
					life--;
				}
			}
			if (win == name.length) {
				break;
			}
		}
	}

	public int sendScore() {
		int userScore = 10;
		return userScore;
	}
}