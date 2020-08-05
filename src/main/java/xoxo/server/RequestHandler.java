package xoxo.server;

import xoxo.net.request.NetRequest;
import xoxo.net.request.Request;
import xoxo.net.request.user.IUserManager;
import xoxo.net.request.user.LoginRequest;
import xoxo.net.response.NetResponse;
import xoxo.net.response.Response;
import xoxo.server.net.INetwork;

public class RequestHandler implements IRequestHandler {

    private final IUserManager usermanager;
    private final INetwork net;

    public RequestHandler(INetwork net) {
        usermanager = null;
        this.net = net;
    }

    public void handle(Request request) {
        System.out.println("handing request with type: " + request.type);
        if (request.type == NetRequest.LOGIN) {
            final LoginRequest req = new LoginRequest(request.body);
            net.respond(new Response(NetResponse.OK, "hi " + req.getUser().username).toString(), request.getAuth());
        }
    }

}