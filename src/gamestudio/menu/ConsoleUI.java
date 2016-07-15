package gamestudio.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import gamestudio.database.DatabaseConnectionStats;
import gamestudio.entity.Comment;
import gamestudio.games.guessthenumber.GuessTheNumber;
import gamestudio.games.hangman.HangMan;
import gamestudio.games.miles.NPuzzle;
import gamestudio.games.minesweeper.Minesweeper;
import gamestudio.services.CommentServicesMethods;
import gamestudio.services.RatingServicesMethods;

public class ConsoleUI {

	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	private enum Option {
		Minesweeper, Miles, GuessTheNumber, Hangman, EXIT
	};

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
		writeComment(gameName);

	}

	private void guessTheNumber() throws SQLException {
		String gameName = "GuessTheNumber";
		writeComment(gameName);
		GuessTheNumber.startGuessTheNumber();

	}

	private void miles() throws SQLException {
		String gameName = "Miles";
		NPuzzle.startMiles();
		writeComment(gameName);

	}

	private void minesweeper() throws SQLException {
		String gameName = "Minesweeper";
		Minesweeper.startMinesweeper();
		writeComment(gameName);

	}

	private void writeComment(String gameName) {
		System.out.println("Do you want to write a comment? Y or N");
		String userInput = readLine().toUpperCase();
		if (userInput.equals("Y")) {

			Comment newComment = new Comment(0, 0, null);

			newComment.setGame_id(findGameID(gameName));
			newComment.setUser_id(findUserID());

			System.out.println("Please write down your comment.");
			newComment.setUser_comment(readLine());
			new CommentServicesMethods().addComment(newComment, gameName);
		}
	}

	private int findUserID() {

		String findGameID = "SELECT Userid FROM User_names WHERE User_name = ?";
		String insertNewUser = "insert into User_names (Userid, User_name) values (ids.nextval, ?)";
		String userName = System.getProperty("user.name");
		int userID = 0;

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(), DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

				PreparedStatement stmt = con.prepareStatement(findGameID)) {
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				userID = rs.getInt(1);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (userID == 0) {
			try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(), DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

					PreparedStatement stmt2 = con.prepareStatement(insertNewUser)) {
				stmt2.setString(1, userName);
				stmt2.executeUpdate();

				stmt2.close();
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(), DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

				PreparedStatement stmt = con.prepareStatement(findGameID)) {
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				userID = rs.getInt(1);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userID;
	}

	private int findGameID(String gameName) {

		String findGameID = "SELECT Gameid FROM Game WHERE Game_name = ?";
		int gameID = 0;

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(), DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

				PreparedStatement stmt = con.prepareStatement(findGameID)) {
			stmt.setString(1, gameName);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				gameID = rs.getInt(1);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gameID;

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
