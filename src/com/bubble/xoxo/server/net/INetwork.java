package com.bubble.xoxo.server.net;

public interface INetwork {
    void start();
    void accept(Client client);
}