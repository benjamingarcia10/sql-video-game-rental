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
		 * @throws SQLException 
		 */
		public static Platform getPlatformById(RentalDatabase db, int platformId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM platforms WHERE platform_id = ?");
				ps.setInt(1, platformId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new Platform(rs.getInt("platform_id"), rs.getString("platform_name"));
				}
			}
			return null;
		}
	}
}
