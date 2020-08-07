
package xoxo.client.ui;

import xoxo.client.ui.command.FindGameCommand;
import xoxo.client.ui.command.IMenuLauncher;

public class MainMenu extends Menu {
    MainMenu(IMenuLauncher lnchr) {
        super();
        addCommand(new FindGameCommand(lnchr));
    }
}