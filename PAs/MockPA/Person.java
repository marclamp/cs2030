class Person {
    private final String identification;
    private final String vaccinationStatus;
    private final int healthRiskScore;
    private static final int HEALTH_RISK_LIMIT = 7;

    Person(String identification, String vaccinationStatus, int healthRiskScore) {
        this.identification = identification;
        this.vaccinationStatus = vaccinationStatus;
        this.healthRiskScore = healthRiskScore;
    }

    public boolean isVaccinated() {
        return this.vaccinationStatus.length() > 1;
    }

    public boolean isHighRisk() {
        if (this.healthRiskScore > HEALTH_RISK_LIMIT) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String string = this.identification + "/" + this.vaccinationStatus + "/";
        if (this.isHighRisk()) {
            string += "HIGH";
        } else {
            string += "LOW";
        }
        return string;
    }
}

