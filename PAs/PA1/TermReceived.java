import java.util.List;

class TermReceived extends Term {

    TermReceived(String identifier) {
        super(identifier);
    }
 
    TermReceived(String term, String host, String message, ImList<Term> listOfConnectedTerminals) {
        super(term, host, message, listOfConnectedTerminals);
    }   

    public HostConnected ack() {
        String message = this.getMessage() + this.getIdentifier() + " >--ack--> ";
        ImList<Term> listOfTermsConnected = this.getListOfConnected()
                .add(new TermConnected(this.getIdentifier()));
        return new HostConnected(this.getIdentifier(), this.getHost(), 
                message, listOfTermsConnected);
    }
}

