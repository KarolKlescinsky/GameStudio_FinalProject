package gamestudio.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScoreServicesMethods {

	public static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	public static final String USER = "gamestudiouser";
	public static final String PASSWORD = "gamestudiouser";
    public static final String QUERY = "SELECT userid, user_name FROM User_names";

	public static void connectToDatabase() throws SQLException {

		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(QUERY);



		while (rs.next()) {
			System.out.printf("%4d %-32s%n", rs.getInt(1), rs.getString(2));
		}
		
		rs.close();
		stmt.close();
		con.close();

	}
}
