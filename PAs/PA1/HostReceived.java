class HostReceived extends Host {

    HostReceived(String term, String host, String message, ImList<Term> listOfConnectedTerminals) {
        super(term, host, message, listOfConnectedTerminals);
    } 

    public TermReceived rcv() {
        String message = this.getMessage() + this.getIdentifier() + " >--rcv--> ";
        return new TermReceived(this.getTerm(), this.getIdentifier(), 
                message, this.getListOfConnected());
    }
}
