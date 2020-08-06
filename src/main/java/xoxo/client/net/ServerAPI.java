package xoxo.client.net;

import xoxo.net.request.NetRequest;
import xoxo.net.request.Request;
import xoxo.net.request.user.LoginRequest;
import xoxo.net.request.user.SignupRequest;
import xoxo.net.response.NetResponse;
import xoxo.net.response.Response;

public class ServerAPI {
    private final Network net;
    private String username;

    public ServerAPI(Network net) {
        this.net = net;
    }

    public void login(String username, String password) {
        net.request(new LoginRequest(username, password));
        final Response response = net.getResponse();
        if (response.type== NetResponse.OK) {
            this.username = username;
        }
    }

    public void singup(String username, String password) {
        net.request(new SignupRequest(username, password));
    }

    public void findMatch() {
        net.request(new Request(NetRequest.START_GAME, username));
    }
}