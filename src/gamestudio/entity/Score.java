package gamestudio.entity;

public class Score {

	private int user_id;
	private int game_id;
	private int user_score;
	
	public Score(int user_id, int game_id, int user_score) {
		this.user_id = user_id;
		this.game_id = game_id;
		this.user_score = user_score;
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
	public int getUser_score() {
		return user_score;
	}
	public void setUser_score(int user_score) {
		this.user_score = user_score;
	}
	
	
	
}
