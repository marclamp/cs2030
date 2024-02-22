class Statistics {
    private final double waitingTime;
    private final int numWaited;
    private final int numServed;
    private final int numLeft;

    Statistics() {
        this.waitingTime = 0.0;
        this.numWaited = 0;
        this.numServed = 0;
        this.numLeft = 0;
    }

    Statistics(double waitingTime, int numWaited, int numServed, int numLeft) {
        this.waitingTime = waitingTime;
        this.numWaited = numWaited;
        this.numServed = numServed;
        this.numLeft = numLeft;
    }

    Statistics addWaitingTime(double time) {
        double newTime = this.waitingTime + time;
        return new Statistics(newTime, this.numWaited, this.numServed, this.numLeft);
    }

    Statistics increaseWaitingCustomers() {
        return new Statistics(this.waitingTime, this.numWaited + 1, this.numServed, this.numLeft);
    }

    Statistics increaseServicedCustomers() {
        return new Statistics(this.waitingTime, this.numWaited, this.numServed + 1, this.numLeft);
    }

    Statistics increaseLeftCustomer() {
        return new Statistics(this.waitingTime, this.numWaited, this.numServed, this.numLeft + 1);
    }

    Statistics add(Statistics stats) {
        return new Statistics(this.waitingTime + stats.waitingTime,
            this.numWaited + stats.numWaited,
            this.numServed + stats.numServed,
            this.numLeft + stats.numLeft);
    }

    @Override
    public String toString() {
        return "[" + String.format("%.3f", 
            Math.max(0, this.waitingTime / this.numServed)) 
            + " " + this.numServed + " " + this.numLeft + "]";
    }
}
