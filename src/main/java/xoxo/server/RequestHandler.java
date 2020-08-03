package xoxo.server;

import xoxo.net.request.Request;
import xoxo.net.request.user.IUserManager;

public class RequestHandler implements IRequestHandler {

    private final IUserManager usermanager;

    public RequestHandler() {
        usermanager = null;
    }

    public void handle(Request request) {
        System.out.println("handing request with type: " + request.type);
    }
}