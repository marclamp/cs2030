/open openall.jsh

Customer c2 = new Customer(2, 0.6, 1.0)

ImList<Server> list = new ImList<Server>(
    List.of(new Server("1", 2.5, 0), new Server("2", 1.6, 0)))
ServerHandler serverHandler = new ServerHandler(list);

ImList<Customer> allCustomers = new ImList<Customer>(List.of(c2))

PQ<Event> generateArriveEventsList(ImList<Customer> listOfCustomers) {
    PQ<Event> eventsPQ  = new PQ<Event>(new EventComp());
    for (Customer customer : listOfCustomers) {
        Event event = new Arrive(customer);
        eventsPQ = eventsPQ.add(event);
    }
    return eventsPQ;
}

PQ<Event> eventsPQ = generateArriveEventsList(allCustomers);
Pair<Event, PQ<Event>> eventsPair = eventsPQ.poll();

Event event = eventsPair.first();
eventsPQ = eventsPair.second();