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

    public Pair<Boolean, ? extends Event> updateServeEventTime(double nextEventTime) {
        return new Pair<Boolean, Event>(false, this);
    }

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

    public boolean isServe() {
        return false;
    }

    public boolean isWait() {
        return false;
    }

    public boolean isDone() {
        return false;
    }

    public boolean isLeave() {
        return false;
    }

    public boolean isSameServer(Event event) {
        return Integer.compare(this.serverID, event.serverID) == 0;
    }

    public boolean isSameTime(Event event) {
        return Double.compare(this.eventTime, event.eventTime) == 0;
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

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Event event) {
            return (this.customer.equals(event.customer) &&
                (Double.compare(this.eventTime, event.eventTime) == 0) &&
                (Double.compare(this.priorityNo, event.priorityNo) == 0) &&
                (this.inQueue == event.inQueue) &&
                (this.serverID == event.serverID) && 
                (this.isServe() == event.isServe()) &&
                (this.isDone() == event.isDone()) &&
                (this.isLeave() == event.isLeave()));
        } else {
            return false;
        }
    }
}
