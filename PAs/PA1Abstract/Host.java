import java.util.List;

abstract class Host {
    protected final String identifier;
    protected final String message;
    protected final ImList<Term> listOfTerminals;
    protected final ImList<Term> listOfConnectedTerminals;

    Host(String identifier) {
        this.identifier = identifier;
        this.message = "";
        this.listOfTerminals = new ImList<Term>();
        this.listOfConnectedTerminals = new ImList<Term>();
    } 

    Host(Term term, Host host, String message, ImList<Term> listOfConnectedTerminals) {
        this.identifier = host.identifier;
        this.message = message;
        this.listOfTerminals = new ImList<Term>(List.of(term));
        this.listOfConnectedTerminals = listOfConnectedTerminals;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public ImList<Term> getList() {
        return this.listOfConnectedTerminals;
    }

    public void broadcast() {
        String allTerminals = "";
        for (Term term : this.listOfConnectedTerminals) {
            allTerminals += System.lineSeparator();
            allTerminals += term.broadcast();
        }
        System.out.println(allTerminals);
    }

    public boolean equals(Host host) {
        return this.identifier == host.identifier;
    }
}
