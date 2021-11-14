import java.util.ArrayList;
import java.util.Scanner;

public class RentalSystem {
	private static String[] validFunctionCommands = new String[] {
		"1. View all games",
		"2. View games by genre",
		"3. View games by publisher",
		"9. Exit"
	};
	private static int EXIT_FUNCTION_MODE = 9;
	
	private static void printFunctions() {
		System.out.println("----------------------------------------");
		System.out.println("\tRental System Functions:");
		System.out.println("----------------------------------------");
		for (String s: validFunctionCommands) {
			System.out.println(s);
		}
		System.out.println("----------------------------------------");
	}
	
	public static void main(String[] args) {
		RentalDatabase db = new RentalDatabase();
		Scanner in = new Scanner(System.in);
		while(true) {
			try {
				printFunctions();
				System.out.print("Enter a valid function mode: ");
				String s = in.nextLine().trim();
				int functionMode = Integer.parseInt(s);
				boolean isValidFunctionMode = true;
				if (functionMode == EXIT_FUNCTION_MODE) {
					break;
				}
				switch (functionMode) {
					case 1:
						ArrayList<VideoGame> games = db.getAllGames();
						System.out.println("\nAll games:");
						for (VideoGame game: games) {
							System.out.printf("%d: %s (%d) - %s\n", game.getGameId(), game.getGameName(), game.getReleaseYear(), game.getGameDescription());
						}
						break;
					case 2:
						System.out.print("Enter the genre name: ");
						String genre = in.nextLine().trim();
						ArrayList<VideoGame> genreGames = db.getAllGamesByGenre(genre);
						if (genreGames.size() > 0) {
							System.out.printf("\nAll games for genre: %s\n", genre);
							for (VideoGame game: genreGames) {
								System.out.printf("%d: %s (%d) - %s\n", game.getGameId(), game.getGameName(), game.getReleaseYear(), game.getGameDescription());
							}
						} else {
							System.out.printf("No games found for genre: %s\n", genre);
						}
						break;
					case 3:
						System.out.print("Enter the publisher name: ");
						String publisher = in.nextLine().trim();
						ArrayList<VideoGame> publisherGames = db.getAllGamesByPublisher(publisher);
						if (publisherGames.size() > 0) {
							System.out.printf("\nAll games for publisher: %s\n", publisher);
							for (VideoGame game: publisherGames) {
								System.out.printf("%d: %s (%d) - %s\n", game.getGameId(), game.getGameName(), game.getReleaseYear(), game.getGameDescription());
							}
						} else {
							System.out.printf("No games found for publisher: %s\n", publisher);
						}
						break;
					default:
						isValidFunctionMode = false;
						System.out.println("You have entered an invalid function mode.");
						break;
				}
				if (isValidFunctionMode) {
					System.out.print("Would you like to perform another action? (y/n): ");
					String continueString = in.nextLine().trim().toLowerCase();
					if (continueString.equals("n")) break;
					System.out.print("\n\n\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("You have entered an invalid number.");
			}
		}
        in.close();
	}
}
