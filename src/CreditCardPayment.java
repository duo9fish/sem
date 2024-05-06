import java.util.Scanner;
import java.time.YearMonth;

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
    
    public boolean luhnCheck(String cardNo){
        cardNo = cardNo.replaceAll("\\s+", "");
        int sum = 0;
        boolean alternate = false;
        for(int i = cardNo.length() - 1; i >= 0; i--){
            int n = Integer.parseInt(cardNo.substring(i, i+1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return(sum % 10 == 0);
    }

    // Validate card number using Luhn's algorithm
    public boolean validateCardNo(String cardNo) {
        cardNo = cardNo.replaceAll("\\s+", ""); // remove any spaces
        if (!cardNo.matches("\\d{16}")) {
            System.out.println("Invalid Credit Card Number format. Example: 1000 1234 1234 1234");
            return false;
        }

    // Luhn algorithm
    // Example of card number 
    // 4539 1488 0343 6467
    // 6011 1111 1111 1117
    // 5105 1051 0510 5100
    // 4111 1111 1111 1111

        if (!luhnCheck(cardNo)) {
            System.out.println("Invalid Credit Card Number: Luhn Check Failed");
            return false;
        }
    return true;
    }

    public boolean validateExpireDate(String expireDate) {
        if (!expireDate.matches("[0-9]{4}")) {
            System.out.println("Invalid Expiry Date format. Example: 0125");
            return false;
        }

        int month = Integer.parseInt(expireDate.substring(0, 2));
        int year = Integer.parseInt("20" + expireDate.substring(2, 4));
        YearMonth expiry = YearMonth.of(year, month);
        YearMonth now = YearMonth.now();

        if (expiry.isBefore(now)) {
            System.out.println("Card Expiry Date is in the past. Please try again.");
            return false;
        }
        return true;
    }

    // Prompt for card details with validation and retry limit
    public void promptForCardDetails() {
        Scanner sc = new Scanner(System.in);
        int retries = 3; // Limit retries to 3 attempts for each input

        System.out.print("Enter Card Holder Name: ");
        cardId = sc.nextLine();

        for (int i = 0; i < retries; i++) {
            System.out.print("Enter Card Number: ");
            cardNo = sc.nextLine();
            if (validateCardNo(cardNo)) break;
            if (i == retries - 1) {
                System.out.println("Failed to provide valid Card Number after " + retries + " attempts.");
                return;
            }
        }

        for (int i = 0; i < retries; i++) {
            System.out.print("Enter Card Expiry Date (MMYY): ");
            expireDate = sc.nextLine();
            if (validateExpireDate(expireDate)) break;
            if (i == retries - 1) {
                System.out.println("Failed to provide valid Expiry Date after " + retries + " attempts.");
                return;
            }
        }
    }

}
