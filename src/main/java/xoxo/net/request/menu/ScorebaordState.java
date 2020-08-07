package xoxo.net.request.menu;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import xoxo.server.score.Entry;


public class ScorebaordState {
    
    private List<ScoreRow> scores;
    private Entry me;

    public ScorebaordState(Map<String, Integer> scores, Map<String, Boolean> onlineStatus, Entry me) {
        this.scores = makeScoreboard(scores, onlineStatus);
        this.me = me;
    }

    public ScorebaordState(String json) {
        final ScorebaordState state = new Gson().fromJson(json, ScorebaordState.class);
        this.scores = state.scores;
        this.me = state.me;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    private List<ScoreRow> makeScoreboard(Map<String, Integer> scores, Map<String, Boolean> onlineStatus) {
        return scores
            .keySet()
            .stream()
            .map(user -> new ScoreRow(user, scores.get(user), onlineStatus.getOrDefault(user, false)))
            .collect(Collectors.toList());
    }

    public List<ScoreRow> getOnline() {
        return filterByOnline(true);
    }

    public Entry getMe() {
        return me;
    }

    public List<ScoreRow> getOffline() {
        return filterByOnline(false);
    }

    private List<ScoreRow> filterByOnline(boolean online) {
        return scores
            .stream()
            .filter(user -> user.isOnline() == online)
            .sorted((a,b) -> b.getScore() - a.getScore())
            .collect(Collectors.toList());
    }
}