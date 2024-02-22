abstract class Event implements Comparable<Event> {
    protected final Customer customer;
    protected final double eventTime;
    protected final double priorityNo;

    public Event(Customer customer) {
        this.customer = customer;
        this.priorityNo = 0.0;
        this.eventTime = customer.getArrivalTime();
    }

    public Event(Customer customer, double priorityNo) {
        this.customer = customer;
        this.priorityNo = priorityNo;
        this.eventTime = customer.getArrivalTime();
    }

    public Event(Customer customer, double priorityNo, double eventTime) {
        this.customer = customer;
        this.priorityNo = priorityNo;
        this.eventTime = eventTime;
    }

    public abstract Pair<Server, Event> execute(Server server);

    public int compareTo(Event event) {
        if (this.eventTime == event.eventTime) {
            if (this.priorityNo == event.priorityNo) {
                return this.customer.compareTo(event.customer);
            } else {
                return Double.compare(this.priorityNo, event.priorityNo);
            }
        } else {
            return Double.compare(this.eventTime, event.eventTime);
        }
    }
}
