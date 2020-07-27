package com.bubble.xoxo.server.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionDispatcher {

    private final Socket socket;

    public ConnectionDispatcher(Socket socket) {
        this.socket = socket;
    }

    public void connect() {
        //
    }

    private OutputStream getOutput() throws IOException {
        return socket.getOutputStream();
    }

    public void send(byte[] data) {
        final OutputStream out;
        try {
            out = getOutput();
            write(out, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(OutputStream out, byte[] data) throws IOException {
        out.write(data);
        out.flush();
        out.close();
    }
}