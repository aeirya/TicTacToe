package xoxo.client.ui;

import java.io.PrintWriter;
import java.util.Scanner;

import xoxo.client.net.ServerAPI;
import xoxo.client.ui.command.IMenuLauncher;
import xoxo.client.ui.command.MenuType;

public class CliGraphics implements IMenuLauncher {
    private final ServerAPI net;
    private final Scanner in;
    private final PrintWriter out;
    private Menu menu;

    public CliGraphics(ServerAPI net) {
        this.net = net;
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
        launch(MenuType.LOGIN);
        run();
    }

    private void run() {
        new Thread(() -> {
            clear();
            credits();
            help();
            while (true) {
                update();
            }
        }).start();
    }

    private void credits() {
        out.println("XO - made by: aeirya");
        out.flush();
    }

    private void update() {
        try {
            final int cmd = in.nextInt();
            in.nextLine();
            if (menu.hasCommand(cmd)) {
                menu.getCommand(cmd).takeArgs(in, out).act(net);
            } else {
                clear();
                help();
            }
            out.flush();
        } catch (Exception e) {
            clear();
            help();
            in.reset();
            in.nextLine();
        }
    }

    private void clear() {
        out.print("\033[H\033[2J");
        out.flush();
    }

    private void printLine() {
        out.println("---------------------------");
    }

    private void help() {
        printLine();
        menu.print(out);
        printLine();
        out.flush();
    }

    public static void main(String[] args) {
        new CliGraphics(null);
    }

    @Override
    public void launch(MenuType type) {
        switch(type) {
            case LOGIN:
            menu = new LoginMenu(this);
            break;
            default:
            case MAIN:
            menu = new MainMenu(this);
            break;
            case GAME:
            menu = new GameMenu();
        }
        help();
    }
}