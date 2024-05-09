// Define CustomerInterface containing methods validateInput and calPrice
public interface CustomerInterface {
    int validateInput(int totalQty, boolean isTotal);
    double calPrice();
}