import java.util.Scanner;

// it is a sub class that the parents class is Customer
public class Children extends Customer {
    private double childPrice;
    private final double DISCOUNT = 0.5;

    // no args constructor
    public Children() {
    }

    // constructor with three parameters
    public Children(int adultQauntity, int childQuantity, int studentQuantity) {
        super(adultQauntity, childQuantity, studentQuantity);
    }

    // validation method that use to validate input by user
    public int input(int totalQty) {
        int qty;

        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter quantity of children tickets needed: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter NUMBERS ONLY!");
                System.out.print("\nEnter quantity of children tickets needed: ");
                sc.next();
            }
            qty = sc.nextInt();
            if (super.inputValidation(qty, totalQty)) {
                break;
            }

        } while (qty < 0 || qty > totalQty);
        return qty;
    }

    // override the method that found in parent class to calculate child price
    @Override
    public double calPrice() {
        this.childPrice = super.calPrice() * DISCOUNT;
        return childPrice;
    }

    // calculate total child price
    public double calTotalPrice() {
        return calPrice() * (double) super.getChildQuantity();
    }

    // display the unit price as well as total price in each category by using toString method
    @Override
    public String toString() {
        return String.format("%.2f", calPrice()) + "\t\t\t|" + String.format("%.2f", calTotalPrice());
    }
}