import java.util.List;

class Case {
    private final Person person;
    private final Test test;
    private final ImList<Test> testHistory;
    private final Protocol protocol;
    private final int numOfDays;

    Case(Person person, PCR pcr) {
        this.person = person;
        this.test = pcr;
        this.testHistory = new ImList<Test>(List.of(pcr));
        this.numOfDays = 1;
        this.protocol = this.determineProtocol(pcr);
    }

    protected Case(Case c) {
        this.person = c.person;
        this.test = c.test;
        this.testHistory = c.testHistory;
        this.numOfDays = c.numOfDays;
        this.protocol = c.protocol;
    }

    protected Case(Person person, Test test, ImList<Test> testHistory, int numOfDays) {
        this.person = person;
        this.test = test;
        this.testHistory = testHistory;
        this.numOfDays = numOfDays;
        this.protocol = this.determineProtocol(person, test);
    }

    Case(Person person, Test test, ImList<Test> testHistory, Protocol protocol, int numOfDays) {
        this.person = person;
        this.test = test;
        this.testHistory = testHistory;
        this.protocol = protocol;
        this.numOfDays = numOfDays;
    }

    Protocol determineProtocol(Test test) {
        if (test.isPositive()) {
            if (this.person.isHighRisk()) {
                return new P1();
            } else {
                return new P2();
            }
        } else {
            return new Discharge();
        }
    }

    Protocol determineProtocol(Person person, Test test) {
        if (test.isPositive()) {
            if (person.isHighRisk()) {
                return new P1();
            } else {
                return new P2();
            }
        } else {
            return new P3();
        }
    }

    public boolean hasContact() {
        return false;
    }

    public ImList<Case> getLineage() {
        return new ImList<Case>(List.of(this));
    }

    public Protocol getCurrentProtocol() {
        return this.protocol;
    }

    public ImList<Test> getTestHistory() {
        return this.testHistory;
    }

    public Case test(Test test) {
        int numOfDaysTested = this.numOfDays;
        if (test.isValid()) {
            numOfDaysTested = numOfDaysTested + 1;
            ImList<Test> nextTestHistory = this.testHistory.add(test);
            Protocol nextProtocol = this.protocol.next(this.person, test, numOfDaysTested);
            return new Case(this.person, this.test, nextTestHistory, nextProtocol, numOfDaysTested);
        } else {
            return this;
        }  
    }

    @Override
    public String toString() {
        return this.person.toString() + " " +
            this.testHistory.toString() + " " +
            this.protocol.toString();
    }
}

