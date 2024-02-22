import java.util.List;

class Transmitter implements Host {
    private final String identifier;
    private final String message;
    private final ImList<Term> list;

    Transmitter(String identifier) {
        this.identifier = identifier;
        this.message = "";
        this.list = new ImList<Term>();
    }

    Transmitter(Term term, Host host, String message) {
        this.identifier = transmitter.identifier;
        this.message = message;
        this.list = new ImList<Term>(List.of(term));
    }   

    public boolean equals(Transmitter host) {
        return this.identifier == host.identifier;
    }
       
    @Override 
    public String toString() {
        return this.message + this.identifier;
    }
}
