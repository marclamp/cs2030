abstract class Event implements Comparable<Event> {
    protected final Customer customer;
    protected final double eventTime;
    protected final double priorityNo;
    protected final Statistics stats;
    protected final boolean inQueue;
    protected final int serverID;

    public Event(Customer customer) {
        this.customer = customer;
        this.priorityNo = 0.0;
        this.eventTime = customer.getArrivalTime();
        this.stats = new Statistics();
        this.serverID = 0;
        this.inQueue = false;
    }

    public Event(Customer customer, double priorityNo) {
        this.customer = customer;
        this.priorityNo = priorityNo;
        this.eventTime = customer.getArrivalTime();
        this.stats = new Statistics();
        this.serverID = 0;
        this.inQueue = false;
    }

    public Event(Customer customer, Statistics stats) {
        this.customer = customer;
        this.priorityNo = 0.0;
        this.eventTime = customer.getArrivalTime();
        this.stats = stats;
        this.serverID = 0;
        this.inQueue = false;
    }

    public Event(Customer customer, double priorityNo, Statistics stats) {
        this.customer = customer;
        this.priorityNo = priorityNo;
        this.eventTime = customer.getArrivalTime();
        this.stats = stats;
        this.serverID = 0;
        this.inQueue = false;
    }

    public Event(Customer customer, double priorityNo, 
            Statistics stats, int serverID, boolean inQueue) {
        this.customer = customer;
        this.priorityNo = priorityNo;
        this.eventTime = customer.getArrivalTime();
        this.stats = stats;
        this.serverID = serverID;
        this.inQueue = inQueue;
    }

    public Event(Customer customer, double priorityNo, double eventTime, 
            Statistics stats, int serverID, boolean inQueue) {
        this.customer = customer;
        this.priorityNo = priorityNo;
        this.eventTime = eventTime;
        this.stats = stats;
        this.serverID = serverID;
        this.inQueue = inQueue;
    }

    public abstract Pair<Server, Event> execute(Server server);

    public Statistics getStatistics() {
        return this.stats;
    }

    public boolean inQueue() {
        return this.inQueue;
    }

    public int getServerID() {
        return this.serverID;
    }

    public boolean isWaitAfter() {
        return false;
    }

    // for testing
    public double getEventTime() {
        return this.eventTime;
    }

    public int compareTo(Event event) {
        if (this.eventTime == event.eventTime) {
            if (this.customer.equals(event.customer)) {
                return Double.compare(this.priorityNo, event.priorityNo);
            } else {
                return this.customer.compareTo(event.customer);
            }
        } else {
            return Double.compare(this.eventTime, event.eventTime);
        }
    }
}
