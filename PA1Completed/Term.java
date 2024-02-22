import java.util.List;

abstract class Term {
    private final String identifier;
    private final String message;
    private final String host;
    private final ImList<Term> listOfConnectedTerminals;

    Term(String identifier) {
        this.identifier = identifier;
        this.message = "";
        this.host = "";
        this.listOfConnectedTerminals = new ImList<Term>();
    }

    Term(String term, String host, String message, ImList<Term> listOfConnectedTerminals) {
        this.identifier = term;
        this.message = message;
        this.host = host;
        this.listOfConnectedTerminals = listOfConnectedTerminals;
    }

    protected String getIdentifier() {
        return this.identifier;
    }

    protected String getMessage() {
        return this.message;
    }

    protected String getHost() {
        return this.host;
    }

    protected ImList<Term> getListOfConnected() {
        return this.listOfConnectedTerminals;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Term term) {
            return this.identifier.equals(term.identifier);
        } else {
            return false;
        }
    }

    public String broadcast() {
        return "";
    }

    @Override 
    public String toString() {
        return this.getMessage() + this.getIdentifier();
    }
}
