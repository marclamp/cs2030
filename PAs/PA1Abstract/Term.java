import java.util.List;

abstract class Term {
    protected final String identifier;
    protected final String message;
    protected final ImList<Host> listOfHosts;
    protected final ImList<Term> listOfConnectedTerminals;

    Term(String identifier) {
        this.identifier = identifier;
        this.message = "";
        this.listOfHosts = new ImList<Host>();
        this.listOfConnectedTerminals = new ImList<Term>();
    }

    Term(Term term, Host host, String message, ImList<Term> listOfConnectedTerminals) {
        this.identifier = term.identifier;
        this.message = message;
        this.listOfHosts = new ImList<Host>(List.of(host));
        this.listOfConnectedTerminals = listOfConnectedTerminals;
    }

    public boolean equals(Term term) {
        return this.identifier == term.identifier;
    }

    public String broadcast() {
        return "";
    }
}
