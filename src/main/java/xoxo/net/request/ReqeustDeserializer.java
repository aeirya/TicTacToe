package xoxo.net.request;

import com.google.gson.Gson;
import xoxo.util.serialize.ISerializer;

public class ReqeustDeserializer implements ISerializer {

    private final Gson gson;

    public ReqeustDeserializer() {
        gson = new Gson();
    }

    public String serialize(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T deserialize(String json, Class<T> type) {
        return gson.fromJson(json, type);

    }
    
    public IEvent deserialize(String json, NetRequest type) {
        return deserialize(json, findClass(type));
    }

    private Class<? extends IEvent> findClass(NetRequest type) {
        /*
        switch(type) {
            case LOGIN:
            // return LoginRequest.class;
            return null;
            default:
            return null;
        }
        */
        return null;
    }
}