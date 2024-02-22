import java.util.List;

class TransmitterReceived implements Host {
    private final String identifier;
    private final String message;
    private final ImList<Term> listOfTerminals;
    private final ImList<Term> listOfConnectedTerminals;

    TransmitterReceived(String identifier) {
        this.identifier = identifier;
        this.message = "";
        this.listOfTerminals = new ImList<Term>();
        this.listOfConnectedTerminals = new ImList<Term>();
    } 
 
    TransmitterReceived(Term term, Host host, String message, ImList<Term> listOfConnectedTerminals) {
        this.identifier = host.getIdentifier();
        this.message = message;
        this.listOfTerminals = new ImList<Term>(List.of(term));
        this.listOfConnectedTerminals = listOfConnectedTerminals;
    } 

    public PagerReceived rcv() {
        String message = this.message + this.identifier + " >--rcv--> ";
        return new PagerReceived(this.listOfTerminals.get(0), this, message, this.listOfConnectedTerminals);
    }

    public void broadcast() {
        String allTerminals = "";
        for (Term term : this.listOfConnectedTerminals) {
            allTerminals += System.lineSeparator();
            allTerminals += term.broadcast();
        }
        System.out.println(allTerminals);
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public ImList<Term> getListConnected() {
        return this.listOfConnectedTerminals;
    }

    public boolean equals(Host host) {
        return this.identifier == host.getIdentifier();
    }

    @Override 
    public String toString() {
        return this.message + this.identifier;
    }
}
