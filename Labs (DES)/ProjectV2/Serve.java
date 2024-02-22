class Serve extends Event {
    private final Server server;
    private static final double PRIORITY_NO = 3.0;

    public Serve(Customer customer, Server server) {
        super(customer, PRIORITY_NO);
        this.server = server;
    }

    public Serve(Customer customer, Server server, double eventTime) {
        super(customer, PRIORITY_NO, eventTime);
        this.server = server;
    }

    public Pair<Server, Event> execute(Server server) {
        Server newServer = server; //.decrementInQueue();
        return new Pair<Server, Event>(newServer, new Done(this.customer, 
        newServer,this.eventTime + customer.getServiceTime()));
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) +  " " 
            + this.customer.toString() + " serves by " + this.server.toString();
    }
}
