import java.util.Comparator;

class EventServerPairComp implements Comparator<Pair<Server, Event>> {
    @Override
    public int compare(Pair<Server, Event> pair1, Pair<Server, Event> pair2) {
        return pair1.second().compareTo(pair2.second());
    }
}
