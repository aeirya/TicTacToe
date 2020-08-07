package xoxo.game;

public class Block {
    public final int x;
    public final int y;
    private Sign sign;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        sign = null;
    }

    public void fill(Sign sign) {
        this.sign = sign;
    }

    public void clear() {
        this.sign = null;
    }

    public boolean matches(Sign sign) {
        if (this.sign == null) return false;
        return this.sign.equals(sign);
    }

    public boolean isFilled() {
        return (sign != null);
    }

    public Sign getSign() {
        return sign;
    }
}