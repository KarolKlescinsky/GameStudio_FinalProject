package gamestudio.entity;

public class Rating {
	
	private String playerName;
	private String gameName;
	private int rating;
		
	public Rating(String playerName, String gameName, int rating) {
		this.playerName = playerName;
		this.gameName = gameName;
		this.rating = rating;
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
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}

}
