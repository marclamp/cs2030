class Server implements Comparable<Server> {
    private final String name;
    private final double nextAvailableTime;
    private final int numInQueue;
    private final int qmax;
    private final boolean servable;
    //private final PQ<Customer> custQueuePQ; 

    public Server(String name, double nextAvailableTime, int qmax) {
        this.name = name;
        this.nextAvailableTime = nextAvailableTime;
        this.qmax = qmax;
        this.numInQueue = 0;
        this.servable = true;
        //this.custQueuePQ = new PQ<>(new CustComp());
    }

    public Server(String name, double nextAvailableTime, 
            int qmax, int numInQueue, boolean servable) {
        this.name = name;
        this.nextAvailableTime = nextAvailableTime;
        this.qmax = qmax;
        this.numInQueue = numInQueue;
        this.servable = servable;
        //this.custQueuePQ = new PQ<>(new CustComp());
    }


    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    public double calcWaitingTime(Customer c) {
        return this.nextAvailableTime - c.getArrivalTime();
    }

    public double calcServeTime(Customer c, double eventTime) {
        return eventTime + c.getServiceTime();
    }

    // public String getName() {
    //     return this.name;
    // }

    public int getServerID() {
        return Integer.parseInt(name) - 1;
    }

    public boolean servable() {
        return this.servable;
    }

    public boolean waitable() {
        return this.numInQueue < this.qmax;
    }
    
    public boolean hasQueue() {
        return this.numInQueue > 1;
    }

    public Server serverFree() {
        return new Server(this.name, this.nextAvailableTime, 
                this.qmax, this.numInQueue, true);
    }

    public Server serverNotFree() {
        return new Server(this.name, this.nextAvailableTime, 
                this.qmax, this.numInQueue, false);
    }

    public Server updateIfFree() {
        if (this.numInQueue == 0) {
            return this.serverFree();
        } else {
            return this;
        }
    }

    public Server updateServer(Customer c) {
        if (c.getArrivalTime() < this.nextAvailableTime) {
            double newTiming =  this.nextAvailableTime + c.getServiceTime();
            return new Server(this.name, newTiming, 
                this.qmax, this.numInQueue, this.servable);
        } else {
            double newTiming =  c.getArrivalTime() + c.getServiceTime();
            return new Server(this.name, newTiming, 
                this.qmax, this.numInQueue, this.servable);
        }
    }

    public Server incrementInQueue() {
        return new Server(this.name, this.nextAvailableTime, 
                this.qmax, this.numInQueue + 1, this.servable);
    }

    public Server decrementInQueue() {
        int newNumInQueue = Math.max(0, this.numInQueue - 1);
        return new Server(this.name, this.nextAvailableTime, 
                this.qmax, newNumInQueue, this.servable);

    }

    public int compareTo(Server server) {
        if (this.servable && server.servable) { 
            return this.name.compareTo(server.name);
        } else if (this.servable) {
            return -1;
        } else if (server.servable) {
            return 1;
        } else if (this.waitable() && server.waitable()) {
            return this.name.compareTo(server.name);
        } else if (this.waitable()) {
            return -1;
        } else if (server.waitable()) {
            return 1;
        } else if (this.nextAvailableTime == server.nextAvailableTime) {
            return this.name.compareTo(server.name);
        } else {
            return Double.compare(this.nextAvailableTime, server.nextAvailableTime);
        }
        
        /*

        if (this.nextAvailableTime != server.nextAvailableTime) {
            return Double.compare(this.nextAvailableTime, server.nextAvailableTime);
        } else {


        if (this.nextAvailableTime == server.nextAvailableTime) {
            //free 
            if (this.servable && server.servable) { 
                return this.name.compareTo(server.name);
            } else if (this.servable) {
                return -1;
            } else if (server.servable) {
                return 1;
            } else if (this.waitable() && server.waitable()) { 
                return this.name.compareTo(server.name);
            } else if (this.waitable()) {
                return -1;
            } else if (server.waitable()) {
                return 1;
        } else if (this.nextAvailableTime == server.nextAvailableTime) {
            return this.name.compareTo(server.name);      
        } else {
            if (this.numInQueue == 0 || server.numInQueue == 0) {
                return this.numInQueue - server.numInQueue;
            } else if (this.nextAvailableTime < server.nextAvailableTime) {
                return -1; 
            } else if (this.nextAvailableTime > server.nextAvailableTime) {
                return 1;
            } else {
                return 0;
            }
        }*/
    }

    public boolean isSameServer(Server server) {
        return this.name.equals(server.name);
    }

    @Override
    public String toString() {
        return this.name;
        // + " " + this.nextAvailableTime + " " + this.numInQueue 
        // + " " + this.servable + " " + this.waitable();
    }
}
