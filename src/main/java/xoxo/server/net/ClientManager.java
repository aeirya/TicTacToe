package xoxo.server.net;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    private final List<Client> clients;
    
    public ClientManager() {
        clients = new ArrayList<>();
    }
    public void add(Client client) {
        clients.add(client);
        client.start();
    }
}
