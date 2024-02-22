class SmallCruise extends Cruise {
    private static final int NUM_OF_LOADERS = 1;
    private static final int SERVICE_TIME = 30;

    SmallCruise(String identifier, int arrivalTime) {
        super(identifier, arrivalTime, NUM_OF_LOADERS, SERVICE_TIME);
    }
}
