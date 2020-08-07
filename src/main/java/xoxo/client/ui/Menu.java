package xoxo.client.ui;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Menu {

    private final Map<Integer, ICommand> map;

    public Menu() {
        map = new HashMap<>();
    }

    protected void addCommand(ICommand command) {
        map.put(map.size(), command);
    }

    public ICommand getCommand(int index) {
        return map.get(index);
    }

    public boolean hasCommand(int index) {
        return map.containsKey(index);
    }

    public List<ICommand> getAvailableCommands() {
        return map.keySet().stream().sorted().map(map::get).collect(Collectors.toList());
    }

    public void print(PrintWriter out) {
        IntStream.range(0, map.size()).forEach(i -> map.get(i).print(i, out));
    }
}