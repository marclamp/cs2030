import java.util.List;

class Transmitter extends HostUnconnected {

    Transmitter(String identifier) {
        super(identifier);
    }

    Transmitter(String term, String host, String message, ImList<Term> list) {
        super(term, host, message, list);
    }   
}
