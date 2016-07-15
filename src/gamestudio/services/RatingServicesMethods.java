package gamestudio.services;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gamestudio.database.DatabaseConnectionStats;

public class RatingServicesMethods implements RatingServices {


	public static final String QUERY = "Select AVG(r.RATING), count(r.RATING) from game g join rating r on r.GAMEID = g.GAMEID WHERE Game_name = ? GROUP BY g.GAME_NAME";

	public void averageRating(String Game_name) throws SQLException {

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(), DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

			PreparedStatement stmt = con.prepareStatement(QUERY)) {
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
}
