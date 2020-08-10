package xoxo.server;

import xoxo.util.resource.Config;

public class ServerMain {
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
        new GameServer(ip, port).run();
    }
}