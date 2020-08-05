package xoxo.util.serialize;

public interface ISerializer {

    String serialize(Object object);

    <T> T deserialize (String json, Class<T> type);
}