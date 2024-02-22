class Arrive extends Event {
    private static final double PRIORITY_NO = 2.0;

    public Arrive(Customer customer) {
        super(customer, PRIORITY_NO);
    }

    public Arrive(Customer customer, Statistics stats) {
        super(customer, PRIORITY_NO, stats);
    }

    public Pair<Server, Event> execute(Server server) {
        if (server.servable() && !server.resting()) { // Server
            int serverID = server.getServerID();
            boolean inQueue = true;
            Server newServer = 
                server.serverNotFree();
            
            return new Pair<Server, Event>(newServer, 
                new Serve(this.customer, newServer, this.stats, serverID, inQueue));
        } else if (server.waitable() || (server.waitable() && server.resting())) { // Wait
            Server newServer = server.incrementInQueue();
            Statistics newStats = this.stats.increaseWaitingCustomers();
            int serverID = server.getServerID();
            boolean inQueue = true;

            return new Pair<Server, Event>(newServer, 
                    new Wait(this.customer, newServer, newStats, serverID, inQueue));
        } else { // Leave
            Statistics newStats = this.stats.increaseLeftCustomer();
            return new Pair<Server, Event>(server, 
                    new Leave(this.customer, newStats));
        }
    }

    @Override 
    public String toString() {
        return String.format("%.3f", this.eventTime) + 
            " " +  this.customer.toString() + " arrives";
    }
}
