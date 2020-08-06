package xoxo.game;

public class Game {
    private final Board board;
    private final Player home;
    private final Player away;

    public Game() {
        board = null;
        home = null;
        away = null;
    }

    private void setHomePlayer(Sign sign) {
        home.setSign(sign);
        away.setSign(sign.flip());
    }

    public void play(Player player, int x, int y) {
        board.play(player, x, y);
    }
}