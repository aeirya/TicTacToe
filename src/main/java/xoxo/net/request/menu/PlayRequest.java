package xoxo.net.request.menu;

import xoxo.net.request.Request;
import xoxo.net.response.Response;
import xoxo.server.MatchFinder;

public class PlayRequest extends Request {

    public PlayRequest(Request request) {
        super(request);
    }

    private String getUsername() {
        return body;
    }
    
    public Response apply(MatchFinder matchFinder) {
        return new Response(
            matchFinder.queue(getUsername())
        );
    }
}