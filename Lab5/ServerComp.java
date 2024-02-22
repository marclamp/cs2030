import java.util.Comparator;

class ServerComp implements Comparator<Server> {
    @Override
    public int compare(Server server1, Server server2) {
        return server1.compareTo(server2);
    }
}