import java.util.List;

class Pager extends Term {

    Pager(String identifier) {
        super(identifier);
    }

    Pager(Term term, Host host, String message, ImList<Term> listOfConnectedTerminals) {
        super(term, host, message, listOfConnectedTerminals); 
    }

    public TransmitterReceived snd(Transmitter transmitter) {
        String message = this.identifier + " >--snd--> ";
        return new TransmitterReceived(this, transmitter, message, transmitter.listOfConnectedTerminals);
    }

    @Override
    public String toString() {
        return this.message + this.identifier;
    }
}
