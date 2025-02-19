package engine;

public abstract class Player {
    // Declared as protected to be directly accessible by subclasses
    protected Game game;

    // Declared as private so subclasses are forced to change these
    // using the moveTo(row, col) method.  Remember that moveTo is
    // part of our design and that it automatically notifies a Tile
    // to take action when a Player moves there.    
    private int row, col;

    public Player(Game g) {
        this.game = g;
        this.row = -1;  // We'll say -1 means no row
        this.col = -1;  // We'll say -1 means no col
    }

    public Player(Game g, int row, int col) {
        this.game = g;
        this.row = row;
        this.col = col;
    }

    // Player number is this player's index position in the game's player list
    public int getPlayerNum() {
        return game.getPlayers().indexOf(this);
    }

    // This method should be called/used by your player's takeTurn method.
    // If your player moves, call this method so that the Tile the player is
    // on can decide what to do about it.
    public void moveTo(int row, int col) {
        printMoveToMessage(this.row, this.col, row, col);
        this.row = row;
        this.col = col;
        game.getBoard().getTile(row, col).onMoveTo(this);
    }

    // This method is used by moveTo() to print info whenever the player moves
    public void printMoveToMessage(int fromRow, int fromCol, int toRow, int toCol) {
        System.out.println("Player " + getPlayerNum() + " moved from (" + fromRow + ", " + fromCol +
                        ") to (" + toRow + ", " + toCol + ")");
    }

    // Getters
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Game getGame() {
        return game;
    }

    // abstract method left to subclasses to define
    public abstract void takeTurn();
}
