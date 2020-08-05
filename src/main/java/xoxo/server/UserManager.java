package xoxo.server;

import xoxo.net.request.user.IUserManager;

public class UserManager implements IUserManager {

    public UserManager() {
        //
    }

    @Override
    public boolean login(String username, String password) {
        System.out.println("logging in with " + username + " ," + password);
        return true;
    }

}