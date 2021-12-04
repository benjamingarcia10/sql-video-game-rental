import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private int userId;
	private String name;
	private int allowedRentals;
	
	public User(int userId, String name, int allowedRentals) {
		this.userId = userId;
		this.name = name;
		this.allowedRentals = allowedRentals;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAllowedRentals() {
		return allowedRentals;
	}
	
	public static class DatabaseOperations {
		/**
		 * Get a user by the userId
		 * @param db - Rental Database to retrieve data from
		 * @param userId - Desired userId to get information for
		 * @return User
		 * @throws SQLException 
		 */
		public static User getUserById(RentalDatabase db, int userId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM users WHERE user_id = ?");
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new User(rs.getInt("user_id"), rs.getString("name"), rs.getInt("allowed_rentals"));
				}
			}
			return null;
		}
	}
}
