package xoxo.server;

import xoxo.net.request.Request;
import xoxo.net.request.game.GetSaveRequest;
import xoxo.net.request.game.GetUpdateRequest;
import xoxo.net.request.game.PlayRequest;
import xoxo.net.request.menu.FindMatchRequest;
import xoxo.net.request.menu.GetScoreboardRequest;
import xoxo.net.request.menu.MatchFinishedRequest;
import xoxo.net.request.user.DeleteRequest;
import xoxo.net.request.user.IUserManager;
import xoxo.net.request.user.LoginRequest;
import xoxo.net.request.user.LogoutRequest;
import xoxo.net.request.user.SignupRequest;
import xoxo.net.response.Response;
import xoxo.server.net.INetwork;
import xoxo.server.score.Scoreboard;
import xoxo.server.user.OnlineUser;
import xoxo.server.user.UserManager;

public class RequestHandler implements IRequestHandler {

    private final IUserManager usermanager;
    private final MatchFinder matcher;
    private final INetwork net;
    private final Scoreboard scoreboard;

    public RequestHandler(INetwork net) {
        this.net = net;
        usermanager = new UserManager();
        scoreboard = new Scoreboard();
        matcher = new MatchFinder(usermanager, net);
    }

    public synchronized void handle(Request request) {
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
            case GET_SCOREBOARD:
            return new GetScoreboardRequest(request)
                .apply(getUser(request), scoreboard, usermanager);
            case FIND_MATCH:
            return new FindMatchRequest(request).apply(matcher);
            case PLAY:
            final OnlineUser user = getUser(request);
            return new PlayRequest(request).apply(user);
            case GET_UPDATE:
            return new GetUpdateRequest(request).apply(getUser(request));
            case MATCH_FINISH:
            return new MatchFinishedRequest().apply(getUser(request), scoreboard);
            case REPLAY:
            return new GetSaveRequest().apply(getUser(request));
            default:
            return null;
        }
    }

    public synchronized void respond(Response response, String auth) {
        net.respond(response.toString(), auth);
    }

    public OnlineUser getUser(Request request) { 
        return usermanager
            .findUserWithAuth(request.getAuth());
    }
}