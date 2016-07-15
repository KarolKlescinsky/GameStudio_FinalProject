package gamestudio.services;

import java.sql.SQLException;

public interface RatingServices {
	

	public void averageRating(String Game_name) throws SQLException;
	
}
