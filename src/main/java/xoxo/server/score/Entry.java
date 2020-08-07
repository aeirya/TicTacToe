package xoxo.server.score;

public class Entry {
    private int wins;
    private int lost;

    public int getScore() {
        return wins - lost;
    }

    public void win() {
        wins += 1;
    }

    public void lose() {
        lost += 1;
    }

    public void add(MatchResult result) {
        result.apply(this);
    }
}