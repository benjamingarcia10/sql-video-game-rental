import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inventory {
	private int inventoryId;
	private int gameId;
	private int platformId;
	private int availableCopies;
	
	public Inventory(int inventoryId, int gameId, int platformId, int availableCopies) {
		this.inventoryId = inventoryId;
		this.gameId = gameId;
		this.platformId = platformId;
		this.availableCopies = availableCopies;
	}
	
	public int getInventoryId() {
		return inventoryId;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public int getPlatformId() {
		return platformId;
	}
	
	public int getAvailableCopies() {
		return availableCopies;
	}
	
	public static class DatabaseOperations {
		/**
		 * Get an inventory entry by the inventoryId
		 * @param db - Rental Database to retrieve data from
		 * @param inventoryId - Desired inventoryId to get information for
		 * @return Inventory
		 * @throws SQLException 
		 */
		public static Inventory getInventoryById(RentalDatabase db, int inventoryId) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM inventory WHERE inventory_id = ?");
				ps.setInt(1, inventoryId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new Inventory(rs.getInt("inventory_id"), rs.getInt("game_id"), rs.getInt("platform_id"), rs.getInt("available_copies"));
				}
			}
			return null;
		}
		
		public static void getAvailableInventory(RentalDatabase db) throws SQLException {
			if (db.isConnected()) {
				Statement stmt = db.getConnection().createStatement();
				if (stmt != null) {
					ResultSet rs = stmt.executeQuery("SELECT games.*, platform_name, available_copies FROM games, platforms, inventory WHERE inventory.game_id = games.game_id AND inventory.platform_id = platforms.platform_id AND available_copies > 0;");
					
					int rowCount = 0;
					while(rs.next()) {
						rowCount++;
						if (rowCount == 1) {
							System.out.println("Games Available to Rent:");
						}
						System.out.printf("- %s (%d) (%s): %d copies available\n", rs.getString("game_name"), rs.getInt("release_year"), rs.getString("platform_name"), rs.getInt("available_copies"));
					}
					if (rowCount == 0) {
						System.out.println("No games available to rent.");
					}
				}
			}
		}
		
		public static void getAvailableInventoryByGameName(RentalDatabase db, String gameName) throws SQLException {
			if (db.isConnected()) {
				PreparedStatement ps = db.getConnection().prepareStatement("SELECT games.*, platform_name, available_copies FROM games, platforms, inventory WHERE games.game_name = ? AND inventory.game_id = games.game_id AND inventory.platform_id = platforms.platform_id AND available_copies > 0;");
				if (ps != null) {
					ResultSet rs = ps.executeQuery("SELECT games.*, platform_name, available_copies FROM games, platforms, inventory WHERE inventory.game_id = games.game_id AND inventory.platform_id = platforms.platform_id AND available_copies > 0;");
					
					int rowCount = 0;
					while(rs.next()) {
						rowCount++;
						if (rowCount == 1) {
							System.out.println("Games Available to Rent:");
						}
						System.out.printf("- %s (%d) (%s): %d copies available\n", rs.getString("game_name"), rs.getInt("release_year"), rs.getString("platform_name"), rs.getInt("available_copies"));
					}
					if (rowCount == 0) {
						System.out.println("No games available to rent.");
					}
				}
			}
		}
	}
}
