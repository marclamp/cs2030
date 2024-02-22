class ShareARide implements Service {
    private static final int PRICE_PER_KM = 50; // cents
    private static final int SURCHARGE_PEAK = 500; // cents, between 600 to 900
    private static final int PEAK_HOUR_START = 600;
    private static final int PEAK_HOUR_END = 900;

    ShareARide() {
    }
     
    public int computeFare(int distance, int numOfPassengers, int timeOfService) {
        if (timeOfService >= PEAK_HOUR_START && timeOfService <= PEAK_HOUR_END) {
            return (distance * PRICE_PER_KM + SURCHARGE_PEAK) / numOfPassengers;
        } else {
            return (distance * PRICE_PER_KM) / numOfPassengers;

        }
    }

    @Override
    public String toString() {
        return "ShareARide";
    }
}
