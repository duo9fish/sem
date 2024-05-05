import java.util.Scanner;

public abstract class Customer { // abstract applied

    private int adultQuantity;
    private int childQuantity;
    private int studentQuantity;
    private final double ADULT_PRICE = 17.00;  // a fixed price that cannot be change

    // no args constructor
    public Customer() {
    }

    // constructor with three parameters
    public Customer(int adultQauntity, int childQuantity, int studentQuantity) {
        this.adultQuantity = adultQauntity;
        this.childQuantity = childQuantity;
        this.studentQuantity = studentQuantity;
    }

      // no body where it is an abstract method
      public abstract double calTotalPrice();

    // getter and setter
    public int getAdultQuantity() {
        return adultQuantity;
    }

    public int getChildQuantity() {
        return childQuantity;
    }

    public int getStudentQuantity() {
        return studentQuantity;
    }

    public void setAdultQuantity(int adultQuantity) {
        this.adultQuantity = adultQuantity;
    }

    public void setChildQuantity(int childQuantity) {
        this.childQuantity = childQuantity;
    }

    public void setStudentQuantity(int studentQuantity) {
        this.studentQuantity = studentQuantity;
    }

    // method that calculate every category of price
    public double calPrice() {
        return ADULT_PRICE;
    }

    // validation method that use to validate the value of total quantity entered by user
    public int inputValidation() {
        int qty;
        Scanner sc = new Scanner(System.in);
        do { // Validation
            System.out.print("\nEnter total quantity of ticket needed: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter NUMBERS ONLY!");
                System.out.print("\nEnter total quantity of ticket needed: ");
                sc.next();
            }
            qty = sc.nextInt();
            if (qty < 1) {
                System.out.println("Please buy at least ONE ticket!");
            } else if (qty > 30) {
                System.out.println("You are not allowed to buy more than 30 tickets at one time!");
            } else {
                break;
            }
        } while (qty < 1 || qty > 30);
        return qty;
    }

    // validate the boolean type to check whether true or false
    public boolean inputValidation(int qty, int totalQty) {
        if (qty < 0) {
            System.out.println("Invalid input! Please enter POSITIVE NUMBERS ONLY");
            return false;
        } else if (qty > totalQty) {
            System.out.println("Ticket count cannot be more than total quantity of tickets needed!");
            return false;
        } else {
            return true;
        }
    }

    // to display the quantity of tickets left
    public void ticketsLeft(int qty) {
        System.out.println("\nTotal tickets left: " + qty);
    }
    
    // validate whether user want to continue buying or not with condition y or n
    public Boolean askCustomer() {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        char confirm = 'Y';

        System.out.print("\nAny more customers? (y / n) : ");
        confirm = sc.next().charAt(0); // get character input (y / n)
        confirm = Character.toUpperCase(confirm); // make char input uppercase
        switch (confirm) {
            case 'Y': cont = true; break;
            case 'N': cont = false; break;
            default: 
                System.out.println("Invalid input. Please try again."); 
                cont = askCustomer(); // recursion
                break;
        }
        return cont;
    }
}