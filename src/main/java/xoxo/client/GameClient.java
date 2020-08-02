package xoxo.client;

import xoxo.client.net.INetwork;
import xoxo.client.net.Network;

public class GameClient {

    private final INetwork network;

    public GameClient(String ip, int port) {
        network = new Network(ip, port);
    }

    public void run() {
        network.connect();
        test();
    }

    private void test() {
        final String text = "this is a test";
        network.send(text.getBytes());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final String text2 = "hmmm, another one";
        network.send(text2.getBytes());
        network.send(text2.getBytes());
        network.send(text2.getBytes());
        network.send(text2.getBytes());
        network.send(text2.getBytes());
        network.send(text2.getBytes());
    }
}
