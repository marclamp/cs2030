class Arrive extends Event {
    private static final double PRIORITY_NO = 5.0;

    public Arrive(Customer customer) {
        super(customer, PRIORITY_NO);
    }

    public Pair<Server, Event> execute(Server server) {
        if (server.servable(this.customer)) {
            Server newServer = server.updateServer(this.customer).incrementInQueue();
            return new Pair<Server, Event>(newServer, new Serve(this.customer, newServer));
        } else if (server.waitable()) {
            Server newServer = server.incrementInQueue();
            return new Pair<Server, Event>(newServer, 
                new Wait(this.customer, newServer));
        } else {
            return new Pair<Server, Event>(server, new Leave(this.customer));
        }
    }

    @Override 
    public String toString() {
        return String.format("%.3f", this.eventTime) + 
            " " +  this.customer.toString() + " arrives";
    }
}
