class JustRide implements Service {
    private final int pricePerKM = 22; // cents
    private final int surchargePeakHour = 500; // cents, between 600 to 900
    private static final int peakHourStart = 600;
    private static final int peakHourEnd = 900;

    JustRide() {
    }
    
    public int computeFare(int distance, int numOfPassengers, int timeOfService) {
        if (timeOfService >= peakHourStart && timeOfService <= peakHourEnd) {
            return distance * pricePerKM + surchargePeakHour;
        } else {
            return distance * pricePerKM;
        }
    }

    @Override
    public String toString() {
        return "JustRide";
    }
}
