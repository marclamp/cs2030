class Done extends Event {
    private final Server server;
    private static final double PRIORITY_NO = 1.0;

    public Done(Customer customer, Server server) {
        super(customer, PRIORITY_NO);
        this.server = server;
    }

    public Done(Customer customer, Server server, double eventTime) {
        super(customer, PRIORITY_NO, eventTime);
        this.server = server;
    }

    public Pair<Server, Event> execute(Server server) {
        Server newServer = server.decrementInQueue();
        return new Pair<Server, Event>(newServer, this);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) + " " 
            + this.customer.toString() + " done serving by " + this.server.toString();
    }
}
