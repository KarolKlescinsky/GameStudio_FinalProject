package gamestudio.services;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gamestudio.database.DatabaseConnectionStats;
import gamestudio.entity.Rating;
import gamestudio.usefullmethods.ReadLine;

public class RatingServicesMethods implements RatingServices {

	public static final String selectAverageAndCount = "SELECT AVG(r.RATING), count(r.RATING) FROM game g JOIN rating r ON r.GAMEID = g.GAMEID WHERE Game_name = ? GROUP BY g.GAME_NAME";
	public static final String insertIntoRating = "INSERT INTO Rating (GAMEID,USERID,RATING) VALUES (?,?,?)";
	public static final String selectGameidAndUserid = "SELECT gameid,userid FROM Rating WHERE gameid = ? AND userid= ?";
	public static final String deleteFromRating = "DELETE FROM Rating WHERE gameid = ? AND userid = ?";
	public static final String insertNewUser = "insert into User_names (Userid, User_name) values (ids.nextval, ?)";
	public static final String findUserid = "SELECT Userid FROM User_names WHERE User_name = ?";
	public static final String findGameID = "SELECT Gameid FROM Game WHERE Game_name = ?";

	public void averageRating(String Game_name) throws SQLException {

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
				DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());
				PreparedStatement stmt = con.prepareStatement(selectAverageAndCount)) {

			stmt.setString(1, Game_name);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (Game_name.length() <= 7) {
					System.out.printf("Average rating of %s: %.1f\t\t", Game_name, rs.getDouble(1));
				} else {
					System.out.printf("Average rating of %s: %.1f\t", Game_name, rs.getDouble(1));
				}
				System.out.printf("Number of ratings: %d\n", rs.getInt(2));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addRatingToDatabase(Rating newRating, String gameName) {

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
				DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());
				PreparedStatement stmt = con.prepareStatement(insertIntoRating)) {
			stmt.setInt(1, newRating.getGame_id());
			stmt.setInt(2, newRating.getUser_id());
			stmt.setInt(3, newRating.getUser_rating());
			ResultSet rs = stmt.executeQuery();
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteRatingFromDatabase(String gameName) {

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
				DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());
				PreparedStatement stmt = con.prepareStatement(deleteFromRating)) {
			stmt.setInt(1, findGameID(gameName));
			stmt.setInt(2, findUserID());
			ResultSet rs = stmt.executeQuery();
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isUserAndGameUnique(String gameName) {

		boolean isUnique = false;

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
				DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());
				PreparedStatement stmt = con.prepareStatement(selectGameidAndUserid)) {

			stmt.setInt(1, findGameID(gameName));
			stmt.setInt(2, findUserID());
			ResultSet rs = stmt.executeQuery();

			if (!rs.next()) {
				isUnique = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUnique;
	}

	public void writeRating(String gameName) {
		System.out.println("Do you want to rate a game? Y or N");
		String userInput = new ReadLine().readLine().toUpperCase();
		Rating newRating = new Rating();

		if (userInput.equals("Y")) {
			newRating.setGame_id(findGameID(gameName));
			newRating.setUser_id(findUserID());
			if (isUserAndGameUnique(gameName)) {
				userRatingInputFrom1to5(newRating);
				new RatingServicesMethods().addRatingToDatabase(newRating, gameName);
			} else {
				deleteRatingFromDatabase(gameName);
				userRatingInputFrom1to5(newRating);
				new RatingServicesMethods().addRatingToDatabase(newRating, gameName);
			}
		}
	}

	public void userRatingInputFrom1to5(Rating newRating) {
		String userRating;
		do {
			System.out.println("Please write down your rating. <1 , 5>");
			userRating = new ReadLine().readLine();
			System.out.println(Integer.parseInt(userRating));
		} while (!((Integer.parseInt(userRating) <= 5) && (Integer.parseInt(userRating) >= 1)));
		newRating.setUser_rating(Integer.parseInt(userRating));
	}

	public int findUserID() {

		String userName = System.getProperty("user.name");
		int userID = 0;
		userID = doesUserExist(userName, userID);
		if (userID == 0) {
			try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
					DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());
					PreparedStatement stmt2 = con.prepareStatement(insertNewUser)) {
				stmt2.setString(1, userName);
				stmt2.executeUpdate();
				stmt2.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		userID = doesUserExist(userName, userID);
		return userID;
	}

	public int doesUserExist(String userName, int userID) {
		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
				DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

				PreparedStatement stmt = con.prepareStatement(findUserid)) {
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

		int gameID = 0;

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
				DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());
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
}
