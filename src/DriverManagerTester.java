import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DriverManagerTester {

	public static void main(String[] argv) {
		System.out.println("---------- MySQL JDBC Connection Testing ----------");
		/*
		 * Since JDBC 4.0, loading is automatically done . try {
		 * Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException e) {
		 * System.out.println("Where is your MySQL JDBC Driver?"); e.printStackTrace();
		 * return; }
		 */
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(PrivateInformation.DB_URL, PrivateInformation.DB_USER, PrivateInformation.DB_PASS);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check the console output.");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("Successfully connected to database!");
		} else {
			System.out.println("Failed to make connection.");
		}
	}
}
