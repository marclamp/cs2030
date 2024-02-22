class Arrive extends Event {
    private static final double PRIORITY_NO = 2.0;

    public Arrive(Customer customer, Server server) {
        super(customer, server, PRIORITY_NO);
    }

    public Pair<Server, Event> execute() {
        if (server.servable(this.customer)) {
            return new Pair<Server, Event>(this.server, new Serve(this.customer, this.server));
        } else {
            return new Pair<Server, Event>(this.server, new Leave(this.customer, this.server));
        }
    }

    @Override 
    public String toString() {
        return this.eventTime + " " +  this.customer.toString() + " arrives";
    }
}
