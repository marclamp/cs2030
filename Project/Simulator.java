class Simulator {
    private final double numOfServers;
    private final int qmax;
    private final ImList<Pair<Double,Double>> inputTimes;
    
    Simulator(double numOfServers, int qmax, ImList<Pair<Double,Double>> inputTimes) {
        this.numOfServers = numOfServers;
        this.qmax = qmax;
        this.inputTimes = inputTimes;
    }

    ImList<Server> generateServers() {
        ImList<Server> allServers = new ImList<Server>();
        for (int i = 1; i <= this.numOfServers; i++) {
            allServers = allServers.add(new Server(String.valueOf(i), 0.0, this.qmax));
        }
        return allServers;
    }

    ImList<Customer> generateCustomers() {
        ImList<Customer> allCustomers = new ImList<Customer>();
        for (int i = 0; i < this.inputTimes.size(); i++) {
            double arrivalTime = this.inputTimes.get(i).first();
            double serviceTime = this.inputTimes.get(i).second();
            Customer customer = new Customer(i + 1, arrivalTime, serviceTime);
            allCustomers = allCustomers.add(customer);
        }
        return allCustomers;
    }

    PQ<Event> generateArriveEventsList(ImList<Customer> listOfCustomers) {
        PQ<Event> eventsPQ  = new PQ<Event>(new EventComp());
        for (Customer customer : listOfCustomers) {
            Event event = new Arrive(customer);
            eventsPQ = eventsPQ.add(event);
        }
        return eventsPQ;
    }

    <T> String print(ImList<T> list) {
        String output = "";
        boolean first = true;
        for (T t : list) {
            if (first) {
                output += t.toString();
                first = false;
            } else {
                output += "\n";
                output += t.toString();
            }
            
        }
        return output;
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

        ImList<Server> allServers = generateServers();
        ServerHandler serverHandler = new ServerHandler(allServers);
        ImList<Customer> allCustomers = generateCustomers();
        PQ<Event> eventsPQ = generateArriveEventsList(allCustomers);

        Pair<Event, PQ<Event>> eventsPair = eventsPQ.poll();
        ImList<Event> allEventsExecuted = new ImList<Event>();

        Statistics statistics = new Statistics();
        
        boolean terminalEventExecuted = true;

        while (!eventsPQ.isEmpty()) {
            // Gets the highest priority event
            eventsPair = eventsPQ.poll();
            Event event = eventsPair.first();
            eventsPQ = eventsPair.second();

            Server server = serverHandler.chooseServer(event);
            int serverID = serverHandler.getMatchingServerInList(server);

            if (event != event.execute(server).second()) {
                terminalEventExecuted = false;
            }

            if (event == event.execute(server).second()) {
                if (terminalEventExecuted) {
                    statistics = statistics.add(event.execute(server).second().getStatistics());
                    terminalEventExecuted = false;
                    continue;
                } else {
                    terminalEventExecuted = true;
                    //continue;
                }
            } 
            
            allEventsExecuted = allEventsExecuted.add(event);
            /*
            System.out.println("---------------------------------");
            System.out.println(eventsPQ);
            System.out.println(allServers);
            System.out.println(event);
            System.out.println(event.getStatistics());
            System.out.println(server);
            */
            Pair<Server, Event> serverAndEventPair = event.execute(server);
            server = serverAndEventPair.first();
            event = serverAndEventPair.second();

            allServers = allServers.set(serverID, server);
            serverHandler = new ServerHandler(allServers);

            eventsPQ = eventsPQ.add(event);
        }
        allEventsExecuted = allEventsExecuted.sort(new EventComp());
        return print(allEventsExecuted) + "\n" + statistics.toString();
    }
}
