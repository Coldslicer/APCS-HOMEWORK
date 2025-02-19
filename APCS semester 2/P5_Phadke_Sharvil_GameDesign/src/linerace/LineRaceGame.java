package linerace;

import java.util.ArrayList;
import java.util.List;

import engine.Board;
import engine.Game;
import engine.Player;

public class LineRaceGame extends Game {

    public LineRaceGame(Board b) {
        super(b);
    }

    // Creates the initial Players and returns them in a list.
    // This method is used by the Game constructor to initialize
    // the list of players.
    @Override
    public List<Player> createPlayers() {
        LineRacePlayer p1 = new LineRacePlayer(this, 0, 0);
        LineRacePlayer p2 = new LineRacePlayer(this, 0, 0);
        List<Player> list = new ArrayList<Player>();
        list.add(p1);
        list.add(p2);
        return list;
    }

    // The game is over when a player reaches the last tile
    @Override
    public boolean gameIsOver() {
        return getActivePlayer().getCol() >= getBoard().getNumCols() - 1;
    }

    @Override
    public void printRules() {
        System.out.println("In 'LineRaceGame' players roll a 3-sided die to");
        System.out.println("decide how many spaces to move. There are 10 tiles");
        System.out.println("numbered 0 to 9 and there are three types of tiles:");
        System.out.println("  Pit tiles - You lose a turn");
        System.out.println("  Extra turn tiles - You get an extra turn");
        System.out.println("  Normal tiles - Nothing special happens");
        System.out.println("The game runs automatically until a player reaches");
        System.out.println("the last tile.\n");
    }

    // When the game ends, print out why
    @Override
    public void printGameResults() {
        System.out.println("Player " + getActivePlayer().getPlayerNum() + " won!");
    }
}
