class SelfCheckoutHandler extends Server {
    private final int numOfCounters;
    private final int numServing;
    private final ImList<SelfCheckoutOne> listOfCounters;
    private final int counterIndex;
    private final ImList<Integer> listOfCustomers;
    

    SelfCheckoutHandler(int name, double nextAvailableTime, int qmax, int numOfCounters) {
        super(name, nextAvailableTime, qmax, 0, true);
        this.numOfCounters = numOfCounters;
        this.numServing = 0;
        this.listOfCounters = generateSelfCheckoutCounters(name);
        this.counterIndex = 0;
        this.listOfCustomers = generateCounterCustomers(numOfCounters);
    }

    SelfCheckoutHandler(int name, double nextAvailableTime, int qmax, 
        int numInQueue,int numOfCounters, int numServing, boolean servable, 
        ImList<SelfCheckoutOne> listOfCounters, int counterIndex, 
        ImList<Integer> listOfCustomers) {
        super(name, nextAvailableTime, qmax, numInQueue, servable);
        this.numOfCounters = numOfCounters;
        this.numServing = numServing;
        this.listOfCounters = listOfCounters;
        this.counterIndex = counterIndex;
        this.listOfCustomers = listOfCustomers;
    }

    ImList<SelfCheckoutOne> generateSelfCheckoutCounters(int numOfServers) {
        ImList<SelfCheckoutOne> allCounters = new ImList<SelfCheckoutOne>();
        for (int i = numOfServers; i < (numOfServers + this.numOfCounters); i++) {
            allCounters = allCounters.add(new SelfCheckoutOne(i));
        }
        return allCounters;
    }

    ImList<Integer> generateCounterCustomers(int numOfServers) {
        ImList<Integer> allCounters = new ImList<Integer>();
        for (int i = numOfServers; i < (numOfServers + this.numOfCounters); i++) {
            allCounters = allCounters.add(0);
        }
        return allCounters;
    }

    public boolean waitable() {
        return this.numInQueue < this.qmax;
    }

    public SelfCheckoutHandler setCounterIndex(int index) {
        return new SelfCheckoutHandler(this.name, this.nextAvailableTime, 
            this.qmax, this.numInQueue, this.numOfCounters, this.numServing, 
            this.servable, this.listOfCounters, index, this.listOfCustomers); 
    }

    @Override
    public SelfCheckoutHandler serverFree() {
        return new SelfCheckoutHandler(this.name, this.nextAvailableTime, 
            this.qmax, this.numInQueue, this.numOfCounters, this.numServing, 
            true, this.listOfCounters, this.counterIndex, this.listOfCustomers);
    }

    @Override
    public SelfCheckoutHandler serverNotFree() {
        int counterIndex = 0;
        return new SelfCheckoutHandler(this.name, this.nextAvailableTime, 
            this.qmax, this.numInQueue, this.numOfCounters, this.numServing, 
            false, this.listOfCounters, counterIndex, this.listOfCustomers);
    }

    public SelfCheckoutHandler decrementServing() {
        return new SelfCheckoutHandler(this.name, this.nextAvailableTime, 
            this.qmax, this.numInQueue, this.numOfCounters, this.numServing - 1, 
            this.servable, this.listOfCounters, this.counterIndex, this.listOfCustomers);
    }

    @Override
    public SelfCheckoutHandler addRestTime(double restTime) {
        return this;
    }

    @Override
    public SelfCheckoutHandler setRest(boolean flag) {
        return this;
    }

    @Override
    public SelfCheckoutHandler checkAndUpdateRest(double time) {
        return this;
    }

    @Override
    public SelfCheckoutHandler checkAndUpdateQueue() {
        return this;
    }

    @Override
    public SelfCheckoutHandler incrementInQueue() {
        return new SelfCheckoutHandler(this.name, this.nextAvailableTime, 
            this.qmax, this.numInQueue + 1, this.numOfCounters, this.numServing, 
            this.servable, this.listOfCounters, this.counterIndex, this.listOfCustomers);
    }

    @Override
    public SelfCheckoutHandler decrementInQueue() {
        int newNumInQueue = Math.max(0, this.numInQueue - 1);
        return new SelfCheckoutHandler(this.name, this.nextAvailableTime, 
            this.qmax, newNumInQueue, this.numOfCounters, this.numServing, 
            this.servable, this.listOfCounters, this.counterIndex, this.listOfCustomers);
    }

    public double getNextAvailTime(ImList<SelfCheckoutOne> list) {
        double minAvailTime = list.get(0).getNextAvailableTime();

        for (int i =  0; i < list.size(); i++) {
            double time = list.get(i).getNextAvailableTime();
            if (time < minAvailTime) {
                minAvailTime = time;
            }
        }
        return minAvailTime;
    }

    SelfCheckoutHandler updateIndivCounter(double time) {
        SelfCheckoutOne sc = this.listOfCounters.get(0);
        ImList<SelfCheckoutOne> newList = this.listOfCounters;

        for (int i =  0; i < this.listOfCounters.size(); i++) {
            if (this.listOfCounters.get(i).getNextAvailableTime() <= time) {
                sc = this.listOfCounters.get(i);
                newList = newList.set(i, sc.serverFree());
            }
        }
        return new SelfCheckoutHandler(this.name, this.nextAvailableTime, 
            this.qmax, this.numInQueue, this.numOfCounters, this.numServing, 
            this.servable, newList, this.counterIndex, this.listOfCustomers);
    }

    @Override
    public SelfCheckoutHandler updateServer(Customer c) {
        int newNumServing = this.numServing + 1;
        double newTiming = this.nextAvailableTime;
        ImList<SelfCheckoutOne> newList = this.listOfCounters;
        int index = 0;
        int customerNo = c.getCustomerNo();
        ImList<Integer> newCustomerList = this.listOfCustomers;

        for (int i =  0; i < this.listOfCounters.size(); i++) {
            SelfCheckoutOne sc = this.listOfCounters.get(i);
            if (sc.servable()) {
                index = i;
                newCustomerList = newCustomerList.set(index, customerNo);

                if (c.getArrivalTime() < sc.getNextAvailableTime()) {
                    newTiming = sc.getNextAvailableTime() + c.getServiceTime();
                    newList = newList.set(i, 
                        new SelfCheckoutOne(sc.getServerID() + 1, newTiming).serverNotFree());
                } else {
                    newTiming =  c.getArrivalTime() + c.getServiceTime();
                    newList = newList.set(i, 
                        new SelfCheckoutOne(sc.getServerID() + 1, newTiming).serverNotFree());
                }
                break;
            }
        }
        return new SelfCheckoutHandler(this.name, getNextAvailTime(newList), this.qmax, 
            this.numInQueue, this.numOfCounters, newNumServing, 
            newNumServing < this.numOfCounters, newList, index, newCustomerList);
    }

    boolean isGettingServed(int number) {
        for (int i = 0; i < this.listOfCustomers.size(); i++) {
            if (number == this.listOfCustomers.get(i)) {
                return true;
            }
        }
        return false;
    }

    boolean allUnservable() {
        boolean test = false;
        for (SelfCheckoutOne scOne : this.listOfCounters) {
            test = test || scOne.servable();
        }
        return !test;
    }

    @Override
    public SelfCheckoutHandler updateIfFree(double time, Customer c) {
        int index = -1;
        if (isGettingServed(c.getCustomerNo())) {
            index = this.listOfCustomers.indexOf(c.getCustomerNo());
        }

        if (this.numServing - 1 < this.numOfCounters) {
            return this.serverFree().decrementServing()
                .updateIndivCounter(time).setCounterIndex(index);
        } else {
            return this.decrementServing().updateIndivCounter(time).setCounterIndex(index);
        }
    }

    SelfCheckoutOne chooseSelfCheckoutOne() {
        int counterIndex = this.counterIndex;

        if (counterIndex == -1) {
            return this.listOfCounters.sort(new SelfCheckoutComp()).get(0);
        } else {
            return this.listOfCounters.get(counterIndex);
        }
    }


    @Override
    public String toString() {
        return this.chooseSelfCheckoutOne().toString();
        //  + " " + this.listOfCounters.toString()
        //  + " " + this.counterIndex
        //  + " " + this.allUnservable()
        //  + " " + this.servable + " " + this.waitable()
        //  + " " + this.numServing + " " + this.qmax + " " + this.nextAvailableTime;
    }
}
