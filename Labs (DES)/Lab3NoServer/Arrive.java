class Arrive extends Event {
    private static final double PRIORITY_NO = 3.0;

    public Arrive(Customer customer, Server server) {
        super(customer, server, PRIORITY_NO);
    }

    public Pair<Server, Event> execute(Server server) {
        if (server.servable(this.customer)) {
            return new Pair<Server, Event>(server, new Serve(this.customer, server));
        } else {
            return new Pair<Server, Event>(server, new Leave(this.customer, server));
        }
    }

    @Override 
    public String toString() {
        return this.eventTime + " " +  this.customer.toString() + " arrives";
    }
}
