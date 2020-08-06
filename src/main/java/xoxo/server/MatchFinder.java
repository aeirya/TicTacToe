package xoxo.server;

import java.util.ArrayList;
import java.util.List;

import xoxo.server.user.Player;

public class MatchFinder {
    private final List<Player> inQueue;

    public MatchFinder() {
        inQueue = new ArrayList<>();
    }
}
