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
	public static final String deleteFromScore = "DELETE FROM Score WHERE gameid = ? AND userid = ?";

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
	
		//TODO: isUserAndGameUnique();
			
		

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

	private void isUserAndGameUnique(String gameName) {
		
		int userID=findUserID();
		int gameID=findGameID(gameName);

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
				DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

				PreparedStatement stmt = con.prepareStatement(selectGameidAndUserid)) {

			stmt.setInt(1, gameID);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			
			
			//TODO:
			if(rs.next()){
				
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void writeRating(String gameName) {
		System.out.println("Do you want to rate a game? Y or N");
		String userInput = new ReadLine().readLine().toUpperCase();
		if (userInput.equals("Y")) {

			Rating newRating = new Rating(0, 0, 0);
			newRating.setGame_id(findGameID(gameName));
			newRating.setUser_id(findUserID());
			System.out.println("Please write down your rating.");
			newRating.setUser_rating(Integer.parseInt(new ReadLine().readLine()));
			new RatingServicesMethods().addRatingToDatabase(newRating, gameName);
		}
	}

	private int findUserID() {

		String findGameID = "SELECT Userid FROM User_names WHERE User_name = ?";
		String insertNewUser = "insert into User_names (Userid, User_name) values (ids.nextval, ?)";
		String userName = System.getProperty("user.name");
		int userID = 0;

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
				DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

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

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
				DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

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
