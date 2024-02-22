import java.util.List;

class Transmitter extends Host {

    Transmitter(String identifier) {
        super(identifier);
    }

    Transmitter(Term term, Host host, String message, ImList<Term> list) {
        super(term, host, message, list);
    }   

    @Override 
    public String toString() {
        return this.message + this.identifier;
    }
}
