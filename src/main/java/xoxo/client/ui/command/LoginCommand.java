package xoxo.client.ui.command;

import xoxo.client.net.ServerAPI;

public class LoginCommand extends UserCommand {

    private final IMenuLauncher launcher;

    public LoginCommand(IMenuLauncher launcher) {
        super("login");
        this.launcher = launcher;
    }

    @Override
    public void act(ServerAPI api) {
        api.login(username, password);
        launcher.launch(MenuType.MAIN);
    }
}