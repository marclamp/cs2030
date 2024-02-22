class Customer {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Customer) {
            Customer customer = (Customer) obj;
            return (customer.customerNumber == this.customerNumber &&
                    customer.arrivalTime == this.arrivalTime &&
                    customer.serviceTime == this.serviceTime);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "customer " + customerNumber;
    }
}
