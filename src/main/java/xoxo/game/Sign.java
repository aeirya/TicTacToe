package xoxo.game;

public enum Sign {
    X, O;

    Sign flip() {
        if(this.equals(Sign.X)) return Sign.O;
        else return Sign.X;
    }
}