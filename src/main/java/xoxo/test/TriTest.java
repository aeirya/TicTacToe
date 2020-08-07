package xoxo.test;

import xoxo.server.ServerMain;

public class TriTest {
    public static void main(String[] args) {
        new Thread(
        () -> ServerMain.main(args)
        ).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        new Thread(() -> new Client1().run()).start();
        new Client2().run();
    }
}