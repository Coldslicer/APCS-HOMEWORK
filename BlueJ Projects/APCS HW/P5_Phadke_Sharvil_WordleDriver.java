import java.util.Scanner;

public class P5_Phadke_Sharvil_WordleDriver {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        P5_Phadke_Sharvil_Wordle solver = new P5_Phadke_Sharvil_Wordle();
        String guess = "";
        String colors = "";
        guess = solver.getNextGuess(guess,colors);
        while(true) {
            if (solver.solved()) break;
            System.out.printf("Remaining correct answers, sorted by guess strength: %s\n",solver.getRemainingWords());
            System.out.printf("You should guess: %s\n",guess);
            String input;
            while(true) {
                System.out.printf("Press enter to use the optimal guess, or enter your guess: ");
                input = s.nextLine();
                if (input.isEmpty()) break;
                else if (solver.isValidGuess(input)) {
                    guess = input;
                    break;
                } else System.out.printf("Your entry, %s, is not a valid guess and will likely not be accepted by wordle. Try again.\n",input);
            }
            while (true) {
                System.out.print("Enter the colors as text (G, Y, or N): ");
                colors = s.nextLine();
                if (solver.isValidResult(colors)) break;
                System.out.printf("%s was not a valid color result (5 letters, must all be G, Y, or N). Try again.\n",colors);
            }
            guess = solver.getNextGuess(guess,colors);
        }
        if (guess != null) System.out.printf("The answer is: %s",guess);
        else System.out.println("No such answer exists. You probably entered a color result wrong.");
    }
}