abstract class Event implements Comparable<Event> {
    protected final Customer customer;
    protected final Server server;
    protected final double priorityNo;
    protected final double eventTime;
    private static final double EPSILON = 1e-15;

    public Event(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
        this.priorityNo = 0.0;
        this.eventTime = customer.getArrivalTime();
    }

    public Event(Customer customer, Server server, double priorityNo) {
        this.customer = customer;
        this.server = server;
        this.priorityNo = priorityNo;
        this.eventTime = customer.getArrivalTime();
    }

    public Event(Customer customer, Server server, double priorityNo, double eventTime) {
        this.customer = customer;
        this.server = server;
        this.priorityNo = priorityNo;
        this.eventTime = eventTime;
    }

    public abstract Pair<Server, Event> execute();

    public int compareTo(Event event) {
        if (this.eventTime == event.eventTime) {
            return Double.compare(this.priorityNo, event.priorityNo);
        } else {
            return Double.compare(this.eventTime, event.eventTime);
        }
    }
}
