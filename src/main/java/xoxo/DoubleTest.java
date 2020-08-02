package xoxo;

import xoxo.client.ClientMain;
import xoxo.server.ServerMain;

public class DoubleTest {
    public static void main(String[] args) {
        new Thread(
            () -> ServerMain.main(args)
        ).start();
        new Thread(
            () -> ClientMain.main(args)
        ).start();
    }
}