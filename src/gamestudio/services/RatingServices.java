package gamestudio.services;

import java.util.List;

public interface RatingServices {

    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
    public static final String USER = "register";
    public static final String PASSWORD = "register";
    public static final String QUERY = "Select g.GAME_NAME, count(r.RATING), AVG(r.RATING) from game g join rating r on r.GAMEID = g.GAMEID GROUP BY g.GAME_NAME;";
	
	public void addRating();
	public List listOfRatings();
	
	public void averageRating();
	
}
