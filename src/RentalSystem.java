import java.sql.SQLException;
import java.util.Scanner;

public class RentalSystem {
	private static String[] validFunctionCommands = new String[] {
		"Rental System Browsing Functions:",
		"1. View all games",
		"2. View games by genre",
		"3. View games by publisher",
		"4. View number of games released on or after a certain year",
		"5. View games available to rent",
		"6. Check for a specific game's availability",
		"----------------------------------------",
		"User Functions",
		"7. Rent a game",
		"8. Return a game",
		"9. Check rental length for user's rentals",
		"----------------------------------------",
		"Rental System Admin Functions:",
		"10. Add a new game",
		"11. Remove all copies of a game from the store inventory",
		"12. Update a game entry",
		"13. Update a user entry",
		"14. Add a publisher",
		"15. View all users' active rental count",
		"----------------------------------------",
		"16. Exit"
	};
	private static int EXIT_FUNCTION_MODE = 16;
	
	private static void printFunctions() {
		System.out.println("----------------------------------------");
		System.out.println("\tRental System Functions:");
		System.out.println("----------------------------------------");
		for (String s: validFunctionCommands) {
			System.out.println(s);
		}
		System.out.println("----------------------------------------");
	}
	
	/**
	 * Helper function to retrieve an integer input from user
	 * @param in - Scanner to read from
	 * @param initialPrompt - Initial prompt for integer input
	 * @param errorPrompt - Prompt to accept another integer if user did not input an integer
	 * @return int - User's integer
	 */
	private static int getUserIntegerInput(Scanner in, String initialPrompt, String errorPrompt) {
		Integer userInt = null;
		
		System.out.print(initialPrompt);
		while (userInt == null) {
			try {
				String s = in.nextLine().trim();
				userInt = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.print(errorPrompt);
			}
		}
		return userInt;
	}
	
	/**
	 * Helper function to retrieve user input
	 * @param in - Scanner to read from
	 * @param prompt - Prompt for what user should input
	 * @return String - User's input with whitespace trimmed
	 */
	private static String getUserStringInput(Scanner in, String prompt) {
		System.out.print(prompt);
		String input = in.nextLine().trim();
		return input;
	}
	
	public static void main(String[] args) {
		RentalDatabase db = new RentalDatabase();
		
		if (!db.isConnected()) return;
		
		Scanner in = new Scanner(System.in);
		printFunctions();
		
		// Get user function mode (integer input)
		int functionMode = getUserIntegerInput(in,
				"Enter a valid function mode: ",
				"You have entered an invalid number. Enter a valid function mode: ");
		
		while(true) {
			// To decide whether to print invalid function mode or prompt to do another action
			boolean isValidFunctionMode = true;
			
			// Exit program if user enters the function mode for EXIT
			if (functionMode == EXIT_FUNCTION_MODE) {
				break;
			}
			
			try {
				// Perform actions based on user's selected function mode
				switch (functionMode) {
					case 1:
						System.out.println();
						Game.DatabaseOperations.getAllGames(db);
						System.out.println();
						break;
					case 2:
						String genre = getUserStringInput(in, "Enter the genre name: ");
						System.out.println();
						Game.DatabaseOperations.getAllGamesByGenre(db, genre);
						System.out.println();
						break;
					case 3:
						String publisher = getUserStringInput(in, "Enter the publisher name: ");
						System.out.println();
						Game.DatabaseOperations.getAllGamesByPublisher(db, publisher);
						System.out.println();
						break;
					case 4:
						int year = getUserIntegerInput(in, "Enter the release year to check: ",
								"You have entered an invalid number. Enter the release year to check: ");
						System.out.println();
						Game.DatabaseOperations.getAllGamesOnOrAfterYear(db, year);
						System.out.println();
						break;
					case 5:
						System.out.println();
						Inventory.DatabaseOperations.getAvailableInventory(db);
						System.out.println();
						break;
					default:
						isValidFunctionMode = false;
						functionMode = getUserIntegerInput(in,
								"You have entered an invalid function mode. Enter a valid function mode: ",
								"You have entered an invalid number. Enter a valid function mode: ");
						break;
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
			
			// Prompt to perform another action if user just completed a valid function mode
			if (isValidFunctionMode) {
				System.out.print("Would you like to perform another action? (y/n): ");
				String continueString = in.nextLine().trim().toLowerCase();
				if (continueString.equals("n")) break;
				System.out.print("\n\n\n");
				printFunctions();
				functionMode = getUserIntegerInput(in,
						"Enter a valid function mode: ",
						"You have entered an invalid number. Enter a valid function mode: ");
			}
		}
        in.close();
        db.close();
	}
}
