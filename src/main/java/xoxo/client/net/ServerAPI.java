package xoxo.client.net;

import com.google.gson.Gson;

import xoxo.game.Game;
import xoxo.net.request.NetRequest;
import xoxo.net.request.Request;
import xoxo.net.request.game.PlayRequest;
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

    private void log(Response response) {
        System.out.println(response.body);
    }

    private void log() {
        System.out.println(
            net.getResponse()
        );
    }

    public void login(String username, String password) {
        net.request(new LoginRequest(username, password));
        final Response response = net.getResponse();
        if (response.type == NetResponse.OK) {
            this.username = username;
        }
        log(response);
    }

    public void singup(String username, String password) {
        net.request(new SignupRequest(username, password));
    }

    public void findMatch() {
        net.request(new Request(NetRequest.FIND_MATCH, username));
        log();
    }

    public void play(int x, int y) {
        net.request(new PlayRequest(x + "," + y));
        log();
    }

    public Response getResponse() {
        return net.getResponse();
    }

    public Game getUpdate() {
        return new Gson().fromJson(getResponse().body, Game.class);
    }
}