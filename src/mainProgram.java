import java.util.Scanner;

public class mainProgram {
    public static void main(String[] args) {
        Boolean cont = true;
        MovieModule movieModule = new MovieModule();
        

        // Create theatre objects (under 3. SEAT SELECTION MODULE) - theatre halls only
        // need to be created once (else clears takenSeats after each loop)
        Theatre[] halls = {
                new Theatre(1),
                new Theatre(2),
                new Theatre(3),
                new Theatre(4),
                new Theatre(5),
        };

        do {
            //// --1. MOVIES MODULE--////
            // Display Header and Available Movies
            movieModule.displayMovies();
            int movieChosenIndex = movieModule.selectMovie();
            Movie selectedMovie = movieModule.getSelectedMovie(movieChosenIndex - 1);

            System.out.println("\nMovie Chosen: ");
            selectedMovie.movieTableHeader();
            System.out.printf("|%-3d", movieChosenIndex);
            selectedMovie.printMovieDetails();
            PaymentUtil.printLine();

            //// --------------------////

            //// --2. TICKETING MODULE--////
            // create three object to call methods
            Adult adult = new Adult();
            Children children = new Children();
            Student student = new Student();

            int totalQuantity,
                    adultQuantity,
                    childQuantity,
                    studentQuantity;
            // Input Total Quantity of Ticket
            do {
                totalQuantity = adult.inputValidation(); // input and validation

                // Input Quantity of Adult tickets
                adultQuantity = adult.input(totalQuantity);
                totalQuantity -= adultQuantity;

                adult.ticketsLeft(totalQuantity); // Print total tickets (specified by user prior) left

                // Input Quantity of Student tickets
                studentQuantity = student.input(totalQuantity);
                totalQuantity -= studentQuantity;

                adult.ticketsLeft(totalQuantity); // Print total tickets (specified by user prior) left

                // Input Quantity of Children tickets
                if (selectedMovie.getMoviePGRating() != "18+") {
                    childQuantity = children.input(totalQuantity);
                    totalQuantity -= childQuantity;
                } else {
                    childQuantity = 0;
                }

                if (totalQuantity != 0) {
                    System.out.println(
                            "Total ticket count does not match with total quantity of tickets needed, Please try again!");
                }
            } while (totalQuantity != 0);

            // to pass value into customer class and those sub-classes for claculations
            // purpose
            Customer adultTickets = new Adult(adultQuantity, childQuantity, studentQuantity);
            Customer childTickets = new Children(adultQuantity, childQuantity, studentQuantity);
            Customer studentTickets = new Student(adultQuantity, childQuantity, studentQuantity);
            //// --------------------////

            //// -- 3. SEAT SELECTION MODULE--////

            // Display seat diagram (based on hall number)
            int hallNumber = selectedMovie.getMovieHallNumber();
            Theatre theatre = halls[hallNumber - 1];
            theatre.displaySeats(hallNumber);

            // Select seats for each customer
            int seatNumber;
            int totalCustomer = adultQuantity + childQuantity + studentQuantity;

            for (int i = 0; i < totalCustomer; i++) {
                seatNumber = theatre.inputValidation(i); // get and validate seat number input
                theatre.removeSeat(seatNumber); // remove seat number (mark as occupied)
                theatre.takenSeats.add(new Seat(seatNumber, hallNumber)); // apppend taken seat info in takenSeats[]
                theatre.displaySeats(hallNumber); // display current seat diagram (know which seats taken)
            }

            //// --------------------////

            //// --TICKET PRINTING MODULE--////
            // Displaying Bill for Payment
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
            // Press Enter To Proceed to Payment
            pay.pressEnterToProceed();

            // Clear screen for payment
            System.out.print("\033[H\033[2J");
            System.out.flush();
            printLine();
            System.out.println("|Total Payable(RM): " + pay.getAmount() + "\t\t\t\t\t\t\t\t|");
            System.out.println("|Select Your Payment Method\t\t\t\t\t\t\t\t|");
            printLine();
            System.out.println("|1. Cash Payment\t\t\t\t\t\t\t\t\t| \n|2. Credit/Debit Card Payment\t\t\t\t\t\t\t\t|");
            printLine();
            pay.setPaymentType();
            System.out.print("\n\n***Payment complete****");
            pay.pressEnterToProceed();

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
            // Check if anymore customer
            cont = adult.askCustomer(); // the method is in Customer table to check condition
        } while (cont);

        System.out.println("\nThank you! Have a great day.");
    }

    public static void printLine() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}