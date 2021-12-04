import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Rental {
	private int rentalId;
	private int userId;
	private int inventoryId;
	private Date rentedAt;
	private int rentalLength;
	private boolean isReturned;
	private Timestamp updatedAt;
	
	public Rental(int rentalId, int userId, int inventoryId, Date rentedAt, int rentalLength, boolean isReturned, Timestamp updatedAt) {
		this.rentalId = rentalId;
		this.userId = userId;
		this.inventoryId = inventoryId;
		this.rentedAt = rentedAt;
		this.rentalLength = rentalLength;
		this.isReturned = isReturned;
		this.updatedAt = updatedAt;
	}
	
	public int getRentalId() {
		return rentalId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getInventoryId() {
		return inventoryId;
	}
	
	public Date getRentedAt() {
		return rentedAt;
	}
	
	public int getRentalLength() {
		return rentalLength;
	}
	
	public boolean isReturned() {
		return isReturned;
	}
	
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	
	public static class DatabaseOperations {
		/**
		 * Get a rental entry by the rentalId
		 * @param db - Rental Database to retrieve data from
		 * @param rentalId - Desired rentalId to get information for
		 * @return Rental
		 * @throws SQLException 
		 */
		public static Rental getRentalById(RentalDatabase db, int rentalId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM rentals WHERE rental_id = ?");
				ps.setInt(1, rentalId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new Rental(rs.getInt("rental_id"), rs.getInt("user_id"), rs.getInt("inventory_id"), rs.getDate("rented_at"), rs.getInt("rental_length"), rs.getBoolean("is_returned"), rs.getTimestamp("updated_at"));
				}
			}
			return null;
		}
		
		public static void getAllActiveRentalsForUser(RentalDatabase db, int userId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT rentals.*, game_name, release_year, platform_name FROM rentals, inventory, games, platforms WHERE rentals.user_id = ? AND rentals.inventory_id = inventory.inventory_id AND games.game_id = inventory.game_id AND platforms.platform_id = inventory.platform_id AND is_returned = 0;");
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				
				int rowCount = 0;
				while (rs.next()) {
					rowCount++;
					if (rowCount == 1) {
						System.out.println("Active Rentals For User ID: " + userId);
					}
					System.out.printf("- Rental ID: %d | Rented: %s, Due: %s | %s (%d) (%s)\n", rs.getInt("rental_id"), rs.getDate("rented_at").toString(), rs.getDate("rented_at").toLocalDate().plusDays(rs.getInt("rental_length")).toString(), rs.getString("game_name"), rs.getInt("release_year"), rs.getString("platform_name"));
				}
				if (rowCount == 0) {
					System.out.println("No active rentals found for user ID: " + userId);
				}
			}
		}
		
		public static void getActiveAndAllowedRentalsForUser(RentalDatabase db, int userId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT users.*, COUNT(rental_id) active_rentals FROM rentals, users WHERE users.user_id = ? AND rentals.user_id = users.user_id  AND rentals.is_returned = 0;");
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					if (rs.getString("name") == null) {
						System.out.println("No active rentals found for user id: " + userId);
					} else {
						System.out.printf("User %s has %d active rental(s) out of %d allowed rental(s).\n", rs.getString("name"), rs.getInt("active_rentals"), rs.getInt("allowed_rentals"));
					}
				} else {
					System.out.println("No active rentals found for user id: " + userId);
				}
			}
		}
		
		public static int getActiveRentalsCountForUser(RentalDatabase db, int userId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT COUNT(*) count FROM rentals WHERE rentals.user_id = ? AND is_returned = 0;");
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					return rs.getInt("count");
				}
			}
			return 0;
		}
		
		public static boolean addRentalForUserId(RentalDatabase db, int userId, int inventoryId, int rentalLength) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO rentals (user_id, inventory_id, rented_at, rental_length) VALUES (?, ?, CURRENT_DATE, ?);");
				ps.setInt(1, userId);
				ps.setInt(2, inventoryId);
				ps.setInt(3, rentalLength);
				int rowsUpdated = ps.executeUpdate();
				
				if (rowsUpdated > 0) {
					return true;
				}
			}
			return false;
		}
		
		public static boolean returnRental(RentalDatabase db, int userId, int rentalId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("UPDATE rentals SET is_returned = 1 WHERE user_id = ? AND rental_id = ? AND is_returned = 0;");
				ps.setInt(1, userId);
				ps.setInt(2, rentalId);
				int rowsUpdated = ps.executeUpdate();
				
				if (rowsUpdated > 0) {
					return true;
				}
			}
			return false;
		}
		
		public static boolean deleteRentalsAndUserByUserId(RentalDatabase db, int userId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("DELETE FROM rentals WHERE user_id = ?;");
				ps.setInt(1, userId);
				int rowsUpdated = ps.executeUpdate();
				
				ps = db.getConnection().prepareStatement("DELETE FROM rentals_archive WHERE user_id = ?;");
				ps.setInt(1, userId);
				rowsUpdated = ps.executeUpdate();
				
				ps = db.getConnection().prepareStatement("DELETE FROM users WHERE user_id = ?;");
				ps.setInt(1, userId);
				rowsUpdated = ps.executeUpdate();
				
				if (rowsUpdated > 0) {
					return true;
				}
			}
			return false;
		}
		
		public static void getAllUsersActiveUnarchivedRentalCount(RentalDatabase db) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT users.user_id, name, COUNT(IF(is_returned = 0, 1, NULL)) active_rentals FROM users LEFT JOIN rentals ON rentals.user_id = users.user_id GROUP BY users.name;");
				ResultSet rs = ps.executeQuery();
				
				int rowCount = 0;
				while (rs.next()) {
					rowCount++;
					if (rowCount == 1) {
						System.out.println("Active, Unarchived Rentals:");
					}
					System.out.printf("- User ID: %d | Name: %s | Active Rentals: %d\n", rs.getInt("user_id"), rs.getString("name"), rs.getInt("active_rentals"));
				}
				if (rowCount == 0) {
					System.out.println("No active, unarchived rentals found.");
				}
			}
		}
		
		public static void getAllUsersRentalCountIncludingArchived(RentalDatabase db) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT users.user_id, users.name, COUNT(rental_id) rental_count FROM ((SELECT * FROM rentals) UNION (SELECT * FROM rentals_archive)) combined, users WHERE combined.user_id = users.user_id GROUP BY user_id;");
				ResultSet rs = ps.executeQuery();
				
				int rowCount = 0;
				while (rs.next()) {
					rowCount++;
					if (rowCount == 1) {
						System.out.println("Users' Rental Count (active and inactive, including archived rentals):");
					}
					System.out.printf("- User ID: %d | Name: %s | Rental Count: %d\n", rs.getInt("user_id"), rs.getString("name"), rs.getInt("rental_count"));
				}
				if (rowCount == 0) {
					System.out.println("No rentals found.");
				}
			}
		}
	}
}
