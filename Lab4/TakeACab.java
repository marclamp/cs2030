class TakeACab implements Service {
    private final int pricePerKM = 33; // cents
    private final int bookingFee = 200; // cents

    TakeACab() {
    }

    public int computeFare(int distance, int nuOfPassengers, int time) {
        return this.bookingFee + this.pricePerKM * distance;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}
