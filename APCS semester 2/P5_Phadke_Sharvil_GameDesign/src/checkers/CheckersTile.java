package checkers;

import engine.Board;
import engine.Tile;

public class CheckersTile extends Tile{
    
    /*
     * 0: empty
     * 1: red, regular
     * 2: red, king
     * -1: black, regular
     * -2: black, king
     */
    private int status;

    public CheckersTile(Board b) {
        super(b);
    }

    public CheckersTile(Board b, int status) {
        super(b);
        this.status = status;
    }

    public boolean isEmpty() {
        return status == 0;
    }

    public boolean isRed() {
        return status > 0;
    }

    public boolean isBlack() {
        return status < 0;
    }
    
    public boolean goingForwards() {
        return status == 1 || status == -2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
