package xoxo.util.resource;

import com.google.gson.Gson;

public class Config {
    public final String ip;
    public final int port;

    public Config(String arg) {
        final String text = new FileLoader().loadFile(arg);
        final Config conf = new Gson().fromJson(text, Config.class);
        this.ip = conf.ip;
        this.port = conf.port;
    }
}