class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoaders;
    private final int serviceTime;
    private static final int CONVERT_TO_MINS = 60;


    Cruise(String identifier, int arrivalTime, int numOfLoaders, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numOfLoaders = numOfLoaders;
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return this.serviceTime;
    }

    public int convertToMins(int time) {
        return time / 100 * CONVERT_TO_MINS + time % 100;
    }

    public int getArrivalTime() {
        return convertToMins(this.arrivalTime);
    }

    public int getNumOfLoadersRequired() {
        return this.numOfLoaders;
    }

    @Override
    public String toString() {
        return this.identifier + "@" + String.format("%04d", this.arrivalTime);
    }
}
