package com.bubble.xoxo.server;

import com.bubble.xoxo.server.module.ModuleManager;

public class GameServer implements Runnable {
    
    private final ModuleManager modules;

    public GameServer() {
        modules = new ModuleManager();
    }

    @Override
    public void run() {
        modules.start();
    }
}