package com.bubble.xoxo.server.clientmanager;

import com.bubble.xoxo.server.module.IModule;
import com.bubble.xoxo.server.module.ModuleLocator;

public class ClientManager implements IModule {

    public static final ServerImpl serverListener = new ServerImpl();

    public ClientManager() {
        //
    }
    
    @Override
    public void start() {
        // TODO Auto-generated method stub
        ModuleLocator
            .getRemoteService()
            .registerListener(
                serverListener
            );
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }
}