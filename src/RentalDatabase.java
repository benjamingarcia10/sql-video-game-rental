import java.sql.*;

/**
 * Rental Database class to instantiate a connection to the database defined in PrivateInformation.java
 * (used to perform SELECT and DML statements on the connection)
 * @author benjamingarcia10
 *
 */
public class RentalDatabase {
	private Connection connection;
	
	public RentalDatabase() {
		try {
			this.connection = DriverManager.getConnection(PrivateInformation.DB_URL, PrivateInformation.DB_USER, PrivateInformation.DB_PASS);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check the console output.");
			e.printStackTrace();
		}

		if (connection == null) {
			System.out.println("Failed to make connection.");
		}
	}
	
	/**
	 * Wrapper to execute a query on the underlying database connection
	 * @param sql - SELECT statement to retrieve data from the database connection
	 * @return ResultSet - results from SELECT statement on database
	 */
	public ResultSet executeQuery(String sql) {
		if (connection != null) {
			try {
				Statement stmt = this.connection.createStatement();
				ResultSet results = stmt.executeQuery(sql);
				return results;
			} catch (SQLException e) {
				e.printStackTrace();
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
		if (connection != null) {
			try {
				Statement stmt = this.connection.createStatement();
				int rowsUpdated = stmt.executeUpdate(sql);
				return rowsUpdated;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
