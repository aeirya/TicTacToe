package xoxo.game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Board {
    
    private static final int N = 7;
    private static final int WINNING_LIMIT = 4;

    private final List<Block> blocks;

    public Board() {
        blocks = new ArrayList<>();
        indexBlocks();
        startGame();
    }

    private void indexBlocks() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks.add(new Block(i, j));
            }
        }
    }

    public void startGame() {
        clearBoard();
    }

    private void clearBoard() {
        blocks.forEach(Block::clear);
    }

    private Block find(int x, int y) {
        return blocks.get(N * x + y);
    }

    public void play(Player player, int x, int y) {
        player.sign(find(x,y));
        if(checkWin(x, y, player)) {
            win(player);
        }
    }

    private void win(Player player) {
        System.out.println("Player " + player.getSign().toString() + " won");
    }

    /**
     * x, y are coordinate of the last played block
     */
    public boolean checkWin(int x, int y, Player player) {
        final Sign sign = player.getSign();
        if(checkLine(getVLine(y), sign)) return true;
        if(checkLine(getHLine(x), sign)) return true;
        return (checkLine(getDLine(x, y), sign));
    }

    private List<Block> getVLine(int y) {
        return filter(b -> b.y == y);
    }

    private List<Block> getHLine(int x) {
        return filter(b -> b.x == x);
    }

    /**
     * returns a diagonal line of blocks, passing the block with coordinates x, y
     */
    private List<Block> getDLine(int x, int y) {
        return filter(b -> (b.y - b.x) == (y-x));
    }
    
    private List<Block> filter(Predicate<? super Block> predicate) {
        return blocks.stream().filter(predicate).collect(Collectors.toList());
    }

    /** returns subs of length WINNING_LIMIT */
    private List<List<Block>> subs(List<Block> list) {
        final List<List<Block>> subs = new ArrayList<>();
        for (int i = 0; i < list.size() - WINNING_LIMIT + 1; i++) {
            subs.add(list.subList(i, i + WINNING_LIMIT));
        }
        return subs;
    }

    private boolean allMatch(List<Block> blocks, Sign sign) {
        return blocks.stream().parallel().allMatch(b -> b.matches(sign));
    }

    private boolean checkLine(List<Block> line, Sign sign) {
        return subs(line).stream().anyMatch(sub -> allMatch(sub, sign));
    }
}