import java.util.Scanner;

public class prac_03_atm {

	public static void main(String[] args) { // public static main method

		boolean redo = true;
		int amount = 0;
		Scanner scanner = new Scanner(System.in);
		while (redo) { // repeat request for the valid input

			// prompt information
			System.out.println("Enter the amount you want to withdraw: ");
			try {
				amount = scanner.nextInt(); // get input
				redo = false; // continue the program
			} catch (Exception e) { // catch invalid input
				System.out.println("Please enter a valid number.");
				scanner.next(); // wait for next input
			}
		}
		scanner.close();
		System.out.println(run(amount)); // call method and print the result

	}

	public static String run(int withdraw) {
		int valOfNote1 = 50; // flexible for other values of note, but note1 should be bigger
		int valOfNote2 = 20;
		int numOfNote1 = 0;
		int numOfNote2 = 0;
		String message;
		// search the fewest notes solution
		for (int i = withdraw / valOfNote1; i >= 0; i--) { // count down from the maximum number of 50 notes

			int remains = withdraw - i * valOfNote1;
			if (remains % valOfNote2 == 0) {
				numOfNote1 = i;
				numOfNote2 = remains / valOfNote2;
				break; // got solution, escape from loop

			}
		}
		// return result
		if (numOfNote1 != 0 || numOfNote2 != 0) {
			message = "Here are " + numOfNote2 + " $" + valOfNote2 + " notes and " + numOfNote1 + " $" + valOfNote1
					+ " notes.";
		} else {
			message = "Sorry, the value you input cannot be withdrawn.";
		}
		return message;
	}

}
