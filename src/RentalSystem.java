import java.sql.SQLException;
import java.util.Scanner;

public class RentalSystem {
	private static String[] validFunctionCommands = new String[] {
		"Rental System Browsing Functions:",
		"1. View all games",												// Done
		"2. View games by genre",											// Done
		"3. View games by publisher",										// Done
		"4. View number of games released on or after a certain year",		// Done
		"5. View games available to rent",									// Done
		"6. Check for a specific game's availability",						// Done
		"----------------------------------------",
		"User Functions",
		"7. Rent a game",													// Done
		"8. Return a game",													// Done
		"9. View all of user's active rentals",								// Done
		"10. Get count of user's active and allowed rentals",				// Done
		"----------------------------------------",
		"Rental System Admin Functions:",
		"11. Add a publisher",
		"12. Add a new game",
		"13. Update a game entry",											// Done
		"14. Add a user",
		"15. Update a user entry",
		"16. Add inventory",
		"17. Delete a user and all their rentals",
		"18. View all users and their active (including archived) rental count",
		"----------------------------------------",
		"19. Exit"
	};
	private static int EXIT_FUNCTION_MODE = 19;
	
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
			
			Game game;
			User user;
			Inventory inventory;
			String gameName;
			int year, userId, activeRentals, inventoryId, rentalLength, rentalId, gameId, genreId;
			
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
						year = getUserIntegerInput(in, "Enter the release year to check: ",
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
					case 6:
						gameName = getUserStringInput(in, "Enter the game name to search availability for: ");
						System.out.println();
						Inventory.DatabaseOperations.getAvailableInventoryByGameName(db, gameName);
						System.out.println();
						break;
					case 7:
						userId = getUserIntegerInput(in, "Enter your user id: ",
								"You have entered an invalid number. Enter your user id: ");
//						user = User.DatabaseOperations.getUserById(db, userId);
//						if (user == null) {
//							System.out.println("No user exists for user id: " + userId);
//							break;
//						}
//						activeRentals = Rental.DatabaseOperations.getActiveRentalsCountForUser(db, userId);
//						if (activeRentals >= user.getAllowedRentals()) {
//							System.out.printf("Hello %s, you are unable to rent at this time as you have %d active rental(s) out of %d allowed rental(s)\n", user.getName(), activeRentals, user.getAllowedRentals());
//							break;
//						}
						inventoryId = getUserIntegerInput(in, "Enter the inventory id you want to rent: ",
								"You have entered an invalid number. Enter the inventory id you want to rent: ");
						rentalLength = getUserIntegerInput(in, "Enter how long you want to rent for: ",
								"You have entered an invalid number. Enter how long you want to rent for: ");
//						inventory = Inventory.DatabaseOperations.getInventoryById(db, inventoryId);
//						if (inventory == null) {
//							System.out.println("No inventory entry exists for inventory id: " + inventoryId);
//							break;
//						} else if (inventory.getAvailableCopies() <= 0) {
//							System.out.println("There are no available copies for your selected item.");
//						} else {
//							Rental.DatabaseOperations.addRentalForUserId(db, userId, inventoryId);
//						}
						System.out.println();
						if (Rental.DatabaseOperations.addRentalForUserId(db, userId, inventoryId, rentalLength)) {
							System.out.println("Rental successful.");
						} else {
							System.out.println("Rental failed.");
						}
						System.out.println();
						break;
					case 8:
						userId = getUserIntegerInput(in, "Enter your user id: ",
								"You have entered an invalid number. Enter your user id: ");
						rentalId = getUserIntegerInput(in, "Enter the rental id that you are returning: ",
								"You have entered an invalid number. Enter the rental id that you are returning: ");
						System.out.println();
						if (Rental.DatabaseOperations.returnRental(db, userId, rentalId)) {
							System.out.println("Rental successfully returned.");
						} else {
							System.out.println("Rental return failed. Check user id and rental id and check that the rental is still active.");
						}
						System.out.println();
						break;
					case 9:
						userId = getUserIntegerInput(in, "Enter your user id: ",
								"You have entered an invalid number. Enter your user id: ");
						System.out.println();
						Rental.DatabaseOperations.getAllActiveRentalsForUser(db, userId);
						System.out.println();
						break;
					case 10:
						userId = getUserIntegerInput(in, "Enter your user id: ",
								"You have entered an invalid number. Enter your user id: ");
						System.out.println();
						Rental.DatabaseOperations.getActiveAndAllowedRentalsForUser(db, userId);
						System.out.println();
						break;
					case 13:
						gameId = getUserIntegerInput(in, "Enter the game id you want to update: ",
								"You have entered an invalid number. Enter the game id you want to update: ");
						game = Game.DatabaseOperations.getGameById(db, gameId);
						if (game == null) {
							System.out.println("No game found for game id: " + gameId);
							break;
						}
						int newGameId = getUserIntegerInput(in, "Enter desired new game id (original: " + gameId + "): ",
								"You have entered an invalid number. Enter desired new game id (original: " + gameId + "): ");
						int newGenreId = getUserIntegerInput(in, "Enter desired new genre id (original: " + game.getGenreId() +"): ",
								"You have entered an invalid number. Enter desired new genre id (original: " + game.getGenreId() +"): ");
						int newPublisherId = getUserIntegerInput(in, "Enter desired new publisher id (original: " + game.getPublisherId() + "): ",
								"You have entered an invalid number. Enter desired new publisher id (original: " + game.getPublisherId() + "): ");
						int newReleaseYear = getUserIntegerInput(in, "Enter desired new release year (original: " + game.getReleaseYear() + "): ",
								"You have entered an invalid number. Enter desired new release year (original: " + game.getReleaseYear() + "): ");
						String newGameName = getUserStringInput(in, "Enter desired new game name or leave blank to keep the same (original: " + game.getGameName() + "): ");
						if (newGameName.equals("")) newGameName = game.getGameName();
						String newGameDescription = getUserStringInput(in, "Enter desired new game description or leave blank to keep the same (original: " + game.getGameDescription() + "): ");
						if (newGameDescription.equals("")) newGameDescription = game.getGameDescription();
						if (Game.DatabaseOperations.updateGameById(db, gameId, newGameId, newGenreId, newPublisherId, newReleaseYear, newGameName, newGameDescription)) {
							System.out.println("Game successfully updated.");
						} else {
							System.out.println("Game update unsuccessful.");
						}
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
