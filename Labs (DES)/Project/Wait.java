class Wait extends Event {
    private final Server server;
    private static final double PRIORITY_NO = 2.1;

    public Wait(Customer customer, Server server, Statistics stats, int serverID, boolean inQueue) {
        super(customer, PRIORITY_NO, stats, serverID, inQueue);
        this.server = server;
    }

    public Pair<Server, Event> execute(Server server) {
        Server newServer = server.updateServer(this.customer).serverNotFree();
        return new Pair<Server, Event>(newServer, new Serve(this.customer, newServer, 
            server.getNextAvailableTime(), this.stats.increaseServicedCustomers(), 
            this.serverID, this.inQueue));
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) +  " " 
            + this.customer.toString() + " waits at " + this.server.toString();
    }
}
