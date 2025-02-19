import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class P5_Phadke_Sharvil_Life {

    public static void main(String[] args) {
        P5_Phadke_Sharvil_Life life = new P5_Phadke_Sharvil_Life("life100.txt");
        life.printBoard();
        life.runLife(5);
        life.printBoard();
    }
    private 

    boolean[][] board;
    int generation = 0;

    // Constructor that initializes a game of Life from the given data file
    public P5_Phadke_Sharvil_Life(String fileName) {
        Scanner s;
        try {
            s = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            return;
        }
        try {
            board = new boolean[s.nextInt()][s.nextInt()];
            s.nextLine();
            while (s.hasNextLine()) {
                board[s.nextInt()][s.nextInt()] = true;
            }
        } catch (InputMismatchException ime) {
            s.close();
            board = null;
            return;
        }

        s.close();
    }

    // Method that runs the Life simulation through the given generation
    // Generation 0 is the initial position, Generation 1 is the position after one round of Life, etc
    public void runLife(int numGenerations) {
        for (int i = 0; i < numGenerations; i++) nextGeneration();
        
    }

    // Method that returns the number of living cells in the given row
    // or returns -1 if row is out of bounds. The first row is row 0.
    public int rowCount(int row) {
        int count = 0;
        for (boolean item : board[row]) if (item) count++;
        return count;
    }

    // Method that returns the number of living cells in the given column
    // or returns -1 if column is out of bounds. The first column is column 0.
    public int colCount(int col) {
        int count = 0;
        for (int i = 0; i < board.length; i++) if (board[i][col]) count++;
        return count;
    }

    // Method that returns the total number of living cells on the board
    public int totalCount() {
        int count = 0;
        for (boolean[] row : board) for (boolean item : row) if (item) count++;
        return count;
    }

    // Prints out the Life array with row and column headers as shown in the example below.
    public void printBoard() {
        StringBuilder b = new StringBuilder();
        int indexLength = (int) Math.log(board.length) + 1;
        b.append(String.format(String.format("%%-%ds", indexLength+1), ""));
        for (int i = 0; i < board[0].length; i++) b.append(i%10);
        b.append('\n');
        for (int i = 0; i < board.length; i++) {
            b.append(String.format(String.format("%%%dd ", indexLength), i));
            for (boolean item : board[i]) b.append(item ? '*' : ' ');
            b.append('\n');
        }
        System.out.println(b);
    }

    // Advances Life forward one generation
    public void nextGeneration() {
        boolean[][] next = new boolean[board.length][board[0].length];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                int count = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        if (getOrFalse(board, y + dy,x + dx)) count++;
                    }
                }
                if (board[y][x]) next[y][x] = --count == 3 || count == 2;
                if (!board[y][x]) next[y][x] = count == 3;
            }
        }
        generation++;
        board = next;
    }

    public static boolean getOrFalse(boolean[][] arr, int... loc) {
        return (loc[0] < 0 || loc[0] >= arr.length || loc[1] < 0 || loc[1] >= arr[loc[0]].length) ? false : arr[loc[0]][loc[1]];
    }
}