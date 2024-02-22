class Simulator {
    private final double numOfServers;
    private final int qmax;
    private final ImList<Pair<Double,Double>> inputTimes;
    
    Simulator(double numOfServers, int qmax, ImList<Pair<Double,Double>> inputTimes) {
        this.numOfServers = numOfServers;
        this.qmax = qmax;
        this.inputTimes = inputTimes;
    }

    public String simulate() {
        
        // create all Servers with their qmax
        // create ImList<Servers>
        // using ImList<Pair> of inputTimes, 
        // create ImList<Customers>
        // create ImList<Events> (all Arrive)
        // iterate through the ImList of Events and .execute()
        // .execute needs to take in the ImList<Servers> and updates
        // wrap loop in a flag (completed) where it checks for all to Done or Leave
        
        String allEventOutputs = "";

        ImList<Server> allServers = new ImList<Server>();
        ImList<Customer> allCustomers = new ImList<Customer>();
        PQ<Event> eventsPQ  = new PQ<Event>(new EventComp());
        Pair<Event, PQ<Event>> eventsPair = eventsPQ.poll();
        ImList<Event> allEventList = new ImList<Event>();
        
        for (int i = 1; i <= this.numOfServers; i++) {
            allServers = allServers.add(new Server(String.valueOf(i), 0.0, this.qmax));
        }

        for (int i = 0; i < inputTimes.size(); i++) {
            double arrivalTime = inputTimes.get(i).first();
            double serviceTime = inputTimes.get(i).second();
            Customer customer = new Customer(i + 1, arrivalTime, serviceTime);
            allCustomers = allCustomers.add(customer);
            Event event = new Arrive(customer);
            eventsPQ = eventsPQ.add(event);
        }

        while (!eventsPQ.isEmpty()) { // loop times (not final)
            System.out.println(allServers);

            // Gets Server from sorted ImList<Server> and remove
            allServers = allServers.sort(new ServerComp());
            Server server = allServers.get(0);
            allServers = allServers.remove(0);

            // Retrieve event from queue, adds output to String, remove it from queue (from poll)
            eventsPair = eventsPQ.poll();
            Event event = eventsPair.first();
            eventsPQ = eventsPair.second();

            allEventList = allEventList.add(event);
            allEventOutputs += event.toString() + "\n";
            System.out.println(event);
            while (event != event.execute(server).second()) {
                Pair<Server, Event> serverAndEventPair = event.execute(server);
                server = serverAndEventPair.first();
                event = serverAndEventPair.second();
                allEventList = allEventList.add(event);
                allEventOutputs += event.toString() + "\n";
                System.out.println(event);
            }
            allServers = allServers.add(server);

            /*
            // Executes the event and append the output to String, add new event to queue
            // Add updated server back to ImList<Server>
            Pair<Server, Event> serverAndEventPair = event.execute(server);
            server = serverAndEventPair.first();
            allServers = allServers.add(server);
            Event nextEvent = serverAndEventPair.second();

            // Check if end of Event chain (Done and Leave for now)
            if (event != nextEvent) {
                eventsPQ = eventsPQ.add(nextEvent);
            } */
        } 
        return allEventOutputs;
    }
}
