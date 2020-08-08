package xoxo.client.ui;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.stream.IntStream;

import com.jakewharton.fliptables.FlipTableConverters;

import xoxo.client.net.ServerAPI;
import xoxo.client.ui.command.PlayCommand;
import xoxo.game.Block;
import xoxo.game.Sign;
import xoxo.net.request.game.BoardState;
import xoxo.net.request.game.GameState;

public class GameMenu extends Menu {
    private GameState state;

    GameMenu(ServerAPI api) {
        super(api);
        addCommand(new PlayCommand());
    }

    @Override
    protected void update() {
        final GameState update = api.getUpdate();
        if (update != null) {
            state = update;
            isNeedsRefresh = true;
        }
    }

    @Override
    public void print(PrintWriter out) {
        if (state == null) return;
        printStatus(out);
        out.println(table(state.board));
        out.flush();
    }

    private String table(BoardState state) {
        return new TableCreator().makeTable(state);
    }

    private void printStatus(PrintWriter out) {
        out.println("player: " + state.player.getName());
        out.println("playing as: " + state.player.getSign().toString());
        out.println("against: " + state.opponent.getName());
        if (state.winner != null) out.println(state.winner + " won!"); 
        else out.println("current turn: " + state.getCurrentTurnPlayer());
        out.println();
    }

    public static class TableCreator {
        public String makeTable(BoardState board) {
            return table(board);
        }

        private String table(BoardState board) {
            final Iterator<Block> blocks = board.getBlocks().iterator();
            final int N = board.getSize();
            return FlipTableConverters.fromObjects(tableHead(N), tableBody(N, blocks));
        }
    
        private String[] tableHead(int n) {
            return IntStream.range(0, n).mapToObj(String::valueOf).toArray(String[]::new);
        }
    
        private String[][] tableBody(int n, Iterator<Block> blocks) {
            final String[][] table = new String[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    table[i][j] = signToStr(blocks.next().getSign());
                }
            }
            return table;
        }
    
        private String signToStr(Sign sgn) {
            if (sgn == null) return "";
            else return sgn.toString();
        }
    } 
}