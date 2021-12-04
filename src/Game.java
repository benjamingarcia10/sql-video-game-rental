import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Game {
	private int gameId;
	private int genreId;
	private int publisherId;
	private int releaseYear;
	private String gameName;
	private String gameDescription;
	
	private int count;			// Used for aggregate function 4 to specify release_year and count
	
	public Game(int releaseYear, int count) {
		this.releaseYear = releaseYear;
		this.count = count;
	}
	
	public Game(int gameId, int genreId, int publisherId, int releaseYear, String gameName, String gameDescription) {
		this.gameId = gameId;
		this.genreId = genreId;
		this.publisherId = publisherId;
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
	
	public int getReleaseYear() {
		return releaseYear;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public String getGameDescription() {
		return gameDescription;
	}
	
	public int getCount() {
		return count;
	}
	
	public static class DatabaseOperations {
		/**
		 * Get a game by the gameId
		 * @param db - Rental Database to retrieve data from
		 * @param gameId - Desired gameId to get information for
		 * @return Game
		 * @throws SQLException 
		 */
		public static Game getGameById(RentalDatabase db, int gameId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM games WHERE game_id = ?");
				ps.setInt(1, gameId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new Game(rs.getInt("game_id"), rs.getInt("genre_id"), rs.getInt("publisher_id"), rs.getInt("release_year"), rs.getString("game_name"), rs.getString("game_description"));
				}
			}
			return null;
		}
		
		public static boolean updateGameById(RentalDatabase db, int gameId, int newGameId, int newGenreId, int newPublisherId, int newReleaseYear, String newGameName, String newGameDescription) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("UPDATE games SET game_id = ?, genre_id = ?, publisher_id = ?, release_year = ?, game_name = ?, game_description = ? WHERE game_id = ?;");
				ps.setInt(1, newGameId);
				ps.setInt(2, newGenreId);
				ps.setInt(3, newPublisherId);
				ps.setInt(4, newReleaseYear);
				ps.setString(5, newGameName);
				ps.setString(6, newGameDescription);
				ps.setInt(7, gameId);
				int rowsUpdated = ps.executeUpdate();
				
				if (rowsUpdated > 0) {
					return true;
				}
			}
			return false;
		}
		
		public static boolean addGame(RentalDatabase db, int newGenreId, int newPublisherId, int newReleaseYear, String newGameName, String newGameDescription) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO games (genre_id, publisher_id, release_year, game_name, game_description) VALUES (?, ?, ?, ?, ?);");
				ps.setInt(1, newGenreId);
				ps.setInt(2, newPublisherId);
				ps.setInt(3, newReleaseYear);
				ps.setString(4, newGameName);
				ps.setString(5, newGameDescription);
				int rowsUpdated = ps.executeUpdate();
				
				if (rowsUpdated > 0) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Helper to print games ArrayList
		 * @param games - Games to print out
		 * @param headerString - Header string to print before games list if there are games in list
		 * @param emptyString - String to print if games list is empty
		 */
		private static void printGamesArrayList(ArrayList<Game> games, String headerString, String emptyString) {
			if (games.size() > 0) {
				System.out.println(headerString);
				for (Game game: games) {
					System.out.printf("- Game ID %d: %s (%d) - %s\n", game.getGameId(), game.getGameName(), game.getReleaseYear(), game.getGameDescription());
				}
			} else {
				System.out.println(emptyString);
			}
		}
		
		/**
		 * Internal function to get a list of games based on prepared statement
		 * @param stmt - Prepared statement which returns tuples of games
		 * @return ArrayList<Game> games returned from statement query
		 * @throws SQLException 
		 */
		private static ArrayList<Game> getGamesByPreparedStatement(PreparedStatement stmt) throws SQLException {
			ArrayList<Game> games = new ArrayList<>();
			if (stmt != null) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int gameId = rs.getInt("game_id");
					int genreId = rs.getInt("genre_id");
					int publisherId = rs.getInt("publisher_id");
					int releaseYear = rs.getInt("release_year");
					String gameName = rs.getString("game_name");
					String gameDescription = rs.getString("game_description");
					games.add(new Game(gameId, genreId, publisherId, releaseYear, gameName, gameDescription));
				}
			}
			return games;
		}
		
		/**
		 * Internal function to get a list of games based on callable statement
		 * @param cs - Callable statement which returns tuples of games
		 * @return ArrayList<Game> games returned from statement query
		 * @throws SQLException 
		 */
		private static ArrayList<Game> getGamesByCallableStatement(CallableStatement cs) throws SQLException {
			ArrayList<Game> games = new ArrayList<>();
			if (cs != null) {
				ResultSet rs = cs.executeQuery();
				while (rs.next()) {
					int gameId = rs.getInt("game_id");
					int genreId = rs.getInt("genre_id");
					int publisherId = rs.getInt("publisher_id");
					int releaseYear = rs.getInt("release_year");
					String gameName = rs.getString("game_name");
					String gameDescription = rs.getString("game_description");
					games.add(new Game(gameId, genreId, publisherId, releaseYear, gameName, gameDescription));
				}
			}
			return games;
		}
		
		/**
		 * Retrieve all games in database
		 * @param db - Rental Database to retrieve data from
		 * @throws SQLException 
		 */
		public static void getAllGames(RentalDatabase db) throws SQLException {
			ArrayList<Game> games = new ArrayList<>();
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM games");
				games = getGamesByPreparedStatement(ps);
			}
			printGamesArrayList(games, "All Games:", "No games found.");
		}
		
		/**
		 * Retrieve all games for a specific genre name in database
		 * @param db - Rental Database to retrieve data from
		 * @throws SQLException 
		 */
		public static void getAllGamesByGenre(RentalDatabase db, String genre) throws SQLException {
			ArrayList<Game> genreGames = new ArrayList<>();
			if (db.isConnected()) {
				CallableStatement cs = db.getConnection().prepareCall("{CALL getGamesByGenreName(?)}");
				cs.setString(1, genre);
				genreGames = getGamesByCallableStatement(cs);
//				PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM games WHERE genre_id IN (SELECT genre_id FROM genres WHERE genre_name = ?);");
//				ps.setString(1, genre);
//				games = getGamesByPreparedStatement(ps);
			}
			printGamesArrayList(genreGames, "All Games for Genre: " + genre, "No games found for genre: " + genre);
		}
		
		/**
		 * Retrieve all games for a specific publisher name in database
		 * @param db - Rental Database to retrieve data from
		 * @throws SQLException 
		 */
		public static void getAllGamesByPublisher(RentalDatabase db, String publisher) throws SQLException {
			ArrayList<Game> publisherGames = new ArrayList<>();
			if (db.isConnected()) {
				CallableStatement cs = db.getConnection().prepareCall("{CALL getGamesByPublisherName(?)}");
				cs.setString(1, publisher);
				publisherGames = getGamesByCallableStatement(cs);
//				PreparedStatement ps = db.getConnection().prepareStatement("SELECT games.* FROM games, publishers WHERE games.publisher_id = publishers.publisher_id AND publishers.publisher_name = ?;");
//				ps.setString(1, publisher);
//				games = getGamesByPreparedStatement(ps);
			}
			printGamesArrayList(publisherGames, "All Games for Publisher: " + publisher, "No games found for publisher: " + publisher);
		}
		
		/**
		 * Retrieve all games released on or after a given year
		 * @param db - Rental Database to retrieve data from
		 * @throws SQLException 
		 */
		public static void getAllGamesOnOrAfterYear(RentalDatabase db, int year) throws SQLException {
			ArrayList<Game> gamesOnOrAfterYear = new ArrayList<>();
			if (db.isConnected()) {
				PreparedStatement preparedStatement = null;
				preparedStatement = db.getConnection().prepareStatement("SELECT release_year, COUNT(*) count FROM games GROUP BY release_year HAVING release_year >= ? ORDER BY release_year ASC;");
				preparedStatement.setInt(1, year);
				ResultSet rs = preparedStatement.executeQuery();
				
				int rowCount = 0;
				while(rs.next()) {
					rowCount++;
					if (rowCount == 1) {
						System.out.println("Number of Games Released on or After: " + year);
					}
					System.out.printf("- %d: %d games\n", rs.getInt("release_year"), rs.getInt("count"));
				}
				if (rowCount == 0) {
					System.out.println("No games found released on or after: " + year);
				}

				while (rs.next()) {
					int releaseYear = rs.getInt("release_year");
					int count = rs.getInt("count");
					gamesOnOrAfterYear.add(new Game(releaseYear, count));
				}
			}
		}
	}
}
