package xoxo.server.user;

public class Player {
    private final String username;
    private final String auth;
    private boolean isInMatch = false;
    private boolean isInQueue = false; 

    public Player(String username) {
        this.username = username;
        auth = null;
    }

    public String getUsername() {
        return username;
    }

    public String getAuth() {
        return "";
    } 
}