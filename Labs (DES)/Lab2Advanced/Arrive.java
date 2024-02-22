class Arrive implements Event {
    private final Customer customer;
    private final Server server; 

    public Arrive(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
    }

    public Event execute() {
        if (server.servable(this.customer)) {
            return new Serve(this.customer, this.server);
        } else {
            return new Leave(this.customer, this.server);
        }
    }

    @Override
    public Server getServer() {
        return this.server;
    }

    @Override
    public Customer getCustomer() {
        return this.customer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Arrive) {
            Arrive arrive = (Arrive) obj;
            return (arrive.customer == this.customer &&
                    arrive.server == this.server);
        } else {
            return false;
        }
    }

    @Override 
    public String toString() {
        return this.customer.toString() + " arrives";
    }
}
