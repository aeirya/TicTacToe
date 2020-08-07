package xoxo.game;

public class Player {
    private final String name;
    private Sign sign;
    private boolean isHasTurn = false;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Sign sign) {
        this.name = name;
        this.sign = sign;
    }

    public void sign(Block block) {
        block.fill(sign);
        isHasTurn = false;
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

    public void toggleHasTurn() {
        isHasTurn = true;
    }

    public boolean hasTurn() {
        return isHasTurn;
    }
}