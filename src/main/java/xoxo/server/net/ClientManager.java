package xoxo.server.net;

import java.util.HashMap;
import java.util.Map;

public class ClientManager {
    private final Map<String, Client> clients;
    
    public ClientManager() {
        clients = new HashMap<>();
    }
    public void add(Client client) {
        // i could change this
        client.setAuth(generateAuth());
        clients.put(client.getAuth(), client);
        client.start();
    }

    private String generateAuth() {
        return "auth";
    }

    public Client find(String auth) {
        return clients.get(auth);
    }
}
