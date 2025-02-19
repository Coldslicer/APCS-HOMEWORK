/*
 * Sharvil Phadke P5
 * Tue Feb 11
 * Working
 * HW Question:
 *      ArrayLists can be thought of to have variable length, but in reality
 *      they are still just arrays. What other information does an ArrayList
 *      need to store in order to function like a List?
 * 
 *      Answer: the current number of items in the list is required, 
 *      possibly also the size of the next array if the array needs to be remade
 */

public class P5_Phadke_Sharvil_KnightsTour1 {
    public static void main() {
        P5_Phadke_Sharvil_KnightsTour1 max = null, tour = new P5_Phadke_Sharvil_KnightsTour1();
        for (int i = 0; i < 1000000; i++) {
            while (!tour.advance());
            if (max == null || tour.size() < max.size()) max = tour;
            tour = new P5_Phadke_Sharvil_KnightsTour1();
        }
        System.out.println(max);

        int numChecked = 0;
        while (max.size() != 64) {
            while (!tour.advance());
            if (tour.size() > max.size()) max = tour;
            numChecked++;
            tour = new P5_Phadke_Sharvil_KnightsTour1();
        }
        System.out.println(max);
        System.out.printf("It took %d tours to find a full length tour\n\n",numChecked);
    }

    private static final byte[][] KNIGHT_MOVES = {{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};

    private byte[][] board = new byte[8][8];
    private byte[] loc = {0,0};
    private byte step = 1;

    public P5_Phadke_Sharvil_KnightsTour1() {
        board[0][0] = 1;
    }

    public boolean advance() {
        byte[][] moves = new byte[8][];
        byte length = 0;
        for (byte[] move : KNIGHT_MOVES) if (getIfPossible(board, plus(loc,move)) == 0) moves[length++] = move;
        if (length == 0) return true;
        loc = plus(loc,moves[(int) (Math.random() * length)]);
        board[loc[0]][loc[1]] = ++step;
        return false;
    }

    public static byte getIfPossible(byte[][] arr, byte... loc) {
        return (loc[0] < 0 || loc[0] >= arr.length || loc[1] < 0 || loc[1] >= arr[loc[0]].length) ? -1 : arr[loc[0]][loc[1]];
    }

    public static byte[] plus(byte[] loc, byte[] ds) {
        byte[] out = new byte[Math.min(loc.length,ds.length)];
        for (int i = 0; i < out.length; i++) out[i] = (byte) (loc[i] + ds[i]);
        return out;
    }

    public int size() {
        return step;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(" ");
        for (int i = 1; i <= 8; i++) b.append(String.format("%3d", i));
        b.append('\n');
        for (int i = 0; i < board.length; i++) {
            byte[] row = board[i];
            b.append(i);
            for (byte item : row) {
                if (item > 0) b.append(String.format("%3d",item));
                else b.append("   ");
            }
            b.append('\n');
        }
        b.append(String.format("\n%d squares were visited\n", step));
        return b.toString();
    }
}