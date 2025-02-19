/*
 * Sharvil Phadke P5
 * Oct 28 2024
 * Working
 * HW Question:
 *      Look at a normal solution, as well as the Set, Map, and StringBuilder API
 *      Why is this code more efficient than your code?
 *      What operations does it avoid? How does it replace these operations?
 */

import java.util.*;
public class P5_Phadke_Sharvil_Shorthand {
    public static Set<Character> vowels = Set.of('a','e','i','o','u');
    
    public static String withoutVowels(String s) {
        StringBuilder b = new StringBuilder();
        for (char c : s.toCharArray()) if (!vowels.contains(Character.toLowerCase(c))) b.append(c);
        return b.toString();
    }
    
    public static Map<String,String> replacements = Map.of("and","&","to","2","you","u","for","4");
    
    public static String translateToShorthand(String s) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isLetter(c)) b.append(c);
            else {
                StringBuilder wordBuilder = new StringBuilder();
                for (i--;i+1 < s.length() && Character.isLetter(s.charAt(i+1));i++) wordBuilder.append(s.charAt(i+1));
                String word = wordBuilder.toString();
                if (word.length() > 1) b.append(replacements.getOrDefault(word.toLowerCase(),withoutVowels(word)));
                else b.append(word);
            }
        }
        return b.toString();
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Enter text to convert to shorthand:");
            System.out.println(translateToShorthand(s.nextLine()));
            System.out.print("continue? (yes/no)\t");
        } while (s.nextLine().equals("yes"));
    }
}
