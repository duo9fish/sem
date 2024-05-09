import java.util.Scanner;

public class mainProgram {
    public static void main(String[] args) {
        Boolean cont = true;
        MovieModule movieModule = new MovieModule();

        Theatre[] halls = {
            new Theatre(1),
            new Theatre(2),
            new Theatre(3),
            new Theatre(4),
            new Theatre(5),
        };

        do {
            // Movies Module
            movieModule.displayMovies();
            int movieChosenIndex = movieModule.selectMovie();
            Movie selectedMovie = movieModule.getSelectedMovie(movieChosenIndex - 1);

            System.out.println("\nMovie Chosen: ");
            selectedMovie.movieTableHeader();
            System.out.printf("|%-3d", movieChosenIndex);
            selectedMovie.printMovieDetails();
            printLine();

            // Ticketing Module
            Adult adult = new Adult();
            Children children = new Children();
            Student student = new Student();

            int totalQuantity, adultQuantity, childQuantity, studentQuantity;
            do {
                totalQuantity = validateInputforTotal(30, true); // input and validation

                // Input Quantity of Adult tickets
                adultQuantity = adult.validateInput(totalQuantity, false);
                totalQuantity -= adultQuantity;
                adult.ticketsLeft(totalQuantity); // Print total tickets left

                // Input Quantity of Student tickets
                studentQuantity = student.validateInput(totalQuantity, false);
                totalQuantity -= studentQuantity;
                adult.ticketsLeft(totalQuantity); // Print total tickets left

                // Input Quantity of Children tickets
                if (!"18+".equals(selectedMovie.getMoviePGRating())) {
                    childQuantity = children.validateInput(totalQuantity, false);
                    totalQuantity -= childQuantity;
                } else {
                    childQuantity = 0;
                }

                if (totalQuantity != 0) {
                    System.out.println(
                            "Total ticket count does not match with total quantity of tickets needed, Please try again!");
                }
            } while (totalQuantity != 0);

            // Pass value into customer classes for calculations
            Customer adultTickets = new Adult(adultQuantity, childQuantity, studentQuantity);
            Customer childTickets = new Children(adultQuantity, childQuantity, studentQuantity);
            Customer studentTickets = new Student(adultQuantity, childQuantity, studentQuantity);

            // Seat Selection Module
            int hallNumber = selectedMovie.getMovieHallNumber();
            Theatre theatre = halls[hallNumber - 1];
            theatre.displaySeats(hallNumber);

            int seatNumber;
            int totalCustomer = adultQuantity + childQuantity + studentQuantity;

            for (int i = 0; i < totalCustomer; i++) {
                seatNumber = theatre.inputValidation(i); // get and validate seat number input
                theatre.removeSeat(seatNumber); // remove seat number (mark as occupied)
                theatre.takenSeats.add(new Seat(seatNumber, hallNumber)); // apppend taken seat info in takenSeats[]
                theatre.displaySeats(hallNumber); // display current seat diagram
            }

            // Ticket Printing Module
            Adult adu = new Adult();
            Children chi = new Children();
            Student stu = new Student();
            Payment pay = new Payment();

            double aduPrice = adu.calPrice() * adultQuantity;
            double chiPrice = chi.calPrice() * childQuantity;
            double stuPrice = stu.calPrice() * studentQuantity;

            pay.setAmount(aduPrice + chiPrice + stuPrice);
            System.out.println("\n");
            System.out.println("Ticket Payment:");
            printLine();
            System.out.println("Category\t|Ticket Quantity\t|Pricing(RM Per Unit)\t|Total Pricing(RM)\t|");
            printLine();
            System.out.print("Adult\t\t|" + adultQuantity + "\t\t\t|" + adultTickets.toString() + "\t\t\t|");
            System.out.print("\nChild\t\t|" + childQuantity + "\t\t\t|" + childTickets.toString() + "\t\t\t|");
            System.out.print("\nStudent\t\t|" + studentQuantity + "\t\t\t|" + studentTickets.toString() + "\t\t\t|\n");
            printLine();
            System.out.println("Total(RM) : \t" + pay.getAmount());
            printLine();

            pay.waitForUserToProceed(); // Changed from pressEnterToProceed

            // Clear screen for payment
            System.out.print("\033[H\033[2J");
            System.out.flush();
            printLine();
            System.out.println("|Total Payable(RM): " + pay.getAmount() + "\t\t\t\t\t\t\t\t|");
            System.out.println("|Select Your Payment Method\t\t\t\t\t\t\t\t|");
            printLine();
            System.out.println("|1. Cash Payment\t\t\t\t\t\t\t\t\t| \n|2. Credit/Debit Card Payment\t\t\t\t\t\t\t\t|");
            printLine();
            pay.setPaymentType(); // Changed from setPaymentType
            System.out.print("\n\n***Payment complete****");
            pay.waitForUserToProceed(); // Changed from pressEnterToProceed

            // Ticket Printing
            Ticket tic = new Ticket(); // create new ticket with movie name
            System.out.print("\033[H\033[2J");
            System.out.flush();
            printLine();
            System.out.println(
                    adultQuantity + " Adult Ticket(s), " + childQuantity + " Children Ticket(s), " + studentQuantity
                            + " Student Ticket(s)");
            printLine();
            tic.printTicket(adultQuantity, childQuantity, studentQuantity, theatre,
                    selectedMovie.getMovieName(), adu.calPrice(), chi.calPrice(), stu.calPrice());
            cont = adult.askCustomer(); // Check if any more customer
        } while (cont);

        System.out.println("\nThank you! Have a great day.");
    }

    public static void printLine() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    public static int validateInputforTotal(int totalQty, boolean isTotal) {
    Scanner sc = new Scanner(System.in);
    int qty;
    String message = "quantity of total tickets needed";

        do {
            System.out.print("\nEnter " + message + ": ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter NUMBERS ONLY!");
                System.out.print("\nEnter " + message + ": ");
                sc.next();
            }
            qty = sc.nextInt();
    
            if (qty < 0 || qty > totalQty) {
                System.out.println("The maximum number of tickets that can be purchased at one time is limited to 30.");
            } else {
                break;
            }
        } while (true);

        return qty;
    }
}
