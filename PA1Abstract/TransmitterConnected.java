class TransmitterConnected extends Transmitter {

    TransmitterConnected(String identifier) {
        super(identifier);
    }
 
    TransmitterConnected(Term term, Host host, String message, ImList<Term> listOfPagersConnected) {
        super(term, host, message, listOfPagersConnected);
    }   

}
