package xoxo.game;

public class Player {
    private Sign sign;
    private final String name;
    
    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Sign sign) {
        this.name = name;
        this.sign = sign;
    }

    public void sign(Block block) {
        block.fill(sign);
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }
}