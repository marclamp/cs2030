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

    public Pair<Server, Event> execute(Server server) {
        Server newServer = server.updateServer(this.customer);
        double nextEventTime = server.calcServeTime(this.customer, this.eventTime);
        Statistics newStats = this.stats.increaseServicedCustomers();
        
        return new Pair<Server, Event>(newServer, 
            new Done(this.customer, server, nextEventTime, 
                newStats, this.serverID, this.inQueue));
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) +  " " 
            + this.customer.toString() + " serves by " + this.server.toString();
    }
}
