class TransmitterReceived extends Transmitter {
    private final String identifier;
    private final String message;

    TransmitterReceived(String identifier) {
        super(identifier);
    }
 
    TransmitterReceived(Term term, Host host, String message) {
        super(term, host, message);
    }   

    public Term rcv() {
        String message = this.message + " >--rcv--> ";
        return new Pager(this.list.get(0), this, message);
    }
}
