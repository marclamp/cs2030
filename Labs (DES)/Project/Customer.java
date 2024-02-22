class Customer implements Comparable<Customer> {
    private final int customerNumber;
    private final double arrivalTime;
    private final double serviceTime;
    private static final double EPSILON = 1e-15;

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

    public double getTimeTaken() {
        return this.arrivalTime + this.serviceTime;
    }

    public boolean servable(double servingTime) {
        return (this.arrivalTime > servingTime || 
                Math.abs(servingTime - this.arrivalTime) < EPSILON);
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
    }
}
