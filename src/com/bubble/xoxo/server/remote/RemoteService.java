package com.bubble.xoxo.server.remote;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.bubble.xoxo.server.module.IModule;

public class RemoteService implements IModule {
    private final int port;
    private Registry registry;

    public RemoteService() {
        this(1099);
    }

    public RemoteService(int port) {
        this.port = port;
    }

    public void start() {
        registry = startRegistry(port);
        startOperation();
    }

    private void startOperation() {
        new Thread(() -> {
            while (true)
                operate();
        }).start();
    }

    private void operate() {
        sleep();
    }

    private void sleep() {
        final int sleepTime = 10;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        stopRegistry();
    }

    private Registry startRegistry(int port) {
        return RegistryInitializer.init(port);
    }

    private void stopRegistry() {
        try {
            for(String serviceName : registry.list()) {
                Remote obj = registry.lookup(serviceName);
                UnicastRemoteObject.unexportObject(obj, true); 
                registry.unbind(serviceName);
            }
        } catch (Exception ex) { 
            // do nothing
        }
    }

    public void registerListener(IRemoteListener listener) {
        try {
            UnicastRemoteObject.exportObject(listener, port);
            registry.bind("serverImpl", listener);
            System.out.println(registry.list()[0]);
        } catch (RemoteException|AlreadyBoundException e) {
            // do nothing
            e.printStackTrace();
            stop();
        }
    }
}