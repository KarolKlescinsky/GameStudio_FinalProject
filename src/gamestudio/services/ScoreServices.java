package gamestudio.services;

import java.sql.SQLException;

public interface ScoreServices {
	
	public void printScoreboard(String Game_name) throws SQLException;

}
