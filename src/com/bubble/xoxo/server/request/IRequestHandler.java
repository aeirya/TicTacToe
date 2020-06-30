package com.bubble.xoxo.server.request;

public interface IRequestHandler {
    IResponse handle(IRequest request);
}