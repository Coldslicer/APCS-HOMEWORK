public class UserInputDriver {
    /*
     * Create a driver for testing your program that:
     * prompts the user for readline until they enter q (print what was returned)
     * prompt the user for readInt until they enter 0 (print what was returned)
     * prompt the user for readDouble until they enter 0 (print what was returned)
     * prompt the user for readBoolean until they enter a false value (print what was returned)
     */
    public static void main(String[] args) {
        String input1;
        int input2;
        double input3;
        boolean input4;
        while (!(input1 = UserInput.readLine("Enter q: ")).equals("q")) System.out.println(input1);
        while ((input2 = UserInput.readInt("Enter 0: ")) != 0) System.out.println(input2);
        while ((input3 = UserInput.readDouble("Enter 0.0: ")) != 0) System.out.println(input3);
        while (input4 = UserInput.readBoolean("Enter false: ")) System.out.println(input4);
    }
}
