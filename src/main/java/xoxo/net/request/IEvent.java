package xoxo.net.request;

import xoxo.util.serialize.ISerializable;

public interface IEvent extends ISerializable {
    void apply(); 
    
}