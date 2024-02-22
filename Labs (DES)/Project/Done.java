class Done extends Event {
    private final Server server;
    private static final double PRIORITY_NO = 3.0;

    public Done(Customer customer, Server server, Statistics stats, int serverID, boolean inQueue) {
        super(customer, PRIORITY_NO, stats, serverID, inQueue);
        this.server = server;
    }

    public Done(Customer customer, Server server, double eventTime, 
            Statistics stats, int serverID, boolean inQueue) {
        super(customer, PRIORITY_NO, eventTime, stats, serverID, inQueue);
        this.server = server;
    }

    public Pair<Server, Event> execute(Server server) {
        server = server.updateIfFree();
        return new Pair<Server, Event>(server, this);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) + " " 
            + this.customer.toString() + " done serving by " + this.server.toString();
    }
}
