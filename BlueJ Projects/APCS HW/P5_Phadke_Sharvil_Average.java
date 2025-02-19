/*
 * Sharvil Phadke P5
 * Nov 4 2024
 * Working
 * HW Question: what is the easiest way to catch anything? Why does this work?
 */
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class P5_Phadke_Sharvil_Average
{
    public static void main(String[] args) {
        Scanner in = null;
        FileWriter out = null;
        try { in = new Scanner(new File("numbers.txt")); } 
        catch (FileNotFoundException fnfe) { fnfe.printStackTrace(); }
        try { out = new FileWriter("output.txt"); }
        catch (IOException ioe) { ioe.printStackTrace(); }
        long sum = 0;
        int count = 0;
        while (true) {
            try { sum += in.nextInt(); }
            catch (Throwable t) { break; }
            count++;
        }
        try { 
            out.write(String.format("%.2f",(double)(sum)/count));
            out.close(); 
        }
        catch (IOException ioe) { ioe.printStackTrace(); }
        in.close();
    }
}
