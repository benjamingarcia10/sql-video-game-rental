import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Genre {
	private int genreId;
	private String genreName;
	
	public Genre(int genreId, String genreName) {
		this.genreId = genreId;
		this.genreName = genreName;
	}
	
	public int getGenreId() {
		return genreId;
	}
	
	public String getGenreName() {
		return genreName;
	}
	
	public static class DatabaseOperations {
		/**
		 * Get a genre by the genreId
		 * @param db - Rental Database to retrieve data from
		 * @param genreId - Desired genreId to get information for
		 * @return Genre
		 * @throws SQLException 
		 */
		public static Genre getGenreById(RentalDatabase db, int genreId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM genres WHERE genre_id = ?");
				ps.setInt(1, genreId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new Genre(rs.getInt("genre_id"), rs.getString("genre_name"));
				}
			}
			return null;
		}
	}
}
