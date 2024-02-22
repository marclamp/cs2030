class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;

    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
    }

    private int getPrice(Service service) {
        return this.request.computeFare(service);
    }

    private String convertPriceToDollars(int price) {
        double priceInDollars = (double) price / 100.0;
        return "$" + String.format("%.2f", priceInDollars);
    }

    public ImList<Pair<Service, Integer>> getAllListings() {
        ImList<Pair<Service, Integer>> list = new ImList<Pair<Service, Integer>>(); 
        for (Service service : this.driver.getServices()) {
            int price = this.getPrice(service);
            Pair<Service, Integer> pair = new Pair<Service, Integer>(service, price);
            list = list.add(pair);
        }
        return list;
    }

    public Pair<Service, Integer> getBestServiceAndPrice() {
        Service serviceSelected = this.driver.getServices().get(0);
        int priceSelected = this.getPrice(serviceSelected);
        for (Service service : this.driver.getServices()) {
            int price = this.getPrice(service);
            if (price < priceSelected) {
                priceSelected = price;
                serviceSelected = service;
            }
        }     
        return new Pair<Service, Integer>(serviceSelected, priceSelected);
    }

    @Override
    public int compareTo(Booking booking) {
        if (this.getBestServiceAndPrice().second() < booking.getBestServiceAndPrice().second()) {
            return this.driver.compareTo(booking.driver);
        }
        return this.getBestServiceAndPrice().second() -
            booking.getBestServiceAndPrice().second();
    }

    @Override
    public String toString() {
        Pair<Service, Integer> bestServiceAndPrice = this.getBestServiceAndPrice();
        Service bestService = bestServiceAndPrice.first();
        int bestPrice = bestServiceAndPrice.second();
        return this.convertPriceToDollars(bestPrice) + " using " + 
            this.driver.toString() + " (" + bestService.toString() + ")";
    }
}
