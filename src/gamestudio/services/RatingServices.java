package gamestudio.services;

import java.sql.SQLException;

import gamestudio.entity.Rating;

public interface RatingServices {
	
	public void averageRating(String Game_name) throws SQLException;
	public void addRatingToDatabase(Rating newRating, String gameName);
	
}
