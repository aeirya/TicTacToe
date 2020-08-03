package xoxo.net.request.user;

import xoxo.net.request.NetRequest;

public class LoginRequest extends UserRequest {

    public LoginRequest(String username, String password) {
        super(NetRequest.LOGIN, new User(username, password));
    }

    public void apply(IUserManager manager) {
        final User user = getUser();
        final String username = user.username;
        final String password = user.password;
        manager.login(username, password);
    }

}