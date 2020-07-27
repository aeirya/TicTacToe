package com.bubble.xoxo.server.net;

import java.net.Socket;

public class Client {
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }
}