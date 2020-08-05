package xoxo.net.request.user;

import com.google.gson.Gson;

import xoxo.net.request.NetRequest;
import xoxo.net.request.Request;

public abstract class UserRequest extends Request {

    public UserRequest(NetRequest type, User user) {
        super(type, user);
    }

    public UserRequest(NetRequest type, String body) {
        super(type, body);
    }

    public User getUser() {
        return new Gson().fromJson(body, User.class);
    }

    public abstract void apply(IUserManager manager);
}