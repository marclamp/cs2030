class P2 implements Protocol {
    private static final int NUM_FOR_POSITIVE = 3;
    private static final int NUM_FOR_VACCINATED = 7;
    private static final int NUM_FOR_UNVACCINATED = 14;

    P2() {
    }

    public Protocol next(Person person, Test test, int numOfDays) {
        if (person.isVaccinated()) {
            if (numOfDays > NUM_FOR_VACCINATED) {
                return new Discharge("complete");
            }
        } else {
            if (numOfDays > NUM_FOR_UNVACCINATED) {
                return new Discharge("complete");
            }
        }

        if (numOfDays <= NUM_FOR_POSITIVE) {
            return new P2();
        } else if (test.isPositive()) {
            return new P2();
        } else {
            return new Discharge("complete");
        }
    }

    @Override
    public String toString() {
        return "P2";
    }
}
