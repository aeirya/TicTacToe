package xoxo;

import xoxo.client.GameClient;
import xoxo.server.ServerMain;

public class TriTest {
    public static void main(String[] args) throws InterruptedException {
        ServerMain.main(args);
        Thread.sleep(500);
        new TriTest();
    }

    public TriTest() {
        new DummyClient("arya", "1234").run();
        new DummyClient("ali", "a").run();
    }

    public class DummyClient extends GameClient {
        private static final String IP = "localhost";
        private static final int PORT = 8000;

        String username;
        String password;

        public DummyClient(String username, String password) {
            super(IP, PORT);
            this.username = username;
            this.password = password;
        }

        @Override
        public void run() {
            super.run();
            server.login(username, password);
            server.findMatch();
        }
    }
}