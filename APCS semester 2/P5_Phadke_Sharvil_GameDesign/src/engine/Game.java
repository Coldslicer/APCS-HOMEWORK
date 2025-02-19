package engine;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    private List<Player> players;   // Keeps track of all the players
    private Board board;            // Keeps track of the board
    private Player activePlayer;    // Keeps track of whose turn it is

    public Game(Board b) {
        players = createPlayers();
        board = b;
    }

    public void startGame() {
        printRules();
        playGame();
    }

    private void playGame() {
        do {
            activePlayer = getNextPlayer();
            System.out.println("It's Player " + activePlayer.getPlayerNum() + "'s turn.");
            activePlayer.takeTurn();
            System.out.println("Turn finished.\n");
        } while (!gameIsOver());
        printGameResults();
    }

    public Player getActivePlayer() {
        return activePlayer;
    }
    
    public Board getBoard() {
        return board;
    }

    // Returns a COPY of the player list so that the original cannot be messed with.
    public List<Player> getPlayers() {
        return new ArrayList<Player>(players);
    }

    // Default behavior: returns the next player in the player list, wrapping around to the first player at the end
    // You can override this method in your subclass to change the behavior
    public Player getNextPlayer() {
        return activePlayer == null ? players.get(0) : players.get((activePlayer.getPlayerNum() + 1) % players.size());
    }
    
    public abstract List<Player> createPlayers();
    public abstract boolean gameIsOver();
    public abstract void printRules();
    public abstract void printGameResults();
}
