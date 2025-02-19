package checkers;

import engine.Board;
import engine.Tile;

public class CheckersBoard extends Board {

    public static final int[][] RED_POSITIONS = {
        {0,1},
        {0,3},
        {0,5},
        {0,7},
        {1,0},
        {1,2},
        {1,4},
        {1,6},
        {2,1},
        {2,3},
        {2,5},
        {2,7},
    };

    public static final int[][] BLACK_POSITIONS = {
        {0,1},
        {0,3},
        {0,5},
        {0,7},
        {1,0},
        {1,2},
        {1,4},
        {1,6},
        {2,1},
        {2,3},
        {2,5},
        {2,7},
    };


    @Override
    public Tile[][] createBoard() {
        Tile[][] board = new Tile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board[y][x] = new CheckersTile(this, 0);
            }
        }

        for (int[] pos : RED_POSITIONS) {
            board[pos[0]][pos[1]] = new CheckersTile(this, 1);
        }

        for (int[] pos : BLACK_POSITIONS) {
            board[board.length - 1 - pos[0]][board[board.length - 1 - pos[0]].length - 1 - pos[1]] = new CheckersTile(this, -1);
        }

        return board;
    }

    public boolean tryMove(int y, int x, boolean right) {
        CheckersTile cur = (CheckersTile) getTile(y, x), next;
        if (cur == null || cur.getStatus() == 0) {
            return false;
        }
        int dy = cur.goingForwards() ? 1 : -1;
        int dx = right ? 1 : -1;
        next = (CheckersTile) getTile(y+dy, x+dx);
        if (next != null && next.getStatus() == 0) {
            next.setStatus(cur.getStatus());
            if (next.goingForwards() && y+dy == getNumRows() - 1 || !next.goingForwards() && y + dy == 0) {
                if (next.getStatus() % 2 == 0) next.setStatus(next.getStatus() / 2);
                else next.setStatus(next.getStatus() * 2);
            }
            cur.setStatus(0);
            return true;
        }
        if (next != null && (next.getStatus() < 0 && cur.getStatus() > 0 || next.getStatus() > 0 && cur.getStatus() < 0)) {
            CheckersTile landing = (CheckersTile) getTile(y+dy*2, x+dx*2);
            if (landing == null || landing.getStatus() != 0) {
                return false;
            }
            next.setStatus(0);
            landing.setStatus(cur.getStatus());
            if (landing.goingForwards() && y+2*dx == getNumRows() - 1 || !next.goingForwards() && y+2*dx == 0) {
                if (next.getStatus() % 2 == 0) next.setStatus(next.getStatus() / 2);
                else next.setStatus(next.getStatus() * 2);
            }
            cur.setStatus(0);
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\t0\t1\t2\t3\t4\t5\t6\t7\n\n");
        int i = 0;
        for (Tile[] row : board) {
            out.append(i++);
            out.append('\t');
            for (Tile tile : row) {
                switch (((CheckersTile)tile).getStatus()) {
                    case 2:
                        out.append('B');
                        break;
                    case 1:
                        out.append('b');
                        break;
                    case 0:
                        out.append(' ');
                        break;
                    case -1:
                        out.append('r');
                        break;
                    case -2:
                        out.append('R');
                        break;
                }
                out.append('\t');
            }
            out.append("\n\n");
        }
        return out.toString();
    }
}
