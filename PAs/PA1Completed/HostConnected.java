class HostConnected extends HostUnconnected {

    HostConnected(String identifier) {
        super(identifier);
    }
 
    HostConnected(String term, String host, String message, 
            ImList<Term> listOfPagersConnected) {
        super(term, host, message, listOfPagersConnected);
    }   
}
