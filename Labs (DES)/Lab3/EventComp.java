import java.util.Comparator;

class EventComp implements Comparator<Event> {
    public int compare(Event event1, Event event2) {
        if(event1.getEventTime() == event2.getEventTime()) {
            return event1.compareTo(event2);
        } else {
            return event1.getEventTime() - event2.getEventTime();
        }
    }
}
