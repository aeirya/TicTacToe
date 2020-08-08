package xoxo.test;

import xoxo.client.GameClient;
import xoxo.client.net.ServerAPI;
import xoxo.client.ui.CliGraphics;

public class Client {
    public static void main(String[] args) {
        GameClient client = new GameClient("localhost", 8000);
        client.run();
        final ServerAPI api = client.get();
        new CliGraphics(api);
    }
}