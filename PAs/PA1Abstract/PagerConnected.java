class PagerConnected extends Term {

    PagerConnected(String identifier) {
        super(identifier);
    }
 
    PagerConnected(Term term, Host host, String message, ImList<Term> listOfConnectedTerminals) {
        super(term, host, message, listOfConnectedTerminals);
    }

    @Override
    public String broadcast() {
        return this.identifier + ":beep";
    }
}

