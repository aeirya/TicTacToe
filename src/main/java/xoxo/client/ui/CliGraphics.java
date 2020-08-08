package xoxo.client.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import xoxo.client.net.ServerAPI;
import xoxo.client.ui.command.IMenuLauncher;
import xoxo.client.ui.command.MenuType;

public class CliGraphics implements IMenuLauncher {
    private final ServerAPI net;
    private final Scanner in;
    private final PrintWriter out;
    private Menu menu;
    private boolean isGettingInput = false;
    int a = 0;

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
            // credits();
            updater();
            while (true) {
                getInput();
                refresh();
            }
        }).start();
    }

    private void credits() {
        out.println("write something to continue");
        out.flush();
        in.nextLine();
    }

    private void updater() {
        new Thread(
            () -> {
                while(true) {
                    if ( !isGettingInput && menu.needsRefresh()) {
                        refresh();
                    }
                    sleep();
                }
            }
        ).start();
    }

    private void update() {
        try {
            refresh();
            System.out.println("so refreshing!"  + a++);
            if (in.hasNextLine()) {
                System.out.println("getting the input");
                getInput();
            } else {
                sleep();
            }
        } catch (Exception e) {
            // clear();
            in.reset();
            in.nextLine();
        }
    }

    private void sleep() {
        menu.sleep();
    }

    private void getInput() {
        final int cmd = in.nextInt();
        in.nextLine();
        isGettingInput = true;
        if (menu.hasCommand(cmd)) {
            menu.getCommand(cmd).takeArgs(in, out).act(net);
            out.flush();
        } else {
            clear();
        }
        isGettingInput = false;
    }

    private void refresh() {
        System.out.println("refreshinggggg " + a++);
        in.reset();
        clear();
        menu.print(out);
        help();
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
        menu.printCommands(out);
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
            System.out.println("switching to main");
            menu = new MainMenu(this, net);
            break;
            case GAME:
            menu = new GameMenu(net);
        }
        clear();
    }
}