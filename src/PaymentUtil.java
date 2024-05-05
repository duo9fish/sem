public class PaymentUtil {

    // Press Enter to proceed utility
    public static void pressEnterToProceed() {
        System.out.print("Press Enter to proceed...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Printing a line separator
    public static void printLine() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}
