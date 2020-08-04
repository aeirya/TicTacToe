package xoxo.game;

public class Player {
    private Sign sign;

    public Player(Sign sign) {
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
}