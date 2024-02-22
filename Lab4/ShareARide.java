class ShareARide implements Service {
    private static  final int pricePerKM = 50; // cents
    private final int surchargePeakHour = 500; // cents, between 600 to 900
    private static final int peakHourStart = 600;
    private static final int peakHourEnd = 900;

    ShareARide() {
    }
     
    public int computeFare(int distance, int numOfPassengers, int timeOfService) {
        if (timeOfService >= peakHourStart && timeOfService <= peakHourEnd) {
            return (distance * pricePerKM + surchargePeakHour) / numOfPassengers;
        } else {
            return (distance * pricePerKM) / numOfPassengers;

        }
    }

    @Override
    public String toString() {
        return "ShareARide";
    }
}
