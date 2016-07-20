package gamestudio.entity.entityjpql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GameJPQL {
	
	@Id
	@GeneratedValue
	private  int gameID;
	private String gameName;
	
	public GameJPQL() {

	}
	public GameJPQL(int gameID, String gameName) {
		this.gameID = gameID;
		this.gameName = gameName;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	
	
}
