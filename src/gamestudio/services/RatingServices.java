package gamestudio.services;

import java.sql.SQLException;

import gamestudio.entity.Rating;

public interface RatingServices {
	
	public void averageRating(String Game_name) throws SQLException;
	public void addRatingToDatabase(Rating newRating, String gameName);
	public void deleteRatingFromDatabase(String gameName);
	public boolean isUserAndGameUnique(String gameName);
	public void writeRating(String gameName);
	public void userRatingInputFrom1to5(Rating newRating);
	public int findUserID();
	public int doesUserExist(String userName, int userID);
}
