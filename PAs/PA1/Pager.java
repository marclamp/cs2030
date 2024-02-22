import java.util.List;

class Pager extends Term {

    Pager(String identifier) {
        super(identifier);
    }

    Pager(String term, String host, String message, ImList<Term> listOfConnectedTerminals) {
        super(term, host, message, listOfConnectedTerminals); 
    }

    public HostReceived snd(HostUnconnected host) {
        String message = this.getIdentifier() + " >--snd--> ";
        return new HostReceived(this.getIdentifier(), host.getIdentifier(), 
                message, host.getListOfConnected());
    }
}
