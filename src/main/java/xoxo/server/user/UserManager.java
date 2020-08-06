package xoxo.server.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import xoxo.net.request.user.IUserManager;
import xoxo.net.request.user.User;
import xoxo.util.resource.UserLoader;

public class UserManager implements IUserManager {

    private final Map<String, User> users;
    private final List<OnlineUser> onlineUsers;
    private static final String USERS_DIR = "users/";

    public UserManager() {
        users = loadUsers();
        onlineUsers = new ArrayList<>();
    }

    private void createFolders() {
        try {
            Files.createDirectory(Paths.get(USERS_DIR));
        } catch (IOException e) {
            //
        }
    }

    private Map<String, User> loadUsers() {
        createFolders();
        return new UserLoader().loadDir(USERS_DIR);
    } 

    private void createUser(User user) {
        try {
            Files.write(new File(USERS_DIR + user.username).toPath(), new Gson().toJson(user).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        users.put(user.username, user);
    }

    public boolean signup(String username, String password) {
        if (users.containsKey(username)) return false;
        createUser(new User(username, password));
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        if (! users.containsKey(username)) return false;
        if (! authenticate(username, password)) return false;
        if (onlineUsers.stream().anyMatch(user -> user.getUsername().equals(username))) return false;
        onlineUsers.add(new OnlineUser(username));
        Logger.getGlobal().info(() -> username + " logged in");
        return true;
    }

    public boolean delete(String username, String password) {
        if (! users.containsKey(username)) return false;
        if (! authenticate(username, password)) return false;
        try {
            Files.delete(new File(USERS_DIR + username).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        users.remove(username);
        return true;
    }

    public boolean authenticate(String username, String password) {
        if (! users.containsKey(username)) return false;
        return users.get(username).password.equals(password);
    }

    public List<String> getOnlineUsers() {
        return onlineUsers.stream().map(OnlineUser::getUsername).collect(Collectors.toList());
    }
    
    public OnlineUser getOnlineUser(String username) {
        if(isOnline(username)) return onlineUsers.parallelStream().filter(u-> u.getUsername().equals(username)).collect(Collectors.toList()).get(0);
        else return null;
    }

    public boolean isOnline(String username) {
        return getOnlineUsers().contains(username);
    }

    public boolean logout(String username, String password) {
        if (! authenticate(username, password)) return false;
        if (! isOnline(username)) return false; 
        onlineUsers.removeIf(u -> u.getUsername().equals(username));
        return true;
    }
}