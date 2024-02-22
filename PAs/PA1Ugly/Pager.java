import java.util.List;

class Pager implements Term {
    private final String identifier;
    private final String message;
    private final ImList<Host> listOfHosts;
    private final ImList<Term> listOfConnectedTerminals;

    Pager(String identifier) {
        this.identifier = identifier;
        this.message = "";
        this.listOfHosts = new ImList<Host>();
        this.listOfConnectedTerminals = new ImList<Term>();
    }

    Pager(Term term, Host host, String message, ImList<Term> listOfConnectedTerminals) {
        this.identifier = term.getIdentifier();
        this.message = message;
        this.listOfHosts = new ImList<Host>(List.of(host));
        this.listOfConnectedTerminals = listOfConnectedTerminals;
    }

    public TransmitterReceived snd(Transmitter transmitter) {
        String message = this.identifier + " >--snd--> ";
        return new TransmitterReceived(this, transmitter, message, transmitter.getListConnected());
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

    @Override
    public String toString() {
        return this.message + this.identifier;
    }
}
