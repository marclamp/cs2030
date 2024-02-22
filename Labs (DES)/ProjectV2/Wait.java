class Wait extends Event {
    private final Server server;
    private static final double PRIORITY_NO = 4.0;

    public Wait(Customer customer, Server server) {
        super(customer, PRIORITY_NO);
        this.server = server;
    }

    public Pair<Server, Event> execute(Server server) {
        Server newServer = server.updateServer(this.customer);
        return new Pair<Server, Event>(newServer, new Serve(this.customer, newServer, 
            server.getNextAvailableTime()));
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) +  " " 
            + this.customer.toString() + " waits at " + this.server.toString();
    }
}
