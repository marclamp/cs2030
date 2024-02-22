class TermConnected extends Term {

    TermConnected(String identifier) {
        super(identifier);
    }
 
    TermConnected(String term, String host, String message, ImList<Term> listOfConnectedTerminals) {
        super(term, host, message, listOfConnectedTerminals);
    }

    @Override
    public String broadcast() {
        return this.getIdentifier() + ":beep";
    }
}

