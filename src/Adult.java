import java.util.Scanner;

// it is a sub class that the parents class is Customer
public class Adult extends Customer {
    private double adultPrice;
    private final double DISCOUNT = 1;

    // no args constructor
    public Adult() {
    }

    // constructor with three parameters
    public Adult(int adultQauntity, int childQuantity, int studentQuantity) {
        super(adultQauntity, childQuantity, studentQuantity);
    }


    // validation method that use to validate input by user
    public int input(int totalQty) {
        int qty;

        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter quantity of adult tickets needed: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter NUMBERS ONLY!");
                System.out.print("\nEnter quantity of adult tickets needed: ");
                sc.next();
            }
            qty = sc.nextInt();
            if (super.inputValidation(qty, totalQty)) {
                break;
            }

        } while (qty < 0 || qty > totalQty);

        return qty;
    }

    // override the method that found in parent class to calculate adult price
    @Override
    public double calPrice() {
        adultPrice = super.calPrice() * DISCOUNT;
        return adultPrice;
    }

    // calculate total adult price
    public double calTotalPrice() {
        return calPrice() * (double) super.getAdultQuantity();
    }

    // display the unit price as well as total price in each category by using toString method
    @Override
    public String toString() {
        return String.format("%.2f", calPrice()) + "\t\t\t|" + String.format("%.2f", calTotalPrice());
    }
}