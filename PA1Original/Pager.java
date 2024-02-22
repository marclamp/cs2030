import java.util.List;

class Pager implements Term {
    private final String identifier;
    private final String message;
    private final ImList<Host> list;

    Pager(String identifier) {
        this.identifier = identifier;
        this.message = "";      
        this.list = new ImList<Host>();
    }

    Pager(Term term, Host host, String message) {
        this.identifier = term.identifier;
        this.message = message;
        this.list = new ImList<Host>(List.of(host));
    }

    public Host snd(Transmitter transmitter) {
        String message = this.identifier + " >--snd--> ";
        return new TransmitterReceived(this, transmitter, message);
    }

    public Host ack() {
        String message = this.message + " >--ack--> " + 
            this.list.get(0).toString();
        return new Pager(this.list.get(0), this, message);
    }

    public boolean equals(Pager term) {
        return this.identifier == term.identifier;
    }

    @Override
    public String toString() {
        return this.message + this.identifier;
    }
}
