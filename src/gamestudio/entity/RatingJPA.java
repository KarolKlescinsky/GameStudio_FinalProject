package gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RatingJPA {


	@Id
	@GeneratedValue
	private int rating_id;
	private String user_name;
	private String game_name;
	private int user_rating;
	
	public RatingJPA(String user_name, String game_name, int user_rating) {
		super();
		this.user_name = user_name;
		this.game_name = game_name;
		this.user_rating = user_rating;
	}
	
	
	
	public RatingJPA() {
		super();
	}



	public int getRating_id() {
		return rating_id;
	}
	public void setRating_id(int rating_id) {
		this.rating_id = rating_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getGame_name() {
		return game_name;
	}
	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}
	public int getUser_rating() {
		return user_rating;
	}
	public void setUser_rating(int user_rating) {
		this.user_rating = user_rating;
	}
	
	

	

}
