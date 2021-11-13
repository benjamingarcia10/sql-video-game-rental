import java.sql.*;

/**
 * Rental Database class to instantiate a connection to the database defined in PrivateInformation.java
 * (used to perform SELECT and DML statements on the connection)
 * @author benjamingarcia10
 *
 */
public class RentalDatabase {
	private Connection connection = null;
	
	public RentalDatabase() {
		try {
			this.connection = DriverManager.getConnection(PrivateInformation.DB_URL, PrivateInformation.DB_USER, PrivateInformation.DB_PASS);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check the console output.");
			e.printStackTrace();
		}

		if (this.connection == null) {
			System.out.println("Failed to make connection.");
		}
	}
	
	public boolean isConnected() {
		return this.connection != null;
	}
	
	public void close() {
		try {
			if (this.connection != null) {
				this.connection.close();
			}
		} catch (SQLException e) {
			// Can't do anything here
		}
	}
	
	/**
	 * Wrapper to execute a query on the underlying database connection
	 * @param sql - SELECT statement to retrieve data from the database connection
	 * @return ResultSet - results from SELECT statement on database
	 */
	public ResultSet executeQuery(String sql) {
		Statement stmt = null;
		if (this.connection != null) {
			try {
				stmt = this.connection.createStatement();
				ResultSet results = stmt.executeQuery(sql);
				return results;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
				} catch (SQLException e2) {
					// Can't do anything here
				}
			}
		}
		return null;
	}
	
	/**
	 * Wrapper to execute update on the underlying database connection
	 * @param sql - DML statement to perform update on the database connection
	 * @return integer - number of rows updated by DML statement
	 */
	public int executeUpdate(String sql) {
		Statement stmt = null;
		if (connection != null) {
			try {
				stmt = this.connection.createStatement();
				int rowsUpdated = stmt.executeUpdate(sql);
				return rowsUpdated;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
				} catch (SQLException e2) {
					// Can't do anything here
				}
			}
		}
		return 0;
	}
}
