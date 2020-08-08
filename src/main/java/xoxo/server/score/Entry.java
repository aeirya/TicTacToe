package xoxo.server.score;

public class Entry {
    private int wins;
    private int lost;

    Entry () {
        wins = 0;
        lost = 0;
    }

    public int getScore() {
        return wins > lost ? wins - lost : 0;
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