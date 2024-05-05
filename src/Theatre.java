import java.util.*;

public class Theatre {
	public int hallNumber;
	public ArrayList<Seat> takenSeats = new ArrayList<Seat>(); // ArrayList (resizable arrays, append)
	private int[] availableSeats = { // display for seat selection (3 arrays encapped inside availableSeats array)
			// ========= movie screen =========
			101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
			111, 112, 113, 114, 115, 116, 117, 118, 119, 120,
			121, 122, 123, 124, 125, 126, 127, 128, 129, 130,
			131, 132, 133, 134, 135, 136, 137, 138, 139, 140,
			141, 142, 143, 144, 145, 146, 147, 148, 149, 150,
			151, 152, 153, 154, 155, 156, 157, 158, 159, 160,
			161, 162, 163, 164, 165, 166, 167, 168, 169, 170,
			171, 172, 173, 174, 175, 176, 177, 178, 179, 180,
			181, 182, 183, 184, 185, 186, 187, 188, 189, 190
	};

	public Theatre(int hallNumber) { // Constructor
		this.hallNumber = hallNumber;
	}

	public void displaySeats(int hallNumber) {
		// display movie screen
		System.out.println("");
		System.out.println("████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████████");

		System.out.println("████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ Theatre Hall " + hallNumber + " ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████████");
		System.out.println("████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████████");
		System.out.println("████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ Movie Screen ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████████");
		System.out.println("████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████████");
		System.out.println("████████                                                                                 ████████");
		System.out.println("████████╔═══════════════════════════════════════════════════════════════════════════════╗████████");

		// display seats
		for (int i = 0; i < availableSeats.length; i++) {
			if (i == 0 || i == 10 || i == 20 || i == 30 || i == 40 || i == 50 || i == 60 || i == 70 || i == 80) { // spaces out the 1st, 11th and 21st column of every row
				System.out.printf("%8s", "████████"); // spaceout
			}

			System.out.printf("║%5s", availableSeats[i]); // displaying each element from seat_num array, one at a time
			System.out.printf("%2s", ""); // spaceout

			if (i == 89) { // formatting. When in the last seat
				System.out.printf("%8s", "║████████"); // spaceout
				System.out.println("\n████████╚═══════════════════════════════════════════════════════════════════════════════╝████████");
			} else if (i == 9 || i == 19 || i == 29 || i == 39 || i == 49 || i == 59 || i == 69 || i == 79) { // formatting. Dividers at the end of each row
				System.out.printf("%8s", "║████████"); // spaceout
				System.out.println("\n████████╬═══════╬═══════╬═══════╬═══════╬═══════╬═══════╬═══════╬═══════╬═══════╬═══════╬████████");
			}
		}
	}

	public int inputValidation(int i) { // input seats then validate it
		Scanner sc = new Scanner(System.in); // declare scanner object
		int seatNumber;

		System.out.print("\nEnter Seat Number " + (i + 1) + " : ");

		while (!sc.hasNextInt()) { // check if input is integer
			System.out.println("Invalid input! Please enter an integer.");
			System.out.print("\nEnter Seat Number " + (i + 1) + " : ");
			sc.next(); // stop for input
		}
		seatNumber = sc.nextInt(); // collect input (integer)

		for (int j = 0; j < takenSeats.size(); j++) { // check if input seat has already been taken (linear search through whole takenSeats array)
			if (seatNumber == takenSeats.get(j).getSeatNumber()) {
				System.out.println("Seat has been taken. Please select again.");
				seatNumber = inputValidation(i); // recursion
			}
		}

		if (seatNumber < 101 || seatNumber > 190) { // check if input exceeded range
			System.out.println("Selected seat out of class seat range. Please select again.");
			seatNumber = inputValidation(i); // recursion
		}
		return seatNumber;
	}

	public void removeSeat(int seatNumber) {
		for (int i = 0; i < availableSeats.length; i++) { // linear search through whole availableSeats array
			if (seatNumber == availableSeats[i]) {
				availableSeats[i] = 0; // seat occupied
			}
		}
	}
	
}