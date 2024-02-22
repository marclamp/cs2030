class Server implements Comparable<Server> {
    private final String name;
    private final double nextAvailableTime;
    private final int numInQueue;
    private final int qmax;

    public Server(String name, double nextAvailableTime, int qmax) {
        this.name = name;
        this.nextAvailableTime = nextAvailableTime;
        this.qmax = qmax;
        this.numInQueue = 0;
    }

    public Server(String name, double nextAvailableTime, int qmax, int numInQueue) {
        this.name = name;
        this.nextAvailableTime = nextAvailableTime;
        this.qmax = qmax;
        this.numInQueue = numInQueue;
    }

    // temporary
    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    boolean servable(Customer c) {
        return c.servable(this.nextAvailableTime);
    }

    boolean waitable() {
        return this.numInQueue <= this.qmax;
    }

    public Server updateServer(Customer c) {
        if (c.getArrivalTime() < this.nextAvailableTime) {
            double newTiming = this.nextAvailableTime + c.getServiceTime();
            return new Server(this.name, newTiming, this.qmax, this.numInQueue);
        } else {
            double newTiming = c.getArrivalTime() + c.getServiceTime();
            return new Server(this.name, newTiming, this.qmax, this.numInQueue);
        }
    }

    public Server incrementInQueue() {
        return new Server(this.name, this.nextAvailableTime, this.qmax, this.numInQueue + 1);
    }

    public Server decrementInQueue() {
        int newNumInQueue = Math.max(0, numInQueue - 1);
        return new Server(this.name, this.nextAvailableTime, this.qmax, newNumInQueue);
    
    }

    public int compareTo(Server server) {
        if (this.nextAvailableTime == server.nextAvailableTime) {
            if (this.numInQueue == server.numInQueue) {
                return this.name.compareTo(server.name);
            } else {
                return this.numInQueue - server.numInQueue;
            }
        } else {
            if (this.nextAvailableTime < server.nextAvailableTime) {
                return -1; 
            } else if (this.nextAvailableTime > server.nextAvailableTime) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        return this.name + " " + this.nextAvailableTime + " " + this.numInQueue;
    }
}
