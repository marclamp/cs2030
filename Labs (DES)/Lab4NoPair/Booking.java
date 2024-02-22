class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;
    private final Service serviceChosen;
    private final boolean serviceSpecified;

    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
        this.serviceChosen = driver.getServices().get(0);
        this.serviceSpecified = false;
    }

    Booking(Driver driver, Request request, Service service) {
        this.driver = driver;
        this.request = request;
        this.serviceChosen = service;
        this.serviceSpecified = true;
    }

    private int getPrice(Service service) {
        return this.request.computeFare(service);
    }

    private String convertPriceToDollars(int price) {
        double priceInDollars = (double) price / 100.0;
        return "$" + String.format("%.2f", priceInDollars);
    }

    public Service getBestService() {
        Service serviceSelected = this.driver.getServices().get(0);
        int priceSelected = this.getPrice(serviceSelected);
        for (Service service : this.driver.getServices()) {
            int price = this.getPrice(service);
            if (price < priceSelected) {
                priceSelected = price;
                serviceSelected = service;
            }
        }     
        return serviceSelected;
    }

    public int getBestPrice() {
        return this.getPrice(this.getBestService());
    }

    public ImList<Booking> getAllBookings() {
        ImList<Booking> list = new ImList<Booking>();
        for (Service service : this.driver.getServices()) {
            list = list.add(new Booking(this.driver, this.request, service));
        }
        return list;
    }

    @Override
    public int compareTo(Booking booking) {
        if (serviceSpecified) {
            if (this.getPrice(this.serviceChosen) == booking.getPrice(booking.serviceChosen)) {
                return this.driver.compareTo(booking.driver);
            } else {
                return this.getPrice(this.serviceChosen) - booking.getPrice(booking.serviceChosen);
            } 
        } else {
            if (this.getBestPrice() == booking.getBestPrice()) {
                return this.driver.compareTo(booking.driver);
            } else {
                return this.getBestPrice() - booking.getBestPrice();
            }
        }
    }

    @Override
    public String toString() {
        if (serviceSpecified) {
            int price = this.getPrice(serviceChosen);
            return this.convertPriceToDollars(price) + " using " + 
                this.driver.toString() + " (" + this.serviceChosen.toString() + ")";
        } else {
            Service bestService = this.getBestService();
            int bestPrice = this.getBestPrice();
            return this.convertPriceToDollars(bestPrice) + " using " + 
                this.driver.toString() + " (" + bestService.toString() + ")";
        }
    }
}
