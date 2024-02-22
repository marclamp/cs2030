class Leave implements Event {
    private final Customer customer;
    private final Server server; 

    public Leave(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
    }

    public Event execute() {
        return this;
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
        } else if (obj instanceof Leave) {
            Leave leave = (Leave) obj;
            return (leave.customer == this.customer &&
                    leave.server == this.server);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.customer.toString() + " leaves";
    }
}
