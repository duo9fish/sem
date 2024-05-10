import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private static final String SEPARATOR = "=================================================";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Ticket() {
    }

    public void printTicket(int adultQuantity, int childQuantity, int studentQuantity, 
                            Theatre theatre, String movieName, double aduPrice, double chiPrice, double stuPrice, int quantity, String category, double price, int startIndex) {
        if (theatre == null || movieName == null || theatre.takenSeats == null) {
            throw new IllegalArgumentException("Theatre and movie details must not be null");
        }

        if (theatre.takenSeats.size() < adultQuantity + childQuantity + studentQuantity) {
            System.out.println("Error: Not enough seats available for the number of tickets requested.");
            return;
        }

        int i = 0;
        printTickets(adultQuantity, "Adult", movieName, aduPrice, theatre, i);
        i += adultQuantity;
        printTickets(childQuantity, "Child", movieName, chiPrice, theatre, i);
        i += childQuantity;
        printTickets(studentQuantity, "Student", movieName, stuPrice, theatre, i);

        LocalDateTime now = LocalDateTime.now();
        for (int j = 0; j < quantity; j++) {
            System.out.println(SEPARATOR);
            System.out.printf("TICKET\t\t\tCategory: %s%n", category);
            System.out.println();
            System.out.println("Hall: " + (theatre.takenSeats.get(i)).getHallNumber());
            System.out.println("Seat: " + (theatre.takenSeats.get(i)).getSeatNumber());
            System.out.printf("Movie: %s%n", movieName);
            System.out.printf("Price: RM %.2f%n", price);
            System.out.println(FORMATTER.format(now));
            System.out.println(SEPARATOR + "\n");
        }
    }

    private void printTickets(int quantity, String category, String movieName, double price, Theatre theatre, int startIndex) {
        LocalDateTime now = LocalDateTime.now();
        for (int j = 0; j < quantity; j++) {
            Seat seat = theatre.takenSeats.get(startIndex + j);
            System.out.println(SEPARATOR);
            System.out.printf("TICKET\t\t\tCategory: %s%n", category);
            System.out.println();
            System.out.printf("Hall: %d%n", seat.getHallNumber());
            System.out.printf("Seat: %d%n", seat.getSeatNumber());
            System.out.printf("Movie: %s%n", movieName);
            System.out.printf("Price: RM %.2f%n", price);
            System.out.println(FORMATTER.format(now));
            System.out.println(SEPARATOR + "\n");
        }
    }
}
