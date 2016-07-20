package gamestudio.entity.entityjpql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PlayerJPQL {
	
	@Id
	@GeneratedValue
	private int playerID;
	private String playerName;

	public PlayerJPQL() {
	}
	public PlayerJPQL(int playerID, String playerName) {
		this.playerID = playerID;
		this.playerName = playerName;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	

}
