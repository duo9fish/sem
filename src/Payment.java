import java.util.Scanner;
import java.io.IOException;

public class Payment {
    private double amount;

    public Payment() {
    }

    public Payment(double amt) {
        this.amount = roundAmount(amt);
    }

    // Get amount to print in the bill
    public double getAmount() {
        return roundAmount(amount);
    }

    // Set the amount for paying, gets value from totalPayable
    public void setAmount(double amount) {
        this.amount = amount;
    }

    private double roundAmount(double amt) {
        return Math.round(amt * 100) / 100.0;
    }

    public void waitForUserToProceed() {
        System.out.println("\nPress Enter key to continue...\n");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }

    // To select payment method
    public void setPaymentType() {
        String paymentMethod;
        Scanner sc = new Scanner(System.in);
        int result;
        while (true) {
            try {
                System.out.print("\nSelect Your Payment Method (1 / 2): ");
                paymentMethod = sc.next();
                result = Integer.parseInt(paymentMethod);
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
                continue;
            }

            if (result != 1 && result != 2) {
                System.out.print("\nInvalid input! Please select (1 / 2):");
            } else {
                break;
            }
        }
        // Payment selection and validation
        switch (paymentMethod) {
            case "1":
                double cashAmount, tempCash;
                System.out.print("\nEnter Cash To Pay(RM): ");
                cashAmount = sc.nextDouble();
                while (cashAmount < amount) {
                    System.out.print("\nInsufficient cash for payment. Please add more.\nTotal paid(RM): "
                            + (cashAmount) + "\nTotal Payable(RM): " + (getAmount()) + "\nAdd: ");
                    tempCash = sc.nextDouble();
                    cashAmount += tempCash;
                }
                if (cashAmount >= amount) {
                    System.out
                            .println("Payment Successful. Total paid is RM " + String.format("%.2f", cashAmount) + ".");
                    System.out.print("\nBalance(RM): " + String.format("%.2f", (cashAmount - amount)));
                }
                break;
            case "2":
                //String name, cardNo;
                //String expireDate = "1000100010001000";
                CreditCardPayment ccp = new CreditCardPayment();
                ccp.promptForCardDetails();
                break;
        }
    }

    // Method to handle payment
    public void processPayment() {
        switch (getPaymentType()) {
            case 1:
                handleCashPayment();
                break;
            case 2:
                CreditCardPayment ccp = new CreditCardPayment();
                ccp.promptForCardDetails();
                break;
            default:
                System.out.println("Invalid payment method selected.");
        }
    }

    // Method to handle cash payment
    private void handleCashPayment() {
        try (Scanner sc = new Scanner(System.in)) {
            double cashAmount, tempCash;
            System.out.print("\nEnter Cash To Pay(RM): ");
            cashAmount = sc.nextDouble();
            while (cashAmount < amount) {
                System.out.print("\nInsufficient cash for payment. Please add more.\nTotal paid(RM): "
                        + (cashAmount) + "\nTotal Payable(RM): " + (getAmount()) + "\nAdd: ");
                tempCash = sc.nextDouble();
                cashAmount += tempCash;
            }
            if (cashAmount >= amount) {
                System.out.println("Payment Successful. Total paid is RM " + String.format("%.2f", cashAmount) + ".");
                System.out.print("\nBalance(RM): " + String.format("%.2f", (cashAmount - amount)));
            }
        }
    }

    // Method to handle card payment
    private void handleCardPayment() {
        try (Scanner sc = new Scanner(System.in)) {
            String name, cardNo;
            String expireDate = "1000100010001000"; // Dummy expiry date
            CreditCardPayment ccp = new CreditCardPayment();
            System.out.print("Enter Card Holder Name: ");
            name = sc.next();
            ccp.setCardId(name);
            ccp.validateExpireDate(expireDate);
            ccp.setExpireDate(expireDate);
            System.out.print("Enter Card Number: ");
            cardNo = sc.next();
            ccp.validateCardNo(cardNo);
            ccp.setCardNo(cardNo);
            paymentDetail();
        }
    }

    // Print Card payment details
    public void paymentDetail() {
        CreditCardPayment cp = new CreditCardPayment();
        System.out.println(
                "Total Paid(RM): " + String.format("%.2f", getAmount()) + " by card");
    }
}
