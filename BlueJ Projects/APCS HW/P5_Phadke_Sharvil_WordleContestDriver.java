import wordletester.WordleTester;

public class P5_Phadke_Sharvil_WordleContestDriver {

    public static void main(String[] args) {

        // Modify these two variables
        boolean printMessages = false;  // Whether to print all guesses along the way
        int numTrials = 1000;           // Number of games to run

        int totalTurns = 0;

        for (int i = 0; i < numTrials; i++) {
            WordleTester wt = new WordleTester();

            WordleSolver w = new P5_Phadke_Sharvil_Wordle();

            String guess = w.getNextGuess(null, null);
            int turns = 1;

            do {
                String color = wt.getColorResponse(guess);
                if (printMessages) {
                    System.out.println("Guess:    " + guess);
                    System.out.println("Response: " + color);
                }
                if (!wt.getColorResponse(guess).equals("GGGGG")) {
                    guess = w.getNextGuess(guess, color);
                    turns++;
                }           
            } while(!wt.getColorResponse(guess).equals("GGGGG"));

            System.out.println("You found " + guess.toUpperCase() + " in " + turns + " turns");
            totalTurns += turns;
        }
        System.out.println("It took you an average of " + ((double)totalTurns/numTrials) + " guesses to find each word." );
    }
}