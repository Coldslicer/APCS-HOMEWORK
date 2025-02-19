package engine;

public class Tile {

    protected Board board;

    public Tile(Board b) {
        board = b;
    }

    public void onMoveTo(Player p) {
        // Does nothing by default
    }
}