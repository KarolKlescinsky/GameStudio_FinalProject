package gamestudio.services;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gamestudio.database.DatabaseConnectionStats;
import gamestudio.entity.Score;

public class ScoreServicesMethods implements ScoreServices {

	public static final String QUERY = "Select User_name,Score from Score s join user_names u on u.USERID = s.USERID join game g on s.GAMEID = g.GAMEID where game_name = ?";
	public static final String QUERY1 = "insert into Score (Scoreid,GAMEID,Userid,Score) values (ids.nextval, ?,?,?)";

	public void printScoreboard(String Game_name) throws SQLException {

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
				DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

				PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(1, Game_name);
			ResultSet rs = stmt.executeQuery();

			System.out.println("SCOREBOARD:");
			while (rs.next()) {
				System.out.printf("%s\t %s\n", rs.getString(1), rs.getString(2));
			}
			System.out.println();
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addScoreToDatabase(Score newScore, String gameName, int userScore) {

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(),
				DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

				PreparedStatement stmt = con.prepareStatement(QUERY1)) {
			stmt.setInt(1, newScore.getGame_id());
			stmt.setInt(2, newScore.getUser_id());
			stmt.setInt(3, newScore.getUser_score());
			ResultSet rs = stmt.executeQuery();
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addScore(String gameName, int userScore) {
		Score newScore = new Score(0, 0, 0);
		newScore.setGame_id(findGameID(gameName));
		newScore.setUser_id(findUserID());
		newScore.setUser_score(userScore);
		new ScoreServicesMethods().addScoreToDatabase(newScore, gameName, userScore);
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
