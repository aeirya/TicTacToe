package com.bubble.xoxo.server.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkListener {
    private boolean isRunning;
    private final ServerSocket serverListener;
    private final INetwork server;
    private final int port;

    public NetworkListener(INetwork server, int port) {
        this.server = server;
        this.port = port;
        serverListener = startServerListener();
    }

    private ServerSocket startServerListener() {
        try {
            return new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getLocalAddr() {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e1) {
            address = null;
            e1.printStackTrace();
        }
        if (address != null) return address.getHostAddress();
        else return "";
    }

    private void connectLocal(int port) {
        final String ip = getLocalAddr();
        connect(ip, port);
    }

    private void connect(String ip, int port) {
        try {
            serverListener.bind(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        new Thread(this::run).start();
    }

    private void run() {
        isRunning = true;
        connectLocal(port);
        while(isRunning) {
            try {
                final Socket socket = serverListener.accept();
                final Client client = new Client(socket);
                server.accept(client);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        isRunning = false;
        try {
            serverListener.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}