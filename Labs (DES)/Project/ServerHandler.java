class ServerHandler {
    private final ImList<Server> allServers;

    ServerHandler(ImList<Server> allServers) {
        this.allServers = allServers;
    }

    public int getMatchingServerInList(Server server) {
        for (int i = 0; i < this.allServers.size(); i++) {
            if (server.isSameServer(this.allServers.get(i))) {
                return i;
            }
        }
        return -1;  
    }

    Server chooseServer(Event event) {
        if (event.inQueue()) {
            int serverID = event.getServerID();
            return allServers.get(serverID);
        } else {
            return allServers.sort(new ServerComp()).get(0);
        }
    }
}
