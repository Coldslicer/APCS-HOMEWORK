package linerace;

public class Driver {

    public static void main(String[] args) {
        LineRaceBoard b = new LineRaceBoard();
        LineRaceGame g = new LineRaceGame(b);
        g.startGame();
    }

}