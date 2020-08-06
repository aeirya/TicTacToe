package xoxo.server;

import java.util.LinkedList;

import xoxo.game.Game;
import xoxo.game.Player;
import xoxo.net.request.user.IUserManager;
import xoxo.server.user.OnlineUser;

public class MatchFinder {
    private final LinkedList<OnlineUser> inQueue;
    private final IUserManager usermanager;
    private boolean isAlive = true;

    public MatchFinder(IUserManager usermanager) {
        inQueue = new LinkedList<>();
        this.usermanager = usermanager;
        new Thread(this::run).start();
    }

    public void run() {
        while (isAlive) {
            while (inQueue.size() > 1) {
                match();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean queue(String username) {
        final OnlineUser player = usermanager.getOnlineUser(username);
        if (player == null) return false;
        System.out.println("queueing for " + username);
        inQueue.add(player);
        player.setInMatch(true);
        return true;
    }

    public void match() {
        OnlineUser u1 = pick();
        OnlineUser u2 = pick();
        Player p1 = toPlayer(u1);
        Player p2 = toPlayer(u2);
        Game game = new Game(p1, p2);
        u1.startMatch(game, p1);
        u2.startMatch(game, p2);
        System.out.println("match started");
    }

    public OnlineUser pick() {
        return inQueue.removeFirst();
    }

    public Player toPlayer(OnlineUser user) {
        return new Player(user.getUsername());
    }
}
