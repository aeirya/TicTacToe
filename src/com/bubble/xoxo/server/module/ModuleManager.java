package com.bubble.xoxo.server.module;

import com.bubble.xoxo.server.clientmanager.ClientManager;
import com.bubble.xoxo.server.remote.RemoteService;

public class ModuleManager implements IModule {
    private final ClientManager clientManager;
    private final RemoteService remoteService;

    private final ModuleLocator moduleLocator;

    public ModuleManager() {
        clientManager = new ClientManager();
        remoteService = new RemoteService();
        moduleLocator = initializeModuleLocator();
    }

    private ModuleLocator initializeModuleLocator() {
        return ModuleLocator
            .getInstance()
            .provideRemoteService(remoteService)
            .provideClientManager(clientManager);
    }

    public void start() {
        remoteService.start();
        clientManager.start();
        remoteService.stop();
    }

    public void stop() {
        moduleLocator.stop();
        clientManager.stop();   
        remoteService.stop();
    }
}