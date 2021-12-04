import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Publisher {
	private int publisherId;
	private String publisherName;
	
	public Publisher(int publisherId, String publisherName) {
		this.publisherId = publisherId;
		this.publisherName = publisherName;
	}
	
	public int getPublisherId() {
		return publisherId;
	}
	
	public String getPublisherName() {
		return publisherName;
	}
	
	public static class DatabaseOperations {
		/**
		 * Get a publisher by the publisherId
		 * @param db - Rental Database to retrieve data from
		 * @param publisherId - Desired publisherId to get information for
		 * @return Publisher
		 * @throws SQLException 
		 */
		public static Publisher getPublisherById(RentalDatabase db, int publisherId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM publishers WHERE publisher_id = ?");
				ps.setInt(1, publisherId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new Publisher(rs.getInt("publisher_id"), rs.getString("publisher_name"));
				}
			}
			return null;
		}
	}
}
