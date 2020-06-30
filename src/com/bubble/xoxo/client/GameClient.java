package com.bubble.xoxo.client;

import com.bubble.xoxo.client.remote.RemoteService;

public class GameClient implements Runnable {
    
    private final RemoteService remoteService;

    GameClient() {
        remoteService = new RemoteService();
    }

    public void run() {
        remoteService.start();
    }
}