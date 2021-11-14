import java.sql.*;
import java.util.ArrayList;

/**
 * Rental Database class to instantiate a connection to the database defined in PrivateInformation.java
 * (used to perform SELECT and DML statements on the connection)
 * @author benjamingarcia10
 *
 */
public class RentalDatabase {
	private Connection connection = null;
	
	public RentalDatabase() {
		try {
			this.connection = DriverManager.getConnection(PrivateInformation.DB_URL, PrivateInformation.DB_USER, PrivateInformation.DB_PASS);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check the console output.");
			e.printStackTrace();
		}

		if (this.connection == null) {
			System.out.println("Failed to make connection.");
		}
	}
	
	public boolean isConnected() {
		return this.connection != null;
	}
	
	public void close() {
		try {
			if (this.connection != null) {
				this.connection.close();
			}
		} catch (SQLException e) {
			// Can't do anything here
		}
	}
	
	/**
	 * Retrieve all games in database
	 * @return ArrayList<VideoGame> - list of all video games
	 */
	public ArrayList<VideoGame> getAllGames() {
		Statement stmt = null;
		ArrayList<VideoGame> games = new ArrayList<>();
		if (this.connection != null) {
			try {
				stmt = this.connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM games");
				while (rs.next()) {
					int gameId = rs.getInt("game_id");
					int genreId = rs.getInt("genre_id");
					int publisherId = rs.getInt("publisher_id");
					int platformId = rs.getInt("platform_id");
					int releaseYear = rs.getInt("release_year");
					String gameName = rs.getString("game_name");
					String gameDescription = rs.getString("game_description");
					games.add(new VideoGame(gameId, genreId, publisherId, platformId, releaseYear, gameName, gameDescription));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				while (e != null) {
					System.out.println("SQL Exception Code " + e.getErrorCode());
					System.out.println("SQLState " + e.getSQLState());
					System.out.println("Error Message " + e.getMessage());
					e = e.getNextException();
				}
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
				} catch (SQLException e2) {
					// Can't do anything here
				}
			}
		}
		return games;
	}
	
	/**
	 * Retrieve all games for a specific genre name in database
	 * @return ArrayList<VideoGame> - list of all video games
	 */
	public ArrayList<VideoGame> getAllGamesByGenre(String genre) {
		Statement stmt = null;
		ArrayList<VideoGame> games = new ArrayList<>();
		if (this.connection != null) {
			try {
				stmt = this.connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * from games WHERE genre_id in (SELECT genre_id from genres where genre_name = '" + genre + "');");
				while (rs.next()) {
					int gameId = rs.getInt("game_id");
					int genreId = rs.getInt("genre_id");
					int publisherId = rs.getInt("publisher_id");
					int platformId = rs.getInt("platform_id");
					int releaseYear = rs.getInt("release_year");
					String gameName = rs.getString("game_name");
					String gameDescription = rs.getString("game_description");
					games.add(new VideoGame(gameId, genreId, publisherId, platformId, releaseYear, gameName, gameDescription));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				while (e != null) {
					System.out.println("SQL Exception Code " + e.getErrorCode());
					System.out.println("SQLState " + e.getSQLState());
					System.out.println("Error Message " + e.getMessage());
					e = e.getNextException();
				}
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
				} catch (SQLException e2) {
					// Can't do anything here
				}
			}
		}
		return games;
	}
	
	/**
	 * Retrieve all games for a specific publisher name in database
	 * @return ArrayList<VideoGame> - list of all video games
	 */
	public ArrayList<VideoGame> getAllGamesByPublisher(String publisher) {
		Statement stmt = null;
		ArrayList<VideoGame> games = new ArrayList<>();
		if (this.connection != null) {
			try {
				stmt = this.connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM games, publishers WHERE games.publisher_id = publishers.publisher_id AND publishers.publisher_name = '" + publisher + "';");
				while (rs.next()) {
					int gameId = rs.getInt("game_id");
					int genreId = rs.getInt("genre_id");
					int publisherId = rs.getInt("publisher_id");
					int platformId = rs.getInt("platform_id");
					int releaseYear = rs.getInt("release_year");
					String gameName = rs.getString("game_name");
					String gameDescription = rs.getString("game_description");
					games.add(new VideoGame(gameId, genreId, publisherId, platformId, releaseYear, gameName, gameDescription));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				while (e != null) {
					System.out.println("SQL Exception Code " + e.getErrorCode());
					System.out.println("SQLState " + e.getSQLState());
					System.out.println("Error Message " + e.getMessage());
					e = e.getNextException();
				}
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
				} catch (SQLException e2) {
					// Can't do anything here
				}
			}
		}
		return games;
	}
}
