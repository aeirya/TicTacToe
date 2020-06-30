package com.bubble.xoxo.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteServer extends Remote {
    void dispatch(Object event) throws RemoteException;
}