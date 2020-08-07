package xoxo.test;

public class Client1 extends DummyClient {

    public Client1() {
        super("arya", "1234");
    }
    
    public static void main(String[] args) {
        new Client1().run();
    }
}