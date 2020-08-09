package xoxo.game;

import java.util.logging.Logger;

import com.google.gson.Gson;

import xoxo.net.request.game.GameState;

public class Game {
    private final Board board;
    private final Player home;
    private final Player away;
    private final Save save;
    
    private String winner = null;
    private boolean isUpdated = false;

    public Game(Player p1, Player p2) {
        home = p1;
        away = p2;
        board = new Board();
        save = new Save();
    }

    public void setHomePlayer(Sign sign) {
        home.setSign(sign);
        home.toggleHasTurn();
        away.setSign(sign.flip());
    }

    public boolean play(Player player, int x, int y) {
        Logger.getGlobal().info(() -> player.getName() +  " playing " + x + " ," + y);
        if(player.hasTurn() && board.hasEmpty(x, y) && winner == null) {
            board.play(player, x, y);
            save.play(player, x, y);
            getOpponent(player).toggleHasTurn();
            if(board.checkWin(x, y, player)) {
                win(player);
            }
            isUpdated = false;
            return true;
        }
        else return false;
    }

    private void win(Player player) {
        Logger.getGlobal().info(
            () -> "Player " + player.getSign().toString() + " won");
            winner = player.getName();
    }

    public boolean isToBeUpdated() {
        if (isUpdated) return false;
        else {
            isUpdated = true;
            return true;
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public Player getPlayer(String name) {
        if (home.getName().equals(name)) return home;
        if (away.getName().equals(name)) return away;
        return null;
    }

    public Player getOpponent(Player player) {
        return home.getName().equals(player.getName()) ? away : home;
    }

    public GameState getState(Player player) {
        return new GameState(player, getOpponent(player), winner, board.getState());
    }

    public boolean hasWon(String player) {
        return winner.equals(player);
    }

    public boolean isFinished() {
        return winner != null;
    }

    public Save getSave() {
        return save;
    }
}