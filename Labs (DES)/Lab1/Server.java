class Server {
    private final String name;
    private final double nextAvailableTime;
    private static final double EPSILON = 1e-15;

    Server(String name, double nextAvailableTime) {
        this.name = name;
        this.nextAvailableTime = nextAvailableTime;
    }

    boolean servable(Customer c) {
        if (c.getArrivalTime() > this.nextAvailableTime || 
                Math.abs(this.nextAvailableTime - c.getArrivalTime()) < EPSILON) {
            return true;
        }
        else {
            return false;
        }
    }

    String serve(Customer c) {
        if (servable(c)) {
            return c.toString() + " served by " + this.name;
        }
        else {
            return c.toString() + " left";
        }
    }

    Server updateServer(Customer c) {
        if (servable(c)) {
            double newTiming = c.getArrivalTime() + c.getServiceTime();
            return new Server(this.name, newTiming);
        }
        else {
            return this;
        }
    }
}
