Customer customer = new Customer(1, 1.0, 1.0)
Server server = new Server("Apu", 0.0)
Event event = new Arrive(customer, server)
PQ<Event> eventsPQ = new PQ<Event>(new EventComp())
Pair<Event, PQ<Event>> eventsPair = eventsPQ.poll()
eventsPQ = eventsPQ.add(event).add(new Arrive(new Customer(2, 1.5, 1.0), server))
Pair<Server, Event> serverAndEventPair = new Pair<Server, Event>(server1, event); 
