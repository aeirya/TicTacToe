package com.bubble.xoxo.server.remote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryInitializer {
    public static Registry init(int port) {
        return new RegistryInitializer().startRegistry(port);
    }

    private Registry startRegistry(int port) {
        final Registry reg = getRegistry(port);
        if (reg != null) return reg;
        else return createRegistry(port); 
    }

    private Registry createRegistry(int port) {
        try {
            return LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            return null;
        }
    }

    private Registry getRegistry(int port) {
        try {
            return LocateRegistry.getRegistry(port);
        } catch (RemoteException e) {
            return null;
        }
    }
}