import java.util.List;

class PagerReceived extends Term {

    PagerReceived(String identifier) {
        super(identifier);
    }
 
    PagerReceived(Term term, Host host, String message, ImList<Term> listOfConnectedTerminals) {
        super(term, host, message, listOfConnectedTerminals);
    }   

    public TransmitterConnected ack() {
        String message = this.message + this.identifier + " >--ack--> ";
        ImList<Term> listOfPagersConnected = this.listOfConnectedTerminals.
                add(new PagerConnected(this.identifier));
        return new TransmitterConnected(this, this.listOfHosts.get(0), message, listOfPagersConnected);
    }
}

