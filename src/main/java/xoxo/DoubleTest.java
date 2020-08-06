package xoxo;

import xoxo.client.ClientMain;
import xoxo.server.ServerMain;

public class DoubleTest {
    public static void main(String[] args) {
        new Thread(() -> ServerMain.main(args)).start();
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClientMain.main(args);
    }
}

