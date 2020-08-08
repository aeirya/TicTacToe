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
    private final ServerAPI api;
    private GameState state;

    GameMenu(ServerAPI api) {
        super();
        this.api = api;
        addCommand(new PlayCommand());
        run();
    }

    private void run() {
        new Thread(() -> {
            while (true) {
                update();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void update() {
        final GameState update = api.getUpdate();
        if (update != null) {
            state = update;
        }
    }

    @Override
    public void print(PrintWriter out) {
        if (state == null) return;
        printStatus(out);
        out.println(table(state.board));
        out.flush();
    }

    private void printStatus(PrintWriter out) {
        out.println(state.player.getName());
        out.println("playing as: " + state.player.getSign().toString());
        out.println();
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

    @Override
    public boolean needsRefresh() {
        return !state.player.hasTurn() || super.needsRefresh();
    }
}