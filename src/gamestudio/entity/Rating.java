package gamestudio.entity;

public class Rating {

	private int user_id;
	private int game_id;
	private int user_rating;

	public Rating(int user_id, int game_id, int user_rating) {
		this.user_id = user_id;
		this.game_id = game_id;
		this.user_rating = user_rating;
	}
	
	public Rating() {
		this(0,0,0);
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
