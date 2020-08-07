package xoxo.client.ui;

import xoxo.client.ui.command.PlayCommand;

public class GameMenu extends Menu {
    GameMenu() {
        super();
        addCommand(new PlayCommand());
    }
}