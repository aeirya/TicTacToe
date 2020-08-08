package xoxo.client.ui;

import xoxo.client.ui.command.IMenuLauncher;
import xoxo.client.ui.command.LoginCommand;
import xoxo.client.ui.command.SignupCommand;

public class LoginMenu extends Menu {

    public LoginMenu(IMenuLauncher launcher) {
        super(null);
        addCommand(new LoginCommand(launcher));
        addCommand(new SignupCommand());
    }
}