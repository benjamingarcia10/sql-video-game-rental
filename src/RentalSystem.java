import java.util.Scanner;

public class RentalSystem {
	private static String[] validFunctionCommands = new String[] {
		"1. Function 1",
		"2. Function 2",
		"3. Function 3",
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
						System.out.println("Starting function 1");
						break;
					case 2:
						System.out.println("Starting function 2");
						break;
					default:
						isValidFunctionMode = false;
						System.out.println("You have entered an invalid function mode.");
						break;
				}
				if (isValidFunctionMode) {
					System.out.printf("Successfully completed function mode %d\n", functionMode);
				}
			} catch (NumberFormatException e) {
				System.out.println("You have entered an invalid number.");
			}
		}
        in.close();
	}
}
