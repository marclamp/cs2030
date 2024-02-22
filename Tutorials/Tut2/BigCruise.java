class BigCruise extends Cruise {
    private static final double PASSENGERS_PER_MIN = 50;
    private static final double LENGTH_PER_LOADER = 40;

    BigCruise(String identifier, int arrivalTime, int lengthOfCruise, int numOfPassengers) {
        super(identifier, arrivalTime, (int) Math.ceil(lengthOfCruise / LENGTH_PER_LOADER), 
                (int) Math.ceil(numOfPassengers / PASSENGERS_PER_MIN));
    }
}
