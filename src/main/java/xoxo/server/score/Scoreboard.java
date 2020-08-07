
package xoxo.server.score;

import java.util.Map;

import com.google.gson.Gson;

import xoxo.util.resource.FileLoader;
import xoxo.util.resource.IResourceLoader;

public class Scoreboard {
    private final Map<String, Entry> map;
    private static final String SCORE_DIR = "scores/";

    public Scoreboard() {
        map = loadEntries();
    }

    private Map<String, Entry> loadEntries() {
        IResourceLoader<Entry> loader = 
            path -> new Gson().fromJson(
                new FileLoader().loadFile(path), 
                Entry.class);
        return loader.loadDir(SCORE_DIR);
    }

    public void addRecord(String user, MatchResult result) {
        map.get(user).add(result);
    }
}