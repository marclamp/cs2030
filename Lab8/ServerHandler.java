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
            //System.out.println(allServers.sort(new ServerComp()));
            return allServers.sort(new ServerComp()).get(0);
        }
    }

    ServerHandler updateAllRestingStates(Event event) {
        double time = event.getEventTime();
        ImList<Server> newList = new ImList<Server>();
        for (int i = 0; i < this.allServers.size(); i++) {
            Server server = this.allServers.get(i);
            server = server.checkAndUpdateRest(time);
            newList = newList.add(server);
        }
        return new ServerHandler(newList);
    }
}
