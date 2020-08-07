package xoxo.server;

import xoxo.net.request.Request;
import xoxo.net.request.game.PlayRequest;
import xoxo.net.request.menu.FindMatchRequest;
import xoxo.net.request.user.DeleteRequest;
import xoxo.net.request.user.IUserManager;
import xoxo.net.request.user.LoginRequest;
import xoxo.net.request.user.LogoutRequest;
import xoxo.net.request.user.SignupRequest;
import xoxo.net.response.Response;
import xoxo.server.net.INetwork;
import xoxo.server.user.OnlineUser;
import xoxo.server.user.UserManager;

public class RequestHandler implements IRequestHandler {

    private final IUserManager usermanager;
    private final MatchFinder matcher;
    private final INetwork net;

    public RequestHandler(INetwork net) {
        this.net = net;
        usermanager = new UserManager();
        matcher = new MatchFinder(usermanager);
    }

    public void handle(Request request) {
        respond(apply(request), request.getAuth());
    }

    public Response apply(Request request) {
        switch(request.type) {
            case LOGIN:
            return new LoginRequest(request).apply(usermanager);
            case REGISTER:
            return new SignupRequest(request).apply(usermanager);
            case DELETE:
            return new DeleteRequest(request).apply(usermanager);
            case LOGOUT:
            return new LogoutRequest(request).apply(usermanager);
            case FIND_MATCH:
            return new FindMatchRequest(request).apply(matcher);
            case PLAY:
            final OnlineUser user = getUser(request);
            return new PlayRequest(request).apply(user);
            default:
            return null;
        }
    }

    public void respond(Response response, String auth) {
        net.respond(response.toString(), auth);
    }

    public OnlineUser getUser(Request request) { 
        return usermanager
            .findUserWithAuth(request.getAuth());
    }
}