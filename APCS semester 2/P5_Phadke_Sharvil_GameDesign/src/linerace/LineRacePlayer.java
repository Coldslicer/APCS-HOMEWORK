package linerace;

import engine.Game;
import engine.Player;

public class LineRacePlayer extends Player {
    private int numTurnsFrozen;
    public LineRacePlayer(Game g, int row, int col) {
        super(g, row, col);
    }

    // We were forced to override this method because it was abstract in our parent.
    // This method either skips a turn if the player is frozen or rolls a 3-sided
    // die and moves that many tiles on the board.
    @Override
    public void takeTurn() {
        if (numTurnsFrozen > 0) {
            System.out.println("Player " + getPlayerNum() + " is in a Pit.");
            numTurnsFrozen--;
        }
        else {
            int randNum = (int)(Math.random() * 3 + 1);
            int newCol = Math.min(game.getBoard().getNumCols() - 1,  getCol() + randNum);
            System.out.println("Player " + getPlayerNum() + " rolled a " + randNum + ".");
            this.moveTo(getRow(), newCol);
        }
    }

    // We were not forced to override this method because it was not abstract.
    // However, we are choosing to override it to change the way it prints
    // information in 1D format rather than 2D.
    @Override
    public void printMoveToMessage(int fromRow, int fromCol, int toRow, int toCol) {
        System.out.println("Player " + getPlayerNum() + " moved from tile " + fromCol +
                        " to tile " + toCol + ".");
    }

    public int getNumTurnsFrozen() {
        return numTurnsFrozen;
    }

    public void setNumTurnsFrozen(int numTurnsFrozen) {
        this.numTurnsFrozen = numTurnsFrozen;
    }
}
