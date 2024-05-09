import java.util.Scanner;

// it is a sub class that the parents class is Customer
public class Children extends Customer
{
  private double childPrice;
  private final double DISCOUNT = 0.5;

  // no args constructor
  public Children (){}

  // Constructor with parameters
    public Children(int adultQuantity, int childQuantity, int studentQuantity) {
        super(adultQuantity, childQuantity, studentQuantity);
    }

  // validation method that use to validate input by user
  @Override
    public int validateInput(int totalQty, boolean isTotal) {
        Scanner sc = new Scanner(System.in);
        int qty;
        String message = "quantity of children tickets needed";

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


  // override the method that found in parent class to calculate child price
  @Override
  public double calPrice() {
       this.childPrice = super.calPrice() * DISCOUNT;
       return childPrice;
    }

    // Method to calculate total price of child tickets
    @Override
    public double calTotalPrice() {
        return calPrice() * super.getChildQuantity();
    }

    // Method to display the unit price and total price of child tickets
    @Override
    public String toString() {
        return String.format("%.2f", calPrice()) + "\t\t\t|" + String.format("%.2f", calTotalPrice());
    }
}
