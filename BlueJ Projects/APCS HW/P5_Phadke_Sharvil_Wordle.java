/*
 * Sharvil Phadke P5
 * Sun Nov 17
 * HW question: examine the way .removeIf is used. Write your own removeIf method that takes a list of strings, 
 * a character, a string, and an index and removes strings from the list if the character an that index doesn't match
 */

import java.io.*;
import java.util.*;

public class P5_Phadke_Sharvil_Wordle implements WordleSolver {
    private static List<String> allWords;
    static {
        allWords = new ArrayList();
        Scanner s = null;
        try {
            s = new Scanner(new File("all_wordle_words.txt"));
        } catch (FileNotFoundException fnfe) {
            System.err.println("Error: could not find source data file all_wordle_words.txt");
        }
        while (s.hasNext()) {
            allWords.add(s.next());
        }
        s.close();
    }
    
    public static List<String> getAllWords() {
        return new ArrayList<String>(allWords);
    }
    
    public static boolean isValidGuess(String input) {
        return allWords.contains(input.toUpperCase());
    }
    
    public static boolean isValidResult(String input) {
        if (input.length() != 5) return false;
        input = input.toUpperCase();
        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c)) return false;
            switch (c) {
                case 'G': case 'Y': case 'N': break;
                default: return false;
            }
        }
        return true;
    }
    
    public final List<String> getRemainingWords() {
        return new ArrayList(possibleWords);
    }
    
    private List<String> possibleWords;
    private List<String> guessableWords;
    
    
    public P5_Phadke_Sharvil_Wordle() {
        possibleWords = new ArrayList(allWords);
        guessableWords = new ArrayList(allWords);
    }
    
    private char[] solution = new char[5]; // this has no use right now but it may be useful later
    
    public boolean solved() {
        return possibleWords.size() <= 1;
    }
    
    @Override
    public String getNextGuess(String guess, String colorResult) {
        boolean solved = true;
        for (char c : solution) {
            if (c == 0) {
                solved = false;
                break;
            }
        }
        if (solved) return new String(solution);
        if (guess == null) guess = "";
        if (colorResult == null) colorResult = "";
        guess = guess.toUpperCase();
        colorResult = colorResult.toUpperCase();
        char[] chrs = guess.toCharArray();
        byte[] frequency = new byte[26]; // array representing how many of each character are in each word
        for (byte i = 0; i < chrs.length; i++) {
            final byte loc = i;
            if (colorResult.charAt(i) == 'Y') {
                possibleWords.removeIf(s -> s.charAt(loc) == chrs[loc]);
                possibleWords.removeIf(s -> !contains(s,chrs[loc]));
                guessableWords.removeIf(s -> s.charAt(loc) == chrs[loc]);
                guessableWords.removeIf(s -> !contains(s,chrs[loc]));
                frequency[chrs[loc] - 'A']++;
            }
            if (colorResult.charAt(i) == 'G') {
                solution[i] = chrs[i];
                possibleWords.removeIf(s -> s.charAt(loc) != chrs[loc]);
                guessableWords.removeIf(s -> s.charAt(loc) == chrs[loc]);
                frequency[chrs[loc] - 'A']++;
            }
        }
        for (byte i = 0; i < chrs.length; i++) {
            final byte loc = i;
            if (colorResult.charAt(i) == 'N') {
                possibleWords.removeIf(s -> count(s,chrs[loc]) > frequency[chrs[loc]-'A']);
                guessableWords.removeIf(s -> count(s,chrs[loc]) > frequency[chrs[loc]-'A']);
            }
        }
        guessableWords.sort(new InformationGainComparator(this,guessableWords));
        possibleWords.sort(new InformationGainComparator(this,possibleWords));
        try {
            //if (possibleWords.size() >= 5*guessableWords.size() && guessableWords.size() > 1) return guessableWords.get(0);
            return possibleWords.get(0);
        } catch (IndexOutOfBoundsException iobe) {
            return null;
        }
    }
    
    public boolean contains(String s, char query) {
        for (char c : s.toCharArray()) if (c == query) return true;
        return false;
    }
    
    public int count(String s, char query) {
        int i = 0;
        for (char c : s.toCharArray()) if (c == query) i++;
        return i;
    }

    public abstract class GuessComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            // return the difference in score between two words
            return score(o2.toString()) - score(o1.toString());
        }
        protected abstract int score(String word); 
    }
    public class InformationGainComparator extends GuessComparator {
        private List<String> words;
        private int[] wordsContaining = new int[26];
        private int[][] slotLetterFrequency = new int[26][5];
        P5_Phadke_Sharvil_Wordle solver;
        public InformationGainComparator(P5_Phadke_Sharvil_Wordle solver, List<String> words) {
            this.words = words;
            this.solver = solver;
            for (String word : words) {
                char[] chrs = word.toCharArray();
                boolean[] encountered = new boolean[26];
                for (int i = 0; i < chrs.length; i++) {
                    if (!encountered[chrs[i]-'A']) wordsContaining[chrs[i]-'A']++;
                    encountered[chrs[i]-'A'] = true;
                    slotLetterFrequency[chrs[i]-'A'][i]++;
                }
            }
        }
        @Override
        public int score(String word) {
            //int numGreen = 0;
            //for (char c : solver.solution) if (c != 0) numGreen++; 
            
            int score = 1;
            // gain 1 point for every instance of each letter in the word across the dataset, with diminishing point values for repeat letters
            char[] chrs = word.toCharArray();
            boolean[] encountered = new boolean[26];
            for (char c : chrs) {
                if (!encountered[c-'A']) score += wordsContaining[c-'A'];
                encountered[c-'A'] = true;
            }
            
            // gain 2 point for every instance of a new matching letter in the same slot across the dataset
            
            for (int i = 0; i < chrs.length; i++) score += 2 * slotLetterFrequency[chrs[i]-'A'][i];
            
            return score;
        }
    }
}
