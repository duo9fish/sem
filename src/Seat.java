public class Seat {
    private int seatNumber;
    public int hallNumber;

    public Seat(int seatNumber, int hallNumber) {
        this.seatNumber = seatNumber;
        this.hallNumber = hallNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public int getHallNumber() {
        return hallNumber;
    }
}
