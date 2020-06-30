package com.bubble.xoxo.server.clientmanager;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import com.bubble.xoxo.server.remote.IRemoteListener;
import com.bubble.xoxo.server.remote.IRemoteServer;

public class ServerImpl implements IRemoteServer, IRemoteListener {

    public ServerImpl() {
        //
    }

    @Override
    public void dispatch(Object event) throws RemoteException {
        System.out.println("dispatched " + event.toString());
    }

    @Override
    public void bind(Registry registry) throws RemoteException {
        final String name = "serverImpl";
        try {
            registry.bind(name, this);
        } catch(AlreadyBoundException ex) {
            registry.rebind(name, this);
        }
    }
}