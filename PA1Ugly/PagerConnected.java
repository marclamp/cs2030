import java.util.List;

class PagerConnected implements Term {
    private final String identifier;
    private final String message;
    private final ImList<Host> listOfHosts;
    private final ImList<Term> listOfConnectedTerminals;

    PagerConnected(String identifier) {
        this.identifier = identifier;
        this.message = "";
        this.listOfHosts = new ImList<Host>();
        this.listOfConnectedTerminals = new ImList<Term>();
    }
 
    PagerConnected(Term term, Host host, String message, ImList<Term> listOfConnectedTerminals) {
        this.identifier = term.getIdentifier();
        this.message = message;
        this.listOfHosts = new ImList<Host>(List.of(host));
        this.listOfConnectedTerminals = listOfConnectedTerminals;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public boolean equals(Term term) {
        return this.identifier == term.getIdentifier();
    }

    @Override
    public String broadcast() {
        return this.identifier + ":beep";
    }
}

