package xoxo.client.net;

public interface INetwork {
    void connect();
    void send(byte[] data);
}