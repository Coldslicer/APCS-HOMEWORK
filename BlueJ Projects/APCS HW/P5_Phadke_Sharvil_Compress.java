/*
 * Sharvil Phadke
 * P5
 * Nov 7 2024
 * Working
 * Hw question: what is the main advantage of PrintWriter over FileWriter? Complete the problem using both
 * appoaches: Write to a file: "I will name my ___ variable ___" where the first blank is the index followed by
 * "th", "nd", or "st" depending on the number, and the the second blank is a random character.
 */

import java.io.*;
import java.util.*;

public class P5_Phadke_Sharvil_Compress {
    public static void main(String[] args) {
        try {
            compress("P5_Phadke_Sharvil_LeetSpeak.java");
        } catch (FileNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.print("File compressed. Please check before decompressing, press enter to continue");
        Scanner s = new Scanner(System.in);
        s.nextLine();
        
        try {
            decompress("P5_Phadke_Sharvil_LeetSpeak.txt");
        } catch (FileNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.print("File decompressed.");
        s.close();
    }
    
    public static void compress(String filename) throws FileNotFoundException, IllegalArgumentException {
        if (!filename.endsWith(".java")) throw new IllegalArgumentException("The file must be a .java file");
        Scanner in = new Scanner(new File(filename));
        PrintWriter out = new PrintWriter(new File(filename.substring(0,filename.length()-4)+"txt"));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            int i;
            for (i = 0; i < line.length() && line.charAt(i) == '\t'; i++);
            out.printf("%d %s\n",i,line.substring(i));
        }
        out.close();
        in.close();
    }
    
    public static void decompress(String filename) throws FileNotFoundException, IllegalArgumentException {
        if (!filename.endsWith(".txt")) throw new IllegalArgumentException("The file must be a .txt file");
        Scanner in = new Scanner(new File(filename));
        PrintWriter out = new PrintWriter(new File(filename.substring(0,filename.length()-3)+"java"));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            int space = line.indexOf(' ');
            int t = Integer.parseInt(line.substring(0,space));
            if (line.length() - (space+1) > 0) line = line.substring(space+1);
            else line = "";
            for (; t > 0; t--) line = '\t' + line;
            out.println(line);
        }
        out.close();
        in.close();
    }
}
