import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Server server1 = new Server(name, 0.0);
        int customerCount = 0;
        PQ<Event> eventsPQ  = new PQ<Event>(new EventComp());
        Pair<Event, PQ<Event>> eventsPair = eventsPQ.poll();
        Event event = new Arrive(new Customer(0, 0.0, 0.0), server1);
        Pair<Server, Event> serverAndEventPair = new Pair<Server, Event>(server1, event); 

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            double serviceTime = sc.nextDouble();
            customerCount = customerCount + 1;
            Customer customer = new Customer(customerCount, arrivalTime, serviceTime);
            event = new Arrive(customer, server1);
            eventsPQ = eventsPQ.add(event);    
        }
        
        while (!eventsPQ.isEmpty()) {
            eventsPair = eventsPQ.poll();
            event = eventsPair.first();
            eventsPQ = eventsPair.second();
            System.out.println(event);
            
            serverAndEventPair = event.execute();
            server1 = serverAndEventPair.first();
            Event nextEvent = serverAndEventPair.second();

            if (event != nextEvent) {
                eventsPQ = eventsPQ.add(nextEvent);
            }
            event = nextEvent;
        }
        sc.close();
    }
}
