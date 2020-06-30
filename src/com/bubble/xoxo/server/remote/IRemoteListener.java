package com.bubble.xoxo.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public interface IRemoteListener extends Remote {
    void bind(Registry registry) throws RemoteException;
}