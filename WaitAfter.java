class WaitAfter extends Event {
    private final Server server;
    private static final double PRIORITY_NO = 10;

    public WaitAfter(Customer customer, Server server, 
            Statistics stats, int serverID, boolean inQueue) {
        super(customer, PRIORITY_NO, stats, serverID, inQueue);
        this.server = server;
    }

    @Override
    public boolean isWaitAfter() {
        return true;
    }

    public Pair<Server, Event> execute(Server server) {
        //System.out.println("WaitAfter executed.");
        double nextAvailableTime = server.getNextAvailableTime();
        Server newServer = server.serverNotFree();
        double waitingTime = server.calcWaitingTime(this.customer);
        Statistics newStats = this.stats.addWaitingTime(waitingTime);
        
        return new Pair<Server, Event>(newServer, 
            new Serve(this.customer, server, nextAvailableTime, 
                newStats, this.serverID, this.inQueue));
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) +  " " 
            + this.customer.toString() + " waits at " + this.server.toString();
    }
}
