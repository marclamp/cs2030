class ART implements Test {
    private final String result;

    ART(String result) {
        this.result = result;
    }

    public boolean isPositive() {
        if (this.result == "CT") {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValid() {
        if (this.result == "C" || this.result == "CT") {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String string = "A";
        if (this.isValid()) {
            if (this.isPositive()) {
                string += "+";
            } else {
                string += "-";
            }
        } else {
            string += "X";
        }
        return string;
    }
}
