package xoxo.client;

public class ClientMain {
    public static void main(String[] args) {
        final String ip = "localhost";
        final int port = 8000;
        new GameClient(ip, port).run();
    }
}