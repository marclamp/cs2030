class Leave extends Event {
    private static final double PRIORITY_NO = 2.0;

    public Leave(Customer customer) {
        super(customer, PRIORITY_NO);
    }

    public Pair<Server, Event> execute(Server server) {
        return new Pair<Server, Event>(server, this);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) +  " " 
            + this.customer.toString() + " leaves";
    }
}
