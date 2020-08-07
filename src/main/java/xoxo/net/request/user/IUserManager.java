package xoxo.net.request.user;

import xoxo.server.user.OnlineUser;

public interface IUserManager {
    boolean login(String username, String password);
    boolean signup(String username, String password);
    boolean delete(String username, String password);
    boolean authenticate(String username, String password);
    boolean logout(String username, String password);
    OnlineUser getOnlineUser(String username);
    OnlineUser findUserWithAuth(String auth);
}