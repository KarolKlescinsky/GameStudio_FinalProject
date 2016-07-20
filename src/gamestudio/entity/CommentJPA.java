package gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class CommentJPA {

	//@Id
	//@GeneratedValue
	private int comment_id;
	private String user_name;
	private String game_name;
	private String user_comment;
		
	public CommentJPA(String user_name, String game_name, String user_comment) {
		this.user_name = user_name;
		this.game_name = game_name;
		this.user_comment = user_comment;
	}
	public CommentJPA() {
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
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
	public String getUser_comment() {
		return user_comment;
	}
	public void setUser_comment(String user_comment) {
		this.user_comment = user_comment;
	}
	
}
