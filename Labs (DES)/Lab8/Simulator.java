import java.util.function.Supplier;

class Simulator {
    private final double numOfServers;
    private final int numOfSelfChecks;
    private final int qmax;
    private final ImList<Pair<Double,Supplier<Double>>> inputTimes;
    private final Supplier<Double> restTimes;

    Simulator(double numOfServers, int numOfSelfChecks, int qmax, 
        ImList<Pair<Double,Supplier<Double>>> inputTimes, Supplier<Double> restTimes) {
        this.numOfServers = numOfServers;
        this.numOfSelfChecks = numOfSelfChecks;
        this.qmax = qmax;
        this.inputTimes = inputTimes;
        this.restTimes = restTimes;
    }

    ImList<Server> generateServers() {
        ImList<Server> allServers = new ImList<Server>();
        for (int i = 1; i <= this.numOfServers; i++) {
            allServers = allServers.add(
                new Server(i, 0.0, this.qmax, this.restTimes));
        }
        if (numOfSelfChecks > 0) {
            allServers = allServers.add(
                new SelfCheckoutHandler((int) this.numOfServers + 1, 
                    0.0, qmax, numOfSelfChecks));
        }
        return allServers;
    }

    ImList<Customer> generateCustomers() {
        ImList<Customer> allCustomers = new ImList<Customer>();
        for (int i = 0; i < this.inputTimes.size(); i++) {
            double arrivalTime = this.inputTimes.get(i).first();
            Lazy<Double> serviceTime = Lazy.<Double>of(this.inputTimes.get(i).second());
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

    // <T> String nicePrint(ImList<T> list) {
    //     String output = "";
    //     boolean first = true;
    //     for (T t : list) {
    //         if (first) {
    //             output += t.toString();
    //             first = false;
    //         } else {
    //             output += "\n";
    //             output += t.toString();
    //         }
    //     }
    //     return output;
    // }

    String nicePrint(ImList<Pair<Server, Event>> list) {
        String output = "";
        boolean first = true;
        for (Pair<Server, Event> p : list) {
            if (first) { 
                first = false;
            } else {
                output += "\n";
            }
            Server server = p.first();
            Event event = p.second();

            output += event.toString();

            if (event.isServe() || event.isWait() || event.isDone()) {
                output += server.toString();
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
        // ImList<Event> allEventsExecuted = new ImList<Event>();
        ImList<Pair<Server, Event>> allEventsExecuted = new ImList<Pair<Server, Event>>();

        Statistics statistics = new Statistics();

        boolean terminalEventExecuted = true;

        while (!eventsPQ.isEmpty()) {
            // System.out.println("loop");
            // Gets the highest priority event
            eventsPair = eventsPQ.poll();
            Event event = eventsPair.first();
            eventsPQ = eventsPair.second();
            serverHandler = serverHandler.updateAllRestingStates(event);

            Server server = serverHandler.chooseServer(event);
            int serverID = serverHandler.getMatchingServerInList(server);

            Pair<Server, Event> serverAndEventPair = event.execute(server);
            Server newServer = serverAndEventPair.first();
            Event newEvent = serverAndEventPair.second();

            if (!event.equals(newEvent)) {
                terminalEventExecuted = false;
            } else {
                if (terminalEventExecuted) {
                    //System.out.println("Terminal Event Executed");
                    statistics = statistics.add(newEvent.getStatistics());
                    terminalEventExecuted = false; 
                    continue;
                } else {
                    terminalEventExecuted = true;
                }
            } 

            if ((event.isServe() || event.isDone()) && !eventsPQ.isEmpty()) {
                double nextAvailableTime = newServer.getNextAvailableTime();
                Event nextEvent = eventsPQ.poll().first();
                ImList<Event> tempList = new ImList<Event>();

                while (!eventsPQ.isEmpty() && nextEvent.isServe() && nextEvent.isSameTime(event) 
                    && nextEvent.isSameServer(event)) {
                    /*
                    System.out.println(event);
                    System.out.println("nextEvent: " + nextEvent);
                    System.out.println("Before: " + eventsPQ);
                    */

                    eventsPQ = eventsPQ.poll().second();
                    Event addEvent = nextEvent.updateServeEventTime(nextAvailableTime);
                    tempList = tempList.add(addEvent);
                    nextEvent = eventsPQ.poll().first();

                    /*
                    boolean updated = pair.first();
                    eventsPQ = eventsPQ.add(addEvent);
                    nextEvent = eventsPQ.poll().first();
                    System.out.println("After: " + eventsPQ);
                    System.out.println("nextEvent: " + nextEvent);
                    System.out.println("---------------------------------");
                    if (updated) {
                        //System.out.println("Not added");
                        break;
                    } */
                }
                for (Event e : tempList) {
                    eventsPQ = eventsPQ.add(e);
                }
            }

            allEventsExecuted = allEventsExecuted.add(new Pair<Server, Event>(newServer, event));

            /*
            System.out.println("---------------------------------");
            System.out.println("Event: " + event);
            System.out.println(eventsPQ);
            //System.out.println(allEventsExecuted);
            System.out.println("Before edit: " + allServers);
            System.out.println("Event Stats: " + event.getStatistics());
            System.out.println("Server selected: " + server);
            System.out.println("Server updated: " + newServer);
            allServers = allServers.set(serverID, newServer);
            serverHandler = new ServerHandler(allServers);
            System.out.println("After edit: " + allServers);
            System.out.println(statistics);
            System.out.println("---------------------------------");
            // */

            allServers = allServers.set(serverID, newServer);
            serverHandler = new ServerHandler(allServers);
            eventsPQ = eventsPQ.add(newEvent);
        }
        allEventsExecuted = allEventsExecuted.sort(new EventServerPairComp());
        return nicePrint(allEventsExecuted) + "\n" + statistics.toString();
    }
}
