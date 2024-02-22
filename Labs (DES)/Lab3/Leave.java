class Leave implements Event {
    private final Customer customer;
    private final Server server;
    private final double eventTime;
    private final double priorityNo;

    public Leave(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
        this.eventTime = customer.getArrivalTime();
        this.priorityNo = 4.0;
    }

    public Event execute() {
        return this;
    }

    public int compareTo(Event event) {
        if (this.eventTime == event.eventTime) {
            return this.priorityNo - event.priorityNo;
        } else {
            return this.eventTime - event.eventTime;
        }
    }

    @Override
    public String toString() {
        return this.eventTime + " " + this.customer.toString() + " leaves";
    }
}
