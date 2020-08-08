
package xoxo.client.ui;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.jakewharton.fliptables.FlipTableConverters;

import xoxo.client.net.ServerAPI;
import xoxo.client.ui.command.FindGameCommand;
import xoxo.client.ui.command.IMenuLauncher;
import xoxo.client.ui.command.LogoutCommand;
import xoxo.net.request.menu.ScoreRow;
import xoxo.net.request.menu.ScorebaordState;

public class MainMenu extends Menu {

    private ScorebaordState scoreboard;

    MainMenu(IMenuLauncher lnchr, ServerAPI api) {
        super(api);
        addCommand(new FindGameCommand(lnchr));
        addCommand(new LogoutCommand(lnchr));
    }
    
    @Override
    protected void update() {
        ScorebaordState update = api.getScoreboard();
        if(scoreboard != null && 
        scoreboard.toString().equals(update.toString())) return;
        this.scoreboard = update;
        isNeedsRefresh = true;
    }

    @Override
    public void print(PrintWriter out) {
        if (scoreboard == null) return;
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