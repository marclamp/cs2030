class Leave extends Event {
    private static final double PRIORITY_NO = 2.0;

    public Leave(Customer customer, Server server) {
        super(customer, server, PRIORITY_NO);
    }

    public Pair<Server, Event> execute(Server server) {
        return new Pair<Server, Event>(server, this);
    }

    @Override
    public String toString() {
        return this.eventTime + " " + this.customer.toString() + " leaves";
    }
}
