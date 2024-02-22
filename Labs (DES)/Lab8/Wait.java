class Wait extends Event {
    private static final double PRIORITY_NO = 2.1;

    public Wait(Customer customer, Server server, Statistics stats, int serverID, boolean inQueue) {
        super(customer, PRIORITY_NO, stats, serverID, inQueue);
    }

    public boolean isWait() {
        return true;
    }

    public Pair<Server, Event> execute(Server server) {
        double nextAvailableTime = server.getNextAvailableTime();
        Server newServer = server.serverNotFree();
        // double waitingTime = server.calcWaitingTime(this.customer);
        // Statistics newStats = this.stats.addWaitingTime(waitingTime);

        return new Pair<Server, Event>(newServer, 
                new Serve(this.customer, server, nextAvailableTime, 
                    stats, this.serverID, this.inQueue));
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) +  " " 
            + this.customer.toString() + " waits at ";
    }
}
