package com.bubble.xoxo.server.module;

import com.bubble.xoxo.server.clientmanager.ClientManager;
import com.bubble.xoxo.server.remote.RemoteService;

public class ModuleLocator implements IModule {
    private ClientManager clientManager;
    private RemoteService remoteService;

    private static class InstanceHolder {
        private static final ModuleLocator instance = new ModuleLocator();
    }

    static ModuleLocator getInstance() {
        return InstanceHolder.instance;
    }

    ModuleLocator provideRemoteService(RemoteService remoteService) {
        this.remoteService = remoteService;
        return this;
    }

    public static RemoteService getRemoteService() {
        return getInstance().remoteService;
    } 

    ModuleLocator provideClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
        return this;
    }

    public static ClientManager getClientManager() {
        return getInstance().clientManager;
    }

    public void start() {
        // do nothing
    }

    public void stop() {
        clientManager = null;
        remoteService = null;
    }
}