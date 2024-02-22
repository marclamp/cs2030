class WaitAfterEventHandler {
    private final ImList<Event> allWaitAfters;

    WaitAfterEventHandler() {
        this.allWaitAfters = new ImList<Event>();
    }

    WaitAfterEventHandler(ImList<Event> allWaitAfters) {
        this.allWaitAfters = allWaitAfters;
    }

    public int hasMatchingEvent(Server server) {
        for (int i = 0; i < this.allWaitAfters.size(); i++) {
            if (this.allWaitAfters.get(i).getServerID() 
                    == server.getServerID()) {
                return i;
            }
        }
        return -1;
    }

    public Pair<Event, ImList<Event>> getEarliestMatchingEvent(int i) {
        Event event = this.allWaitAfters.get(i);
        ImList<Event> list = this.allWaitAfters.remove(i);
        return new Pair<Event, ImList<Event>>(event, list);
    }

}
