/*
 * Sharvil Phadke P5
 * Sun Feb 9
 * Working
 * HW Question:
 * 
 *      1) Name some pre-built sorting solutions
 * 
 *      2) What are some preconditions for Collections.sort(list)
 * 
 *      3) why might someone want to use list.sort(Comparator), Collections.sort(List,Comparator), etc.
 *          instead of prebuilt solutions that don't take a Comparator?
 */

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class P5_Phadke_Sharvil_CountWords {
    public static void main(String[] args) {
        var countWords = new P5_Phadke_Sharvil_CountWords("dream.txt");
        countWords.generateOutputFile();
    }

    Map<String, Integer> words = new HashMap<>();
    String filename;

    public P5_Phadke_Sharvil_CountWords(String filename) {
        this.filename = filename;
        LoadWords(filename);
    }

    public void generateOutputFile() {
        int i = this.filename.indexOf('.');
        if (i == -1) throw new IllegalArgumentException("could not parse output file name because filename does not have a '.'");
        generateOutputFile(this.filename.substring(0,i) + "_answer" + this.filename.substring(i));
    }

    public void generateOutputFile(String filename) {
        File file = new File(filename);
        PrintWriter pw;
        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        List<Frequency> frequencies = new ArrayList<>(words.size());
        for (Entry<String,Integer> e : words.entrySet()) {
            frequencies.add(new Frequency(e.getKey(),e.getValue()));
        }
        frequencies.sort(
            new Comparator<Frequency>() {
                @Override
                public int compare(Frequency o1, Frequency o2) {
                    return o1.f != o2.f ? o2.f - o1.f : o1.k.compareTo(o2.k);
                }
            }
        );
        int max = 0;
        for (Frequency f : frequencies) {
            max = Math.max(f.f,max);
        }
        int i = 0;
        while(i < frequencies.size()) {
            Frequency f = frequencies.get(i++);
            pw.printf(String.format("%%-%dd %%-%dd %%s\n",(int) Math.ceil(Math.log10(frequencies.size())),(int) Math.ceil(Math.log10(max))),i,f.f,f.k);
        }
        
        pw.close();
    }

    private class Frequency {
        Frequency(String k, int f) {
            this.k = k;
            this.f = f;
        }
        String k;
        int f;
    }

    public void LoadWords(String filename) {
        Scanner fr;
        try {
            fr = new Scanner(new File(filename));
        } catch (FileNotFoundException fnfe) {
            return;
        }
        Queue<String> lines = new LinkedList<>(), words = new LinkedList<>();
        while (fr.hasNextLine()) {
            lines.add(fr.nextLine());
        }
        StringBuilder b = new StringBuilder();
        for (String line : lines) {
            for (char c : line.toCharArray()) {
                if (Character.isLetter(c) || (c == '\'' || c == '-') && !b.isEmpty()) {
                    b.append((c));
                } else {
                    if (!b.isEmpty()) {
                        words.add(b.toString().toLowerCase());
                        b = new StringBuilder();
                    }
                };
            }
            if (!b.isEmpty()) {
                words.add(b.toString().toLowerCase());
                b = new StringBuilder();
            }
        }
        for (String word : words) {
            this.words.put(word,this.words.getOrDefault(word,0)+1);
        }
        fr.close();
    }
}