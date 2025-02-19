import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInput {
    public static final Scanner s = new Scanner(System.in);
    public static String readLine(String input) {
        if (input == null) input = "";
        System.out.print(input);
        return s.nextLine();
    }
    
    public static int readInt(String prompt) {
        int output;
        while (true) {
            System.out.print(prompt);
            try { 
                output = s.nextInt(); 
                s.nextLine();
                break;
            }
            catch (InputMismatchException ime) {
                System.out.println("Invalid input. Please enter an integer.");
                s.nextLine();
                continue;
            }
        }
        return output;
    }
    
    public static double readDouble(String prompt) {
        double output;
        while (true) {
            System.out.print(prompt);
            try { 
                output = s.nextDouble(); 
                s.nextLine();
                break;
            }
            catch (InputMismatchException ime) {
                System.out.println("Invalid input. Please enter a double.");
                s.nextLine();
                continue;
            }
        }
        return output;
    }
    
    public static boolean readBoolean(String prompt) {
        boolean output;
        while (true) {
            System.out.print(prompt);
            try { 
                String input = s.nextLine(); 
                output = parseBoolean(input);
                break;
            }
            catch (IllegalArgumentException iae) {
                System.out.println("Invalid input. Please enter a boolean.");
                continue;
            }
        }
        return output;
    }
    
    private static final String[] falsePatterns = {"false","no","negative"};
    private static final String[] truePatterns = {"true","yes","affirmative"};
    public static boolean parseBoolean(String input) throws IllegalArgumentException {
        input = input.toLowerCase();
        for (String s : falsePatterns) if (input.equals(s)) return false;
        for (String s : truePatterns) if (input.equals(s)) return true;
        throw new IllegalArgumentException("Invalid string cannot be parsed to a boolean.");
    }
}
