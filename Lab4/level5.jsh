/open ImList.java
/open Pair.java
/open Service.java
/open JustRide.java
/open ShareARide.java
/open TakeACab.java
/open Request.java
/open Driver.java
/open NormalCab.java
/open PrivateCar.java 
/open Booking.java

void findBestBooking(Request request, List<? extends Driver> list) {
    ImList<Pair<Service, Integer>> listings = new ImList<Pair<Service, Integer>>();   
    for(Driver driver : list) {
        listings = listings.addAll(new Booking(driver, request).getAllListings());
    }
    listings = listings.sort(new ListingComparator());
    for () {
        System.out.println(
    }
}
