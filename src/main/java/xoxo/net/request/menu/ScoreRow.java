package xoxo.net.request.menu;

public class ScoreRow {
    private final String user;
    private final boolean isOnline;
    private final int score;

    ScoreRow(String user, int score, boolean isOnline) {
        this.user = user;
        this.score = score;
        this.isOnline = isOnline;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public int getScore() {
        return score;
    }

    public String getUser() {
        return user;
    }
}