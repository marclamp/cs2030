import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Server server1 = new Server(name, 0.0);
        int customerCount = 0;

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            double serviceTime = sc.nextDouble();
            customerCount = customerCount + 1;
            Customer customer = new Customer(customerCount, arrivalTime, serviceTime);
            Event event = new Arrive(customer, server1);
            
            while (!event.equals(event.execute())) {
                System.out.println(event);
                event = event.execute();
                System.out.println(event);
            }
            server1 = server1.updateServer(customer);
        }
        sc.close();
    }
}
