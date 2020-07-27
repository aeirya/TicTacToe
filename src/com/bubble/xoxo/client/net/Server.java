package com.bubble.xoxo.client.net;

import java.net.Socket;

public class Server {
    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }
}