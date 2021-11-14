public class VideoGame {
	private int gameId;
	private int genreId;
	private int publisherId;
	private int platformId;
	private int releaseYear;
	private String gameName;
	private String gameDescription;
	
	public VideoGame(int gameId) {
		this.gameId = gameId;
	}
	
	public VideoGame(String gameName) {
		this.gameName = gameName;
	}
	
	public VideoGame(int gameId, int genreId, int publisherId, int platformId, int releaseYear, String gameName, String gameDescription) {
		this.gameId = gameId;
		this.genreId = genreId;
		this.publisherId = publisherId;
		this.platformId = platformId;
		this.releaseYear = releaseYear;
		this.gameName = gameName;
		this.gameDescription = gameDescription;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public int getGenreId() {
		return genreId;
	}
	
	public int getPublisherId() {
		return publisherId;
	}
	
	public int getPlatformId() {
		return platformId;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public String getGameDescription() {
		return gameDescription;
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}
}
