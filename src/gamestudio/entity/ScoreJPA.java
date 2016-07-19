package gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ScoreJPA {

	@Id
	@GeneratedValue
	private int score_id;
	private String user_name;
	private String game_name;
	private int user_score;
	
	public ScoreJPA(String user_name, String game_name, int user_score) {
		this.user_name = user_name;
		this.game_name = game_name;
		this.user_score = user_score;
	}
	
	public ScoreJPA() {

	}

	public int getScore_id() {
		return score_id;
	}
	public void setScore_id(int score_id) {
		this.score_id = score_id;
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
	public int getUser_score() {
		return user_score;
	}
	public void setUser_score(int user_score) {
		this.user_score = user_score;
	}



}
