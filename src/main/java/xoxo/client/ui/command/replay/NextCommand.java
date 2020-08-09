package xoxo.client.ui.command.replay;

import xoxo.client.net.ServerAPI;
import xoxo.client.ui.ReplayMenu;

public class NextCommand extends ReplayCommand {

    public NextCommand(ReplayMenu menu) {
        super("next", menu);
    }

    @Override
    public void act(ServerAPI api) {
        menu.next();
    }    
}