import java.util.Scanner;


// Subclass of Customer representing adult customers
public class Adult extends Customer
{

  private double adultPrice;
  private final double DISCOUNT = 1;


  // No-args constructor
  public Adult (){}

  // Constructor with parameters
  public Adult (int adultQuantity, int childQuantity, int studentQuantity)
  {

	super (adultQuantity, childQuantity, studentQuantity);

  }



  // Validate input for adult ticket quantity
  @Override
    public int validateInput(int totalQty, boolean isTotal) {
        Scanner sc = new Scanner(System.in);
        int qty;
        String message = "quantity of adult tickets needed";

        do {
            System.out.print("\nEnter " + message + ": ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter numbers only!");
                System.out.print("\nEnter " + message + ": ");
                sc.next();
            }
            qty = sc.nextInt();

            if (!isTotal && (qty < 0 || qty > totalQty)) {
                System.out.println("Ticket count cannot be more than total quantity of tickets needed!");
            } else {
                break;
            }
        } while (true);

        return qty;
    }
    
    
  // Override method to calculate adult price
     @Override
    public double calPrice() {
        adultPrice = super.calPrice() * DISCOUNT;
        return adultPrice;
    }

    // Method to calculate total price of adult tickets
    @Override
    public double calTotalPrice() {
        return calPrice() * super.getAdultQuantity();
    }

    // Method to display the unit price and total price of adult tickets
    @Override
    public String toString() {
        return String.format("%.2f", calPrice()) + "\t\t\t|" + String.format("%.2f", calTotalPrice());
    }
}
