package xoxo.client.ui.command;

import java.io.PrintWriter;
import java.util.Scanner;

import xoxo.client.net.ServerAPI;
import xoxo.client.ui.ICommand;

public class FindGameCommand extends Command {

    private final IMenuLauncher launcher;

    public FindGameCommand(IMenuLauncher launcher) {
        super("start playing");
        this.launcher = launcher;
    }

    @Override
    public ICommand takeArgs(Scanner in, PrintWriter out) {
        return this;
    }

    @Override
    public void act(ServerAPI api) {
        api.findMatch();
        launcher.launch(MenuType.GAME);
    }
    
}