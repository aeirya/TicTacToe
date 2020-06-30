package com.bubble.xoxo.client.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.bubble.xoxo.client.module.IModule;
import com.bubble.xoxo.server.remote.IRemoteServer;

public class RemoteService implements IModule {

    @Override
    public void start() {
        IRemoteServer server;
        try {
            server = (IRemoteServer) Naming.lookup("serverImpl");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            server = null;
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            server.dispatch(new Object() {
                @Override
                public String toString() {
                    return "fick";
                }
            });
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }
    
}