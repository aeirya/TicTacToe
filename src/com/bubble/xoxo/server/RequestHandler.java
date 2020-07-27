package com.bubble.xoxo.server;

public class RequestHandler implements IRequestHandler {

    @Override
    public void handle(String request) {
        System.out.println("received request: " + request);
    }
    

}