void findBestBooking(Request request, List<? extends Driver> list) {
    ImList<Booking> allBookings = new ImList<Booking>();  
    for(Driver driver : list) {
        allBookings = allBookings.addAll(new Booking(driver, request).getAllBookings());
    }
    allBookings = allBookings.sort(new BookingComparator());
    for (Booking booking : allBookings) {
        System.out.println(booking);
    }
}
