class P1 implements Protocol {

    P1() {
    }

    public Protocol next(Person person, Test test, int numOfDays) {
        if (test.isValid()) {
            if (test.isPositive()) {
                return this;
            } else {
                return new Discharge();
            }
        } else { // is not valid
            return this;
        }
    }

    @Override
    public String toString() {
        return "P1";
    }
}
