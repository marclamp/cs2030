class P3 implements Protocol {
    private static final int NUM_FOR_MONITORING = 5;

    P3() {
    }
    
    public Protocol next(Person person, Test test, int numOfDays) {
        if (test.isPositive()) {
            if (person.isHighRisk()) {
                return new P1();
            } else {
                return new P2();
            }
        }

        if (numOfDays <= NUM_FOR_MONITORING) {
            return this;
        } else {
            return new Discharge("complete");
        }
    }

    @Override
    public String toString() {
        return "P3";
    }
}


