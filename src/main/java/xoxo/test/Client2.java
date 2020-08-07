package xoxo.test;

public class Client2 extends DummyClient {

    public Client2() {
        super("ali", "a");
    }

    public static void main(String[] args) {
        new Client2().run();
    }
    
}