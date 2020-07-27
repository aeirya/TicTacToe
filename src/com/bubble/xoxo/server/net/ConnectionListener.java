package com.bubble.xoxo.server.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ConnectionListener {
    
    private final Socket socket;
    private final NetworkConnection connection;
    private boolean isRunning;

    public ConnectionListener(NetworkConnection connection ,Socket socket) {
        this.connection = connection;
        this.socket = socket;
    }

    public void listen() {
        new Thread(this::run).start();
    }

    private void run() {
        isRunning = true;
        final InputStream in = getInStream();
        if (in == null) return;
        while (isRunning) {
            final byte[] data = read(in);
            connection.receive(data);
        }
    }

    private InputStream getInStream() {
        try {
            return socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] read(InputStream in) {
        final int len = 0x2000;
        final byte[] data = new byte[len];
        try {
            in.read(data, 0, len);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public void terminate() {
        isRunning = false;
        try {
            final InputStream in = getInStream();
            if (in != null) in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}