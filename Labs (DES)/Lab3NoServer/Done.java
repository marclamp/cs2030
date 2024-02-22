class Done extends Event {
    private static final double PRIORITY_NO = 1.0;

    public Done(Customer customer, Server server) {
        super(customer, server, PRIORITY_NO, customer.getArrivalTime() + customer.getServiceTime());
    }

    public Pair<Server, Event> execute(Server server) {
        return new Pair<Server, Event>(server, this);
    }

    @Override
    public String toString() {
        return this.eventTime + " " + this.customer.toString() + 
                " done serving by " + this.server.toString();
    }
}
