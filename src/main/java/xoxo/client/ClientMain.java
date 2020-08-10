package xoxo.client;

import xoxo.client.ui.CliGraphics;
import xoxo.util.resource.Config;

public class ClientMain {
    public static void main(String[] args) {
        final String ip;
        final int port;
        if (args.length > 0) {
            final Config config = new Config(args[0]);
            ip = config.ip;
            port = config.port;
        } else {
            ip = "localhost";
            port = 8000;
        }
        GameClient client = new GameClient(ip, port);
        client.run();
        new CliGraphics(client.get());
    }
}