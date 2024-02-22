class TransmitterReceived extends Host {

    TransmitterReceived(String identifier) {
        super(identifier);
    }
 
    TransmitterReceived(Term term, Host host, String message, ImList<Term> listOfConnectedTerminals) {
        super(term, host, message, listOfConnectedTerminals);
    }   

    public PagerReceived rcv() {
        String message = this.message + this.identifier + " >--rcv--> ";
        return new PagerReceived(this.listOfTerminals.get(0), this, message, this.listOfConnectedTerminals);
    }
}
