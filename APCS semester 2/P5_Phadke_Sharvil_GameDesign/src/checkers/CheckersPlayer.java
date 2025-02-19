package checkers;

import java.util.InputMismatchException;
import java.util.Scanner;

import engine.Game;
import engine.Player;

public class CheckersPlayer extends Player {

    public static Scanner s = new Scanner(System.in);

    public CheckersPlayer(Game g) {
        super(g);
    }

    @Override
    public void takeTurn() {
        System.out.println(getGame().getBoard());
        System.out.println("Enter coordinates of the piece to move, and either L or R for which direction to move it");
        int x = -1, y = -1;
        try {
            x = s.nextInt();
            y = s.nextInt();
        } catch (InputMismatchException ime) {
            System.out.println("invaild input format. Restarting turn:");
            s.nextLine();
            takeTurn();
            return;
        }
        String in = s.nextLine();
        boolean right = in.trim().equalsIgnoreCase("r");
        boolean success = ((CheckersBoard) game.getBoard()).tryMove(x,y,right);
        if (!success) {
            System.out.println("That move is illegal");
            takeTurn();
        }
    }
}
