import java.util.List;

class PagerReceived implements Term {
    private final String identifier;
    private final String message;
    private final ImList<Host> listOfHosts;
    private final ImList<Term> listOfConnectedTerminals;

    PagerReceived(String identifier) {
        this.identifier = identifier;
        this.message = "";
        this.listOfHosts = new ImList<Host>();
        this.listOfConnectedTerminals = new ImList<Term>();
    }
 
    PagerReceived(Term term, Host host, String message, ImList<Term> listOfConnectedTerminals) {
        this.identifier = term.getIdentifier();
        this.message = message;
        this.listOfHosts = new ImList<Host>(List.of(host));
        this.listOfConnectedTerminals = listOfConnectedTerminals;
    }

    public TransmitterConnected ack() {
        String message = this.message + this.identifier + " >--ack--> ";
        ImList<Term> listOfPagersConnected = this.listOfConnectedTerminals.
                add(new PagerConnected(this.identifier));
        return new TransmitterConnected(this, this.listOfHosts.get(0), message, listOfPagersConnected);
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public boolean equals(Term term) {
        return this.identifier == term.getIdentifier();
    }

    public String broadcast() {
        return "";
    }
}

