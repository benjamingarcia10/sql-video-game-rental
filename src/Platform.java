import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Platform {
	private int platformId;
	private String platformName;
	
	public Platform(int platformId, String platformName) {
		this.platformId = platformId;
		this.platformName = platformName;
	}
	
	public int getPlatformId() {
		return platformId;
	}
	
	public String getPlatformName() {
		return platformName;
	}
	
	public static class DatabaseOperations {
		/**
		 * Get a platform by the platformId
		 * @param db - Rental Database to retrieve data from
		 * @param platformId - Desired platformId to get information for
		 * @return Platform
		 */
		public static Platform getPlatformById(RentalDatabase db, int platformId) {
			if (db.isConnected()) {
				try {
					PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM platforms WHERE platform_id = ?");
					ps.setInt(1, platformId);
					if (ps != null) {
						try {
							ResultSet rs = ps.executeQuery();
							if (rs.first()) {
								return new Platform(rs.getInt("platform_id"), rs.getString("platform_name"));
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
