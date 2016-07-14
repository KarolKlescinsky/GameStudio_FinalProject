package gamestudio.entity;

public class Score {

	private String playerName;
	private String gameName;
	private int gameScore;
	
	public Score(String playerName, String gameName, int gameScore) {
		this.playerName = playerName;
		this.gameName = gameName;
		this.gameScore = gameScore;
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
	
	public int getGameScore() {
		return gameScore;
	}
	
	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}
		
}
