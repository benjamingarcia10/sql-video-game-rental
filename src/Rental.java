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
		 */
		public static Rental getRentalById(RentalDatabase db, int rentalId) {
			if (db.isConnected()) {
				try {
					PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM rentals WHERE rental_id = ?");
					ps.setInt(1, rentalId);
					if (ps != null) {
						try {
							ResultSet rs = ps.executeQuery();
							if (rs.first()) {
								return new Rental(rs.getInt("rental_id"), rs.getInt("user_id"), rs.getInt("inventory_id"), rs.getDate("rented_at"), rs.getInt("rental_length"), rs.getBoolean("is_returned"), rs.getTimestamp("updated_at"));
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
