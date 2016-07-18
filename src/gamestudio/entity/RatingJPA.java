package gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RatingJPA {


	@Id
	@GeneratedValue
	private int rating_id;
	private int user_id;
	private int game_id;
	private int user_rating;

	public RatingJPA(int user_id, int game_id, int user_score) {

		this.user_id = user_id;
		this.game_id = game_id;
		this.user_rating = user_score;
	}

	public RatingJPA() {
		this(0, 0, 0);
	}
	

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getUser_rating() {
		return user_rating;
	}

	public void setUser_rating(int user_rating) {
		this.user_rating = user_rating;
	}

}
