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
		 */
		public static User getUserById(RentalDatabase db, int userId) {
			if (db.isConnected()) {
				try {
					PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM users WHERE user_id = ?");
					ps.setInt(1, userId);
					if (ps != null) {
						try {
							ResultSet rs = ps.executeQuery();
							if (rs.first()) {
								return new User(rs.getInt("user_id"), rs.getString("name"), rs.getInt("allowed_rentals"));
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
