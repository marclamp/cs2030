class Discharge implements Protocol {
    private final String dischargeState;

    Discharge() {
        this.dischargeState = "follow up with doctor";
    }

    Discharge(String state) {
        if (state == "complete") {
            this.dischargeState = state;
        } else if (state == "seek") {
            this.dischargeState = "seek medical attention";
        } else {
            this.dischargeState = "follow up with doctor";
        }
    }

    public Protocol next(Person person, Test test, int numOfDays) {
        if (test.isValid() && test.isPositive()) {
            return new Discharge("seek");        
        } else { // discharged and negative
            return this;
        }
    }

    @Override
    public String toString() {
        return "Discharged: " + this.dischargeState;
    }
}
