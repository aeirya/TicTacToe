package xoxo.client.net;

import com.google.gson.Gson;

import xoxo.net.request.NetRequest;
import xoxo.net.request.Request;

public class Network {

    private final INetworkService server;
    private String auth;
    
    public Network(String ip, int port) {
        server = initiateNetworkService(ip, port);
    }

    private INetworkService initiateNetworkService(String ip, int port) {
        // TODO: also pass an response handler
        return new NetworkService(ip, port);
    }

    public void connect() {
        server.connect();
    }

    public void request(Request request) {
        server.send(
            parse(request.sign(auth))
        );
    }

    public void request(NetRequest type, String body) {
        request(new Request(type, body));
    }

    private String parse(Request request) {
        return new Gson().toJson(request);
    }
}