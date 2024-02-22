class Arrive extends Event {
    private static final double PRIORITY_NO = 2.0;

    public Arrive(Customer customer) {
        super(customer, PRIORITY_NO);
    }

    public Arrive(Customer customer, Statistics stats) {
        super(customer, PRIORITY_NO, stats);
    }

    public Pair<Server, Event> execute(Server server) {
        if (server.servable()) {
            int serverID = Integer.parseInt(server.getName()) - 1;
            boolean inQueue = true;
            Server newServer = 
                server.updateServer(this.customer).serverNotFree();
            return new Pair<Server, Event>(newServer, new Serve(this.customer, newServer, 
                        this.stats.increaseServicedCustomers(), serverID, inQueue));
        } else if (server.waitable()) {
            Server newServer = server.incrementInQueue();
            double waitingTime = server.calcWaitingTime(this.customer);
            Statistics stats = this.stats.addWaitingTime(waitingTime).increaseWaitingCustomers();
            int serverID = Integer.parseInt(server.getName()) - 1;
            boolean inQueue = true;
            return new Pair<Server, Event>(newServer, 
                    new Wait(this.customer, newServer, stats, serverID, inQueue));
        } else {
            return new Pair<Server, Event>(server, 
                    new Leave(this.customer, this.stats.increaseLeftCustomer()));
        }
    }

    @Override 
    public String toString() {
        return String.format("%.3f", this.eventTime) + 
            " " +  this.customer.toString() + " arrives";
    }
}
