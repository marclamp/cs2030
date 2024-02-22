/open openall.jsh

/*
Server s = new Server("1", 0.0, 3, 1)
Customer c1 = new Customer(1, 0.0, 1.0)
Event event = new Arrive(c1)
event.execute(s)
s = event.execute(s).first()

Customer c2 = new Customer(2, 0.0, 1.0)
Event event = new Arrive(c2)
event.execute(s)
*/

Simulator sim = new Simulator(1, 1, new ImList<Pair<Double, Double>>(
    List.of(new Pair<Double, Double>(0.500, 1.000),
            new Pair<Double, Double>(0.600, 1.000),
            new Pair<Double, Double>(0.600, 1.000),
            new Pair<Double, Double>(0.700, 1.000))))

System.out.println(sim.simulate());