import java.util.function.Supplier;

class Simulator {
    private final double numOfServers;
    private final int qmax;
    private final ImList<Pair<Double,Supplier<Double>>> inputTimes;
    
    Simulator(double numOfServers, int qmax, ImList<Pair<Double,Supplier<Double>>> inputTimes) {
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
        ImList<Event> waitAfterList = new ImList<Event>();

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

            Pair<Server, Event> serverAndEventPair = event.execute(server);
            Server newServer = serverAndEventPair.first();
            Event newEvent = serverAndEventPair.second();

            if (event != newEvent) {
                terminalEventExecuted = false;
            } else {
                if (terminalEventExecuted) {
                    //System.out.println("Terminal Event Executed");
                    statistics = statistics.add(newEvent.getStatistics());
                    terminalEventExecuted = false;
                    
                    // Handles WaitAfterEvents
                    if (!newServer.hasQueue()) {
                        //System.out.println("It worked");
                        //System.out.println(waitAfterList);
                        WaitAfterEventHandler waitHandler 
                            = new WaitAfterEventHandler(waitAfterList);
                        int waitIndex = waitHandler.hasMatchingEvent(newServer);

                        if (waitIndex >= 0) {
                            //System.out.println("Has matching waiting event");
                            Pair<Event, ImList<Event>> holdPair 
                                = waitHandler.getEarliestMatchingEvent(waitIndex);
                            Event holdEvent = holdPair.first();
                            waitAfterList = holdPair.second();

                            allEventsExecuted = allEventsExecuted.add(holdEvent);
                            Pair<Server, Event> waitAfterPair = holdEvent.execute(newServer);
                            holdEvent = waitAfterPair.second();
                            newServer = waitAfterPair.first();

                            // allServers = allServers.set(serverID, newServer);
                            // serverHandler = new ServerHandler(allServers);
                            eventsPQ = eventsPQ.add(holdEvent);

                            /*
                            System.out.println("---------------------------------");
                            System.out.println("Event: " + holdEvent);
                            System.out.println(eventsPQ);
                            System.out.println(waitAfterList);
                            System.out.println(allEventsExecuted);
                            System.out.println("Before edit: " + allServers);
                            System.out.println("Event Stats: " + holdEvent.getStatistics());
                            System.out.println("Server selected: " + server);
                            System.out.println("Server updated: " + newServer);
                            allServers = allServers.set(serverID, newServer);
                            serverHandler = new ServerHandler(allServers);
                            System.out.println("After edit: " + allServers);
                            System.out.println("---------------------------------");
                            */
                        } 
                    }
                    continue;
                } else {
                    terminalEventExecuted = true;
                    //continue;
                }
            } 
            
            if (newEvent.isWaitAfter()) {
                waitAfterList = waitAfterList.add(newEvent);
                continue;
            }

            allEventsExecuted = allEventsExecuted.add(event);
            
            /*
            System.out.println("---------------------------------");
            System.out.println("Event: " + event);
            System.out.println(eventsPQ);
            System.out.println(waitAfterList);
            System.out.println(allEventsExecuted);
            System.out.println("Before edit: " + allServers);
            System.out.println("Event Stats: " + event.getStatistics());
            System.out.println("Server selected: " + server);
            System.out.println("Server updated: " + newServer);
            allServers = allServers.set(serverID, newServer);
            serverHandler = new ServerHandler(allServers);
            System.out.println("After edit: " + allServers);
            System.out.println("---------------------------------");
            */

            allServers = allServers.set(serverID, newServer);
            serverHandler = new ServerHandler(allServers);
            eventsPQ = eventsPQ.add(newEvent);
        }


        allEventsExecuted = allEventsExecuted.sort(new EventComp());
        return print(allEventsExecuted) + "\n" + statistics.toString();
    }
}
