import java.sql.*;

/**
 * Rental Database class to instantiate a connection to the database defined in PrivateInformation.java
 * (used to perform SELECT and DML statements on the connection)
 * @author benjamingarcia10
 *
 */
public class RentalDatabase {
	private Connection connection = null;
	
	private String[] triggerStrings = new String[] {
		"DROP TRIGGER IF EXISTS RentGameTrigger;",
		"CREATE TRIGGER RentGameTrigger AFTER INSERT ON rentals FOR EACH ROW BEGIN IF (NEW.is_returned = 0) THEN UPDATE inventory SET available_copies = available_copies - 1 WHERE inventory_id = NEW.inventory_id; END IF; END",
		"DROP TRIGGER IF EXISTS ReturnGameTrigger;",
		"CREATE TRIGGER ReturnGameTrigger AFTER UPDATE ON rentals FOR EACH ROW BEGIN IF (OLD.is_returned = 0 AND NEW.is_returned = 1) THEN UPDATE inventory SET available_copies = available_copies + 1 WHERE inventory_id = NEW.inventory_id; ELSEIF (OLD.is_returned = 1 AND NEW.is_returned = 0) THEN UPDATE inventory SET available_copies = available_copies - 1 WHERE inventory_id = NEW.inventory_id; END IF; END",
	};
	
	private String[] procedureStrings = new String[] {
		"DROP PROCEDURE IF EXISTS getGamesByGenreName;",
		"CREATE PROCEDURE getGamesByGenreName(IN genreNameInput VARCHAR(100)) BEGIN SELECT * FROM games WHERE EXISTS (SELECT * FROM genres WHERE games.genre_id = genres.genre_id AND genre_name = genreNameInput); END",
		"DROP PROCEDURE IF EXISTS getGamesByPublisherName;",
		"CREATE PROCEDURE getGamesByPublisherName(IN publisherInput VARCHAR(100)) BEGIN SELECT games.* FROM games, publishers WHERE games.publisher_id = publishers.publisher_id AND publishers.publisher_name = publisherInput; END",
		"DROP PROCEDURE IF EXISTS archiveRentals;",
		"CREATE PROCEDURE archiveRentals(IN updateCutoff TIMESTAMP, OUT archive_count INT) BEGIN DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN ROLLBACK; END; DECLARE EXIT HANDLER FOR SQLWARNING BEGIN ROLLBACK; END; START TRANSACTION; SELECT COUNT(*) INTO archive_count FROM rentals WHERE updated_at < updateCutoff; INSERT INTO rentals_archive (SELECT * FROM rentals WHERE updated_at < updateCutoff); DELETE FROM rentals WHERE updated_at < updateCutoff; COMMIT; END",
	};
	
	/**
	 * Create DB connection
	 */
	public RentalDatabase() {
		try {
			this.connection = DriverManager.getConnection(PrivateInformation.DB_URL, PrivateInformation.DB_USER, PrivateInformation.DB_PASS);
			this.setupTriggers();
			this.setupProcedures();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check the console output.");
			e.printStackTrace();
		}

		if (this.connection == null) {
			System.out.println("Failed to make connection.");
		}
	}
	
	public void setupTriggers() {
		if (this.isConnected()) {
			for (String trigger: triggerStrings) {
				try {
					Statement stmt = this.connection.createStatement();
					stmt.execute(trigger);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setupProcedures() {
		if (this.isConnected()) {
			for (String procedure: procedureStrings) {
				try {
					Statement stmt = this.connection.createStatement();
					stmt.execute(procedure);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Check if DB is connected
	 * @return
	 */
	public boolean isConnected() {
		return this.connection != null;
	}
	
	/**
	 * Get DB connection
	 * @return
	 */
	public Connection getConnection() {
		return this.connection;
	}
	
	/**
	 * Attempt to close DB connection
	 */
	public void close() {
		try {
			if (this.isConnected()) {
				this.connection.close();
			}
		} catch (SQLException e) {
			// Can't do anything here
		}
	}
}
