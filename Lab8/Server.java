import java.util.function.Supplier;

class Server implements Comparable<Server> {
    protected final int name;
    protected final double nextAvailableTime;
    protected final int numInQueue;
    protected final int qmax;
    protected final boolean servable;
    protected final boolean resting;
    private final Supplier<Double> restTimes;

    protected Server(int name, double nextAvailableTime) {
        this.name = name;
        this.nextAvailableTime = nextAvailableTime;
        this.qmax = 0;
        this.numInQueue = 0;
        this.servable = true;
        this.resting = false;
        this.restTimes = () -> 0.0;
    }

    protected Server(int name, double nextAvailableTime, boolean servable) {
        this.name = name;
        this.nextAvailableTime = nextAvailableTime;
        this.qmax = 0;
        this.numInQueue = 0;
        this.servable = servable;
        this.resting = false;
        this.restTimes = () -> 0.0;
    }

    protected Server(int name, double nextAvailableTime, int qmax, 
        int numInQueue, boolean servable) {
        this.name = name;
        this.nextAvailableTime = nextAvailableTime;
        this.qmax = qmax;
        this.numInQueue = numInQueue;
        this.servable = servable;
        this.resting = false;
        this.restTimes = () -> 0.0;
    }

    public Server(int name, double nextAvailableTime, int qmax, Supplier<Double> restTimes) {
        this.name = name;
        this.nextAvailableTime = nextAvailableTime;
        this.qmax = qmax;
        this.numInQueue = 0;
        this.servable = true;
        this.resting = false;
        this.restTimes = restTimes;
    }

    public Server(int name, double nextAvailableTime, int qmax, 
        int numInQueue, boolean servable, boolean resting, Supplier<Double> restTimes) {
        this.name = name;
        this.nextAvailableTime = nextAvailableTime;
        this.qmax = qmax;
        this.numInQueue = numInQueue;
        this.servable = servable;
        this.resting = resting;
        this.restTimes = restTimes;
    }


    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    // public double calcWaitingTime(Customer c) {
    //     return this.nextAvailableTime - c.getArrivalTime();
    // }

    public double calcServeTime(Customer c, double eventTime) {
        return eventTime + c.getServiceTime();
    }

    // public String getName() {
    //     return this.name;
    // }

    public int getServerID() {
        return name - 1;
    }

    public boolean servable() {
        return this.servable;
    }

    public boolean waitable() {
        return this.numInQueue < this.qmax;
    }

    public boolean resting() {
        return this.resting;
    }
    
    public boolean hasQueue() {
        return this.numInQueue > 1;
    }

    public double getRestTime() {
        return this.restTimes.get();
    }

    public Server serverFree() {
        return new Server(this.name, this.nextAvailableTime, 
                this.qmax, this.numInQueue, true, this.resting, this.restTimes);
    }

    public Server serverNotFree() {
        return new Server(this.name, this.nextAvailableTime, 
                this.qmax, this.numInQueue, false, this.resting, this.restTimes);
    }

    public Server updateIfFree(double time, Customer c) {
        if (this.numInQueue == 0 && !this.resting) {
            return this.serverFree();
        } else {
            return this;
        }
    }

    public Server addRestTime(double restTime) {
        double nextTime = this.nextAvailableTime + restTime;
        return new Server(name, nextTime, qmax, numInQueue, servable, resting, restTimes);
    }

    public Server setRest(boolean flag) {
        return new Server(name, nextAvailableTime, qmax, numInQueue, servable, flag, restTimes);
    }

    public Server checkAndUpdateRest(double time) {
        boolean flag = false;
        if (this.resting) {
            flag = this.nextAvailableTime >= time;
        }
        return new Server(name, nextAvailableTime, qmax, numInQueue, servable, flag, restTimes);
    }

    public Server checkAndUpdateQueue() {
        if (this.resting) {
            return new Server(name, nextAvailableTime, qmax, 
                numInQueue - 1, servable, this.resting, restTimes);
        } else {
            return this;
        }
    }

    public Server updateServer(Customer c) {
        if (c.getArrivalTime() < this.nextAvailableTime) {
            double newTiming =  this.nextAvailableTime + c.getServiceTime();
            return new Server(this.name, newTiming, 
                this.qmax, this.numInQueue, this.servable, this.resting, this.restTimes);
        } else {
            double newTiming =  c.getArrivalTime() + c.getServiceTime();
            return new Server(this.name, newTiming, 
                this.qmax, this.numInQueue, this.servable, this.resting, this.restTimes);
        }
    }

    public Server incrementInQueue() {
        return new Server(this.name, this.nextAvailableTime, 
                this.qmax, this.numInQueue + 1, this.servable, this.resting, this.restTimes);
    }

    public Server decrementInQueue() {
        if (!this.resting) {
            int newNumInQueue = Math.max(0, this.numInQueue - 1);
            return new Server(this.name, this.nextAvailableTime, 
                this.qmax, newNumInQueue, this.servable, this.resting, this.restTimes);
        } else {
            return this;
        }
    }

    public int compareTo(Server server) {
        if (this.servable && server.servable && !this.resting && !server.resting) { 
            return this.name - server.name;
        } else if (this.servable && !this.resting) {
            return -1;
        } else if (server.servable && !server.resting) {
            return 1;
        } else if (this.waitable() && server.waitable()) {
            return this.name - server.name;
        } else if (this.waitable() && !this.resting) {
            return -1;
        } else if (server.waitable() && !server.resting) {
            return 1;
        } else if (this.waitable()) {
            return -1;
        } else if (server.waitable()) {
            return 1;
        } else if (this.nextAvailableTime == server.nextAvailableTime) {
            return this.name - server.name;
        } else {
            return Double.compare(this.nextAvailableTime, server.nextAvailableTime);
        }
    }

    public boolean isSameServer(Server server) {
        return this.name == server.name;
    }

    @Override
    public String toString() {
        return String.valueOf(this.name);
        //  + " " + this.nextAvailableTime + " " + this.numInQueue 
        //  + " " + this.servable + " " + this.waitable() + " " + this.resting;
    }
}
