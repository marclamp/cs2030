class Serve extends Event {
    private final Server server;
    private static final double PRIORITY_NO = 2.9;

    public Serve(Customer customer, Server server, 
            Statistics stats, int serverID, boolean inQueue) {
        super(customer, PRIORITY_NO, stats, serverID, inQueue);
        this.server = server;
    }

    public Serve(Customer customer, Server server, double eventTime, 
            Statistics stats, int serverID, boolean inQueue) {
        super(customer, PRIORITY_NO, eventTime, stats, serverID, inQueue);
        this.server = server;
    }

    public boolean isServe() {
        return true;
    }

    public Pair<Boolean, Serve> updateServeEventTime(double nextEventTime) {
        return new Pair<Boolean, Serve>(true, new Serve(this.customer, this.server, nextEventTime,
                this.stats, this.serverID, this.inQueue));
    }

    public Pair<Server, Event> execute(Server server) {
        // double restTime = server.getRestTime();
        // System.out.println(restTime);
        Server newServer = server.updateServer(this.customer);
        // .addRestTime(restTime).setRest(restTime != 0.0);
        double nextEventTime = server.calcServeTime(this.customer, this.eventTime);
        double waitingTime = customer.calcWaitingTime(this.customer, this.eventTime);
        Statistics newStats = this.stats.increaseServicedCustomers().addWaitingTime(waitingTime);
        
        return new Pair<Server, Event>(newServer, 
            new Done(this.customer, server, nextEventTime, 
                newStats, this.serverID, this.inQueue, false));
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) +  " " 
            + this.customer.toString() + " serves by " + this.server.toString();
    }
}
