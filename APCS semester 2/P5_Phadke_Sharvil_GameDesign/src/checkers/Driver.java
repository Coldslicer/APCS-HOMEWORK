package checkers;

public class Driver {
    public static void main(String[] args) {
        CheckersBoard b = new CheckersBoard();
        CheckersGame g = new CheckersGame(b);
        System.out.println(b);
        g.startGame();
    }
}
