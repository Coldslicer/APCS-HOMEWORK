package engine;
public abstract class Board {
    protected Tile[][] board;

    public Board() {
        board = createBoard();
    }
    
    public abstract Tile[][] createBoard();

    // Gets the Tile at (row, col) in this board
    public Tile getTile(int row, int col) {
        if (row < board.length && row >= 0 && col < board[row].length && col >= 0) {
            return board[row][col];
        }
        return null;
    }

    public void setTile(int row, int col, Tile t) {
        if (row < board.length && row >= 0 && col < board[row].length && col >= 0) {
            board[row][col] = t;
        }
    }

    public int getNumRows() {
        return board.length;
    }

    public int getNumCols() {
        return board[0].length;
    }
}