package gamestudio.entity;

public class Comment {

	private int user_id;
	private int game_id;
	private String user_comment;
	
	public Comment(int user_id, int game_id, String user_comment) {
		this.user_id = user_id;
		this.game_id = game_id;
		this.user_comment = user_comment;
	}
	
	public Comment() {
		this(0,0,"hhhh");
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

	public String getUser_comment() {
		return user_comment;
	}

	public void setUser_comment(String user_comment) {
		this.user_comment = user_comment;
	}
	
	
	

		

	
}
