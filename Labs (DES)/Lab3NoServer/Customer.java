class Customer implements Comparable<Customer> {
    private final int customerNumber;
    private final double arrivalTime;
    private final double serviceTime;

    public Customer(int customerNumber, double arrivalTime, double serviceTime) {
        this.customerNumber = customerNumber;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;        
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getServiceTime() {
        return this.serviceTime;
    }

    public int compareTo(Customer customer) {
        return this.customerNumber - customer.customerNumber;
    }

    @Override
    public String toString() {
        return "customer " + customerNumber;
    }
}
