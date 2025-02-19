package linerace;

import engine.Board;
import engine.Tile;

public class LineRaceBoard extends Board {
    @Override
    public Tile[][] createBoard() {
        Tile[][] tiles = new Tile[1][10];
        tiles[0][0] = new Tile(this);
        tiles[0][tiles[0].length - 1] = new Tile(this);
        for (int i = 1; i < tiles[0].length - 1; i++) {
            double rand = Math.random();
            Tile tile;
            if (rand < 0.3) tile = Tiles.pitTileConstuctor.apply(this);
            else if (rand < 0.5) tile = Tiles.extraTurnConstructor.apply(this);
            else tile = new Tile(this);
            tiles[0][i] = tile;
        }
        return tiles;
    }
}
