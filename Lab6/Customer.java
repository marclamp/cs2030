class Customer implements Comparable<Customer> {
    private final int customerNumber;
    private final double arrivalTime;
    private final Lazy<Double> serviceTime;

    public Customer(int customerNumber, double arrivalTime, Lazy<Double> serviceTime) {
        this.customerNumber = customerNumber;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;        
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getServiceTime() {
        double st = this.serviceTime.get();
        //System.out.println(this.customerNumber + ": Service Time retrieved " + st);
        return st;
        //return this.serviceTime.get();
    }

    public double calcWaitingTime(Customer c, double eventTime) {
        return eventTime - this.arrivalTime;
    }

    public int compareTo(Customer customer) {
        return this.customerNumber - customer.customerNumber;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Customer customer) {
            return this.customerNumber == customer.customerNumber;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(customerNumber);
        // + " " + this.arrivalTime;
    }
}
