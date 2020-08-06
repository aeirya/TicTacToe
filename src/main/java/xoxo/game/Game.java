package xoxo.game;

public class Game {
    private final Board board;
    private Player home;
    private Player away;

    public Game() {
        home = null;
        away = null;
        board = new Board();
    }

    public Game(Player p1, Player p2) {
        home = p1;
        away = p2;
        board = new Board();
    }

    public void setHomePlayer(Sign sign) {
        home.setSign(sign);
        away.setSign(sign.flip());
    }

    public void play(Player player, int x, int y) {
        board.play(player, x, y);
    }
}