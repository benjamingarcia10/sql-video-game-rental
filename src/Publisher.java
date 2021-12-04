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
		 */
		public static Publisher getPublisherById(RentalDatabase db, int publisherId) {
			if (db.isConnected()) {
				try {
					PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM publishers WHERE publisher_id = ?");
					ps.setInt(1, publisherId);
					if (ps != null) {
						try {
							ResultSet rs = ps.executeQuery();
							if (rs.first()) {
								return new Publisher(rs.getInt("publisher_id"), rs.getString("publisher_name"));
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
								if (ps != null) {
									ps.close();
								}
							} catch (SQLException e2) {
								// Can't do anything here
							}
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					while (e != null) {
						System.out.println("SQL Exception Code " + e.getErrorCode());
						System.out.println("SQLState " + e.getSQLState());
						System.out.println("Error Message " + e.getMessage());
						e = e.getNextException();
					}
				}
			}
			return null;
		}
	}
}
