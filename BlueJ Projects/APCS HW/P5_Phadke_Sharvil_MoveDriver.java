/*
 * Sharvil Phadke
 * Oct 30 2024
 * WORKING
 * HW question:
 * 
 * What is the easiest loop to write input with? Why? Assume you can't use break.
 */

import java.util.Scanner;
public class P5_Phadke_Sharvil_MoveDriver {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        do {
            P5_Phadke_Sharvil_Move m = P5_Phadke_Sharvil_Move.parseMove();
            System.out.println(m);
            System.out.print("Type q to quit or anything else to continue: ");
        } while (!s.nextLine().equals("q"));
    }
}
