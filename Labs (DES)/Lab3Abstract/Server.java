class Server {
    private final String name;
    private final double nextAvailableTime;
    private static final double EPSILON = 1e-15;

    public Server(String name, double nextAvailableTime) {
        this.name = name;
        this.nextAvailableTime = nextAvailableTime;
    }

    boolean servable(Customer c) {
        return (c.getArrivalTime() > this.nextAvailableTime || 
                Math.abs(this.nextAvailableTime - c.getArrivalTime()) < EPSILON);
    }

    public Server updateServer(Customer c) {
        if (servable(c)) {
            double newTiming = c.getArrivalTime() + c.getServiceTime();
            return new Server(this.name, newTiming);
        } else {
            return this;
        }

    }
    
    /* @Override 
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Server server) {
            return (server.name == this.name);
        } else {
            return false;
        }
    } */

    @Override
    public String toString() {
        return this.name + " " + this.nextAvailableTime;
    }
}
