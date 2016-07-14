package gamestudio.services;

import java.util.List;

public interface RatingServices {

    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
    public static final String USER = "register";
    public static final String PASSWORD = "register";
    public static final String QUERY = "INSERT INTO users_table (user_id, user_name , user_phone) VALUES (?, ?, ?)";
	
	public void addRating();
	public List listOfRatings();
	
}
