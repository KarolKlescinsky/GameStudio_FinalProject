package gamestudio.database;

public class DatabaseConnectionStats {

	private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	private static final String USER = "gamestudiouser";
	private static final String PASSWORD = "gamestudiouser";
	
	public static String getUrl() {
		return URL;
	}
	public static String getUser() {
		return USER;
	}
	public static String getPassword() {
		return PASSWORD;
	}
	
	
}
