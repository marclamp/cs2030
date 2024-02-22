class SelfCheckoutOne extends Server {

    SelfCheckoutOne(int name) {
        super(name, 0.0);
    }

    SelfCheckoutOne(int name, double time) {
        super(name, time);
    }

    SelfCheckoutOne(int name, double time, boolean servable) {
        super(name, time, servable);
    }

    public SelfCheckoutOne updateServer(double time) {
        return new SelfCheckoutOne(this.name, time);
    }

    public SelfCheckoutOne serverFree() {
        return new SelfCheckoutOne(this.name, this.nextAvailableTime, true);
    }

    public SelfCheckoutOne serverNotFree() {
        return new SelfCheckoutOne(this.name, this.nextAvailableTime, false);
    }
    
    public int compareTo(SelfCheckoutOne server) {
        if (this.servable && server.servable) { 
            return this.name - server.name;
        } else if (this.servable) {
            return -1;
        } else if (server.servable) {
            return 1;
        } else if (this.nextAvailableTime <= server.nextAvailableTime) {
            return this.name - server.name;
        } else {
            return Double.compare(this.nextAvailableTime, server.nextAvailableTime);
        }
    }

    @Override
    public String toString() {
        return "self-check " + super.toString();
    }
}
