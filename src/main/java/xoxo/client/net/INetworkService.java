package xoxo.client.net;

public interface INetworkService {
    void connect();
    void send(byte[] data);
    void send(String data);
    void receive(byte[] data);
}