class TakeACab implements Service {
    private static final int PRICE_PER_KM = 33; // cents
    private static final int BOOKING_FEE = 200; // cents

    TakeACab() {
    }

    public int computeFare(int distance, int numOfPassengers, int timeOfService) {
        return BOOKING_FEE + PRICE_PER_KM * distance;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}
