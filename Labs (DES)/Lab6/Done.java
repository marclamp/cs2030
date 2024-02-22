class Done extends Event {
    private final Server server;
    private static final double PRIORITY_NO = 3.0;
    private final boolean executed;

    public Done(Customer customer, Server server, Statistics stats, int serverID, boolean inQueue) {
        super(customer, PRIORITY_NO, stats, serverID, inQueue);
        this.server = server;
        this.executed = false;
    }

    public Done(Customer customer, Server server, double eventTime, 
            Statistics stats, int serverID, boolean inQueue, boolean executed) {
        super(customer, PRIORITY_NO, eventTime, stats, serverID, inQueue);
        this.server = server;
        this.executed = executed;
    }

    public boolean isDone() {
        return true;
    }

    public Pair<Server, Event> execute(Server server) {
        double restTime = 0.0;
        if (!this.executed) {
            restTime = server.getRestTime();
            //System.out.println(this.toString() + restTime);
        }
        Server newServer = server.updateIfFree().decrementInQueue()
            .addRestTime(restTime).setRest(restTime != 0.0);
        return new Pair<Server, Event>(newServer, 
            new Done(this.customer, newServer, this.eventTime, this.stats, 
                this. serverID, this.inQueue, true));
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) + " " 
            + this.customer.toString() + " done serving by " + this.server.toString();
    }
}
