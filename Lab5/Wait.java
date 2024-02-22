class Wait extends Event {
    private final Server server;
    private static final double PRIORITY_NO = 2.1;

    public Wait(Customer customer, Server server, Statistics stats, int serverID, boolean inQueue) {
        super(customer, PRIORITY_NO, stats, serverID, inQueue);
        this.server = server;
    }

    public Pair<Server, Event> execute(Server server) {
        // if (server.hasQueue()) {
        return new Pair<Server, Event>(server, 
            new WaitAfter(this.customer, server, this.stats, 
                this.serverID, this.inQueue));
        // } else {
        //     double nextAvailableTime = server.getNextAvailableTime();
        //     Server newServer = server.serverNotFree();
        //     double waitingTime = server.calcWaitingTime(this.customer);
        //     Statistics newStats = this.stats.addWaitingTime(waitingTime);
            
        //     return new Pair<Server, Event>(newServer, 
        //         new Serve(this.customer, server, nextAvailableTime, 
        //             newStats, this.serverID, this.inQueue));
        // }
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) +  " " 
            + this.customer.toString() + " waits at " + this.server.toString();
    }
}
