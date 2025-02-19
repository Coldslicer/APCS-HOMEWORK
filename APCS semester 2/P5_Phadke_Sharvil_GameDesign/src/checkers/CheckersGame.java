package checkers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import engine.Board;
import engine.Game;
import engine.Player;

public class CheckersGame extends Game {

    public CheckersGame(Board b) {
        super(b);
        
    }

    @Override
    public List<Player> createPlayers() {
        return List.of(new CheckersPlayer(this), new CheckersPlayer(this));
    }

    @Override
    public boolean gameIsOver() {
        return getNumBlack() == 0 || getNumRed() == 0;
    }

    public int getNumRed() {
        int numRed = 0;
        for (int i = 0; i < getBoard().getNumRows(); i++) {
            for (int j = 0; j < getBoard().getNumCols(); j++) {
                if (((CheckersTile)getBoard().getTile(i, j)).getStatus() > 0) numRed++;
            }
        }
        return numRed;
    }

    public int getNumBlack() {
        int numBlack = 0;
        for (int i = 0; i < getBoard().getNumRows(); i++) {
            for (int j = 0; j < getBoard().getNumCols(); j++) {
                if (((CheckersTile)getBoard().getTile(i, j)).getStatus() < 0) numBlack++;
            }
        }
        return numBlack;
    }

    @Override
    public void printGameResults() {
        if (getNumRed() == 0) {
            System.out.println("RED wins!");
        } else if (getNumBlack() == 0) {
            System.out.println("BLACK wins!");
        }
        
    }

    @Override
    public void printRules() {
        Scanner s;
        try {
            s = new Scanner(new File("rules.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        while (s.hasNextLine()) {
            System.out.println(s.nextLine());
        }
        s.close();
    }
    
}
