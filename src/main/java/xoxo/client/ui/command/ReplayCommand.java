
package xoxo.client.ui.command;

import java.io.PrintWriter;
import java.util.Scanner;

import xoxo.client.net.ServerAPI;
import xoxo.client.ui.ICommand;

public class ReplayCommand extends Command {

    private final IMenuLauncher lnchr;

    public ReplayCommand(IMenuLauncher lnchr) {
        super("replay game");
        this.lnchr = lnchr;
    }

    @Override
    public ICommand takeArgs(Scanner in, PrintWriter out) {
        return this;
    }

    @Override
    public void act(ServerAPI api) {
        lnchr.launch(MenuType.REPLAY);
    }
    
}