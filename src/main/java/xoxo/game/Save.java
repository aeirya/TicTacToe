package xoxo.game;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Save {
    
    private final List<Move> moves;

    Save() {
        moves = new ArrayList<>();
    }

    public Save(String json) {
        final Save save = new Gson().fromJson(json, Save.class);
        this.moves = save.moves;
    }

    void play(Player player, int x, int y) {
        moves.add(new Move(player, x, y));
    }

    public Board getBoard(int turn) {
        final Board board = new Board();
        moves.subList(0, turn).forEach(move -> move.play(board));
        return board;
    }

    private class Move {
        private final Player player;
        private final int x;
        private final int y;

        Move(Player player, int x, int y) {
            this.player = player;
            this.x = x;
            this.y = y;
        }

        void play(Board board) {
            board.play(player, x, y);
        }
    }
}
