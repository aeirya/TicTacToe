package xoxo.game;

import java.util.logging.Logger;

import com.google.gson.Gson;

public class Game {
    private final Board board;
    private Player home;
    private Player away;
    private String winner = null;

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
        Logger.getGlobal().info(() -> player.getName() +  " playing " + x + " ," + y);
        board.play(player, x, y);
        if(board.checkWin(x, y, player)) {
            win(player);
        }
    }

    private void win(Player player) {
        Logger.getGlobal().info(
            ()-> "Player " + player.getSign().toString() + " won");
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}