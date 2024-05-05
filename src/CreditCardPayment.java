import java.util.Scanner;

public class CreditCardPayment extends Payment {
    protected String cardId, expireDate, cardNo;

    public CreditCardPayment() {
    }

    public void setCardId(String cardID) {
        this.cardId = cardID;
    }

    public String getCardId() {
        return cardId;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String validateCardNo(String l) {
        Scanner sc = new Scanner(System.in);
        while (!l.matches("[1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
            System.out.println("Invalid Credit Card Number. Example: 1000123412341234");
            System.out.println("Please Try again: ");
            l = sc.nextLine();
        }
        return l;
    }

    public String validateExpireDate(String i) {
        Scanner sc = new Scanner(System.in);
        Boolean retry = true;
        do {
            System.out.print("\nEnter Card Expiry Date(MMYY): ");
            expireDate = sc.nextLine();
            while (!expireDate.matches("[0-9][0-9][0-9][0-9]")) {
                System.out.println("Invalid Expiry Date (MMYY). Example: 0125");
                System.out.println("Please Try again: ");
                expireDate = sc.nextLine();
            }
            if (expireDate == null) {
                System.out.println("Card Expiry Date cannot be null. Please try again: ");
                expireDate = sc.nextLine();
            } else {
                retry = false;
            }
        } while (retry);
        return i;
    }

}
