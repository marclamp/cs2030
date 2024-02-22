class PCR implements Test {
    private final String result;

    PCR(String result) {
        this.result = result;
    }

    public boolean isPositive() {
        if (this.result == "alpha" || this.result == "beta" ||
                this.result == "delta" || this.result == "omicron") {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        String string = "P";
        if (this.isPositive()) {
            string += "+";
        } else {
            string += "-";
        }
        return string;
    }
}
