class Serve extends Event {
    private static final double PRIORITY_NO = 4.0;

    public Serve(Customer customer, Server server) {
        super(customer, server, PRIORITY_NO);
    }

    public Pair<Server, Event> execute() {
        Server newServer = this.server.updateServer(this.customer);
        return new Pair<Server, Event>(newServer, new Done(this.customer, newServer));
    }

    @Override
    public String toString() {
        return this.eventTime + " " + this.customer.toString() + " served by " + this.server.toString();
    }
}
