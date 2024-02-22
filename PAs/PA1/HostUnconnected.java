abstract class HostUnconnected extends Host {

    HostUnconnected(String identifier) {
        super(identifier);
    } 

    HostUnconnected(String term, String host, String message, ImList<Term> listOfConnectedTerminals) {
        super(term, host, message, listOfConnectedTerminals);
    }
}
