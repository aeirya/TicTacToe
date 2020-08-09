package xoxo.client.ui.command;

import java.io.PrintWriter;
import java.util.Scanner;

import xoxo.client.net.ServerAPI;
import xoxo.client.ui.ICommand;

public class BackToMainMenuCommand extends Command {

    private final IMenuLauncher lnchr;

    public BackToMainMenuCommand(IMenuLauncher lnchr) {
        super("quit");
        this.lnchr = lnchr;
    }

    @Override
    public void act(ServerAPI api) {
        lnchr.launch(MenuType.MAIN);
        api.finishMatch();
    }

    @Override
    public ICommand takeArgs(Scanner in, PrintWriter out) {
        return this;
    }
    
}