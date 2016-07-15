package gamestudio.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScoreServicesMethods implements ScoreServices {

	public static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	public static final String USER = "gamestudiouser";
	public static final String PASSWORD = "gamestudiouser";
	public static final String QUERY = "Select User_name,Score from Score s join user_names u on u.USERID = s.USERID join game g on s.GAMEID = g.GAMEID where game_name = ?";

	public void printScoreboard(String Game_name) throws SQLException {

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

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

}
