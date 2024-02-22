abstract class Host {
    private final String identifier;
    private final String message;
    private final String term;
    private final ImList<Term> listOfConnectedTerminals;

    Host(String identifier) {
        this.identifier = identifier;
        this.message = "";
        this.term = "";
        this.listOfConnectedTerminals = new ImList<Term>();
    }

    Host(String term, String host, String message, ImList<Term> listOfConnectedTerminals) {
        this.identifier = host;
        this.message = message;
        this.term = term;
        this.listOfConnectedTerminals = listOfConnectedTerminals;
    }

    protected String getIdentifier() {
        return this.identifier;
    }

    protected String getMessage() {
        return this.message;
    }

    protected String getTerm() {
        return this.term;
    }

    protected ImList<Term> getListOfConnected() {
        return this.listOfConnectedTerminals;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Host host) {
            return host.identifier.equals(this.identifier);
        } else {
            return false;
        }
    }

    public void broadcast() {
        String allTerminals = "";
        boolean first = true;
        for (Term term : this.listOfConnectedTerminals) {
            if (first == true) {
                allTerminals += term.broadcast();
                first = false;
            } else {
                allTerminals += System.lineSeparator();
                allTerminals += term.broadcast();
            }
        }
        System.out.println(allTerminals);
    }

    @Override 
    public String toString() {
        return this.getMessage() + this.getIdentifier();
    }
}