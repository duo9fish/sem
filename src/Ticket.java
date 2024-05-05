import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket { // remove extend
    public Ticket() {
    }

    public void printTicket(int adultQuantity, int childQuantity, int studentQuantity, 
                            Theatre theatre, String movieName, double aduPrice, double chiPrice, double stuPrice) {
                                
        SimpleDateFormat formattor = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        int i = 0;
        for (int j = 0; j < adultQuantity; j++) {
            System.out.println("=================================================");
            System.out.println("TICKET \t\t\tCategory: Adult");
            System.out.println("");
            System.out.println("Hall: " + (theatre.takenSeats.get(i)).getHallNumber());
            System.out.println("Seat: " + (theatre.takenSeats.get(i)).getSeatNumber());
            System.out.println("Movie: " + movieName);
            System.out.print("Price: RM " + aduPrice + "\n");
            System.out.println(formattor.format(date));
            i++;
            System.out.println("=================================================\n");
        }
        for (int k = 0; k < childQuantity; k++) {
            System.out.println("=================================================");
            System.out.println("TICKET \t\t\tCategory: Child");
            System.out.println("");
            System.out.println("Hall: " + (theatre.takenSeats.get(i)).getHallNumber());
            System.out.println("Seat: " + (theatre.takenSeats.get(i)).getSeatNumber());
            System.out.println("Movie: " + movieName);
            System.out.print("Price: RM " + chiPrice + "\n");
            System.out.println(formattor.format(date));
            i++;
            System.out.println("=================================================\n");
        }
        for (int l = 0; l < studentQuantity; l++) {
            System.out.println("=================================================");
            System.out.println("TICKET \t\t\tCategory: Student");
            System.out.println("");
            System.out.println("Hall: " + (theatre.takenSeats.get(i)).getHallNumber());
            System.out.println("Seat: " + (theatre.takenSeats.get(i)).getSeatNumber());
            System.out.println("Movie: " + movieName);
            System.out.print("Price: RM " + stuPrice + "\n");
            System.out.println(formattor.format(date));
            i++;
            System.out.println("=================================================\n");
        }
    }
}
