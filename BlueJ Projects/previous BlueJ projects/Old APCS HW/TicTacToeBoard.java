import java.util.Scanner;

public class TicTacToeBoard {
    private static enum State {
        X, O, NONE
    }
    private State[][] board;
    private State winner;
    
    public TicTacToeBoard() {
        board = new State[3][3];
        for (int y = 0 ; y < 3; y++) for (int x = 0 ; x < 3; x++) board[y][x] = State.NONE;
    }
    
    public void play(State player, int x, int y) throws IllegalStateException {
        if (winner() != State.NONE) throw new IllegalStateException("the game has already finished");
        if (board[y][x] == State.NONE) board[y][x] = player;
        else throw new IllegalStateException("That square is occupied");
    }

    public State winner() {
        for (State player : new State[] {State.X, State.O}) {
            boolean done = true;
            for (int y = 0; y < 3; y++) {
                done = false;
                for (int x = 0; x < 3; x++) {
                    if (board[y][x] != player) {
                        done = false;
                        break;
                    }
                }
                if (done == true) return player;
            }
            for (int x = 0; x < 3; x++) {
                done = false;
                for (int y = 0; y < 3; y++) {
                    if (board[y][x] != player) {
                        done = false;
                        break;
                    }
                }
                if (done == true) return player;
            }
            done = true;
            for (int i = 0; i < 3; i++) {
                if (board[i][i] != player) {
                    done = false;
                    break;
                }
            }
            if (done == true) return player;
            
            done = true;
            for (int i = 0; i < 3; i++) {
                if (board[i][2-i] != player) {
                    done = false;
                    break;
                }
            }
            if (done == true) return player;
        }
        return State.NONE;
    }
    
    public static void main(String[] args) {
        TicTacToeBoard board = new TicTacToeBoard();
        Scanner s = new Scanner(System.in);
        boolean xturn
        while (true) {
            System.out.println(xturn ? "
        }
    }
}
