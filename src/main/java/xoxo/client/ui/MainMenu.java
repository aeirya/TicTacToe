
package xoxo.client.ui;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.jakewharton.fliptables.FlipTableConverters;

import xoxo.client.net.ServerAPI;
import xoxo.client.ui.command.FindGameCommand;
import xoxo.client.ui.command.IMenuLauncher;
import xoxo.net.request.menu.ScoreRow;
import xoxo.net.request.menu.ScorebaordState;

public class MainMenu extends Menu {

    private final ServerAPI api;

    MainMenu(IMenuLauncher lnchr, ServerAPI api) {
        super();
        this.api = api;
        addCommand(new FindGameCommand(lnchr));
    }

    @Override
    public void print(PrintWriter out) {
        ScorebaordState scoreboard = api.getScoreboard();
        new ScoreboardPrinter(scoreboard, out);
    }

    private class ScoreboardPrinter {
        ScoreboardPrinter(ScorebaordState state, PrintWriter out) {
            final List<ScoreRow> rows = new ArrayList<>();
            rows.addAll(state.getOnline());
            rows.addAll(state.getOffline());
            out.println(FlipTableConverters.fromIterable(rows, ScoreRow.class));
            out.flush();
        }
    }
}