class Service {
    private final Loader loader;
    private final Cruise cruise;
    private final int timeOfService;
    private static final int CONVERT_TO_MINS = 60;

    Service(Loader loader, Cruise cruise, int timeOfService) {
        this.loader = loader;
        this.cruise = cruise;
        this.timeOfService = timeOfService;
    }

    private int convertMinsToHhmm(int time) {
        return time / CONVERT_TO_MINS * 100 + time % CONVERT_TO_MINS;
    }
    
    @Override
    public String toString() {
        return String.format("%04d", convertMinsToHhmm(this.timeOfService)) + " : " + 
            this.loader.toString() + " serving " + this.cruise.toString();
    }
}
