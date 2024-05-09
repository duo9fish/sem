import java.util.Scanner;

// it is a sub class that the parents class is Customer
public class Student extends Customer
{
  private double studentPrice;
  private final double DISCOUNT = 0.75;

  // no args constructor
  public Student() {}

  // constructor with three parameters
  public Student(int adultQuantity, int childQuantity, int studentQuantity) {
        super(adultQuantity, childQuantity, studentQuantity);
    }

  // validation method that use to validate input by user
  @Override
    public int validateInput(int totalQty, boolean isTotal) {
        Scanner sc = new Scanner(System.in);
        int qty;
        String message = "quantity of student tickets needed";

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
    
  // override the method that found in parent class to calculate student price
    @Override
    public double calPrice() {
        this.studentPrice = super.calPrice() * DISCOUNT;
        return studentPrice;
    }

    // Method to calculate total price of student tickets
    @Override
    public double calTotalPrice() {
        return calPrice() * super.getStudentQuantity();
    }

    // Method to display the unit price and total price of student tickets
    @Override
    public String toString() {
        return String.format("%.2f", calPrice()) + "\t\t\t|" + String.format("%.2f", calTotalPrice());
    }
}
