package gamestudio.entity;

public class Comment {

	private String playerName;
	private String gameName;
	private String playersComment;
		
	public Comment(String playerName, String gameName, String playersComment) {
		this.playerName = playerName;
		this.gameName = gameName;
		this.playersComment = playersComment;
	}

	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public String getPlayersComment() {
		return playersComment;
	}
	
	public void setPlayersComment(String playersComment) {
		this.playersComment = playersComment;
	}
		
}
