import java.util.Scanner;
import java.io.PrintWriter;
public class P5_Phadke_Sharvil_Move {
    private int[] values;
    public P5_Phadke_Sharvil_Move(int... values) { this.values = values; }
    @Override
    public String toString() { 
        return String.format("(%d,%d)",values[0],values[1]); 
    }
    public int[] getData() { return values; }
    public void setData(int... values) { this.values = values; }
    public static P5_Phadke_Sharvil_Move parseMove() {
        return parseMove("Enter two integers: ");
    }
    public static P5_Phadke_Sharvil_Move parseMove(String prompt) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = s.nextLine();
            try {
                Scanner p = new Scanner(input);
                P5_Phadke_Sharvil_Move m = new P5_Phadke_Sharvil_Move(p.nextInt(),p.nextInt());
                if (p.hasNext()) throw new Error();
                return m;
            } catch (Throwable t) {
                System.out.println("Invalid format. Please enter exactly two integers separated by a space.");
            }
        }
    }
}
