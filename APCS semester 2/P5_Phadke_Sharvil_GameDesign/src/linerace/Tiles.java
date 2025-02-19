package linerace;

import java.util.function.Function;

import engine.Board;
import engine.Player;
import engine.Tile;

public class Tiles {
    static Function<Board,Tile> pitTileConstuctor = (Board b) -> new Tile(b) {
        @Override
        public void onMoveTo(Player p) {
            // onMoveTo receives a Player parameter, but our player is a subclass of player: LineRacePlayer
            // We need to cast it back into its actual type in order to call LineRacePlayer methods
            LineRacePlayer lp = (LineRacePlayer)p;
            lp.setNumTurnsFrozen(1);
            System.out.println("Player " + p.getPlayerNum() + " fell in a Pit and lost 1 turn.");
        }
    };
    static Function<Board,Tile> extraTurnConstructor = (Board b) -> new Tile(b) {
        @Override
        public void onMoveTo(Player p) {
            System.out.println("Player " + p.getPlayerNum() + " gets an extra turn.");
            // Extra turn happens here
            p.takeTurn();
        }
    };
}
