class Serve implements Event {
    private final Customer customer;
    private final Server server; 

    public Serve(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
    }

    public Event execute() {
        return this;
    }
    
    /* @Override
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
        } else if (obj instanceof Serve) {
            Serve serve = (Serve) obj;
            return (this.customer.equals(serve.customer) && this.server.equals(serve.server));
        } else {
            return false;
        }
    } */

    @Override
    public String toString() {
        return this.customer.toString() + " served by " + this.server.toString();
    }
}
