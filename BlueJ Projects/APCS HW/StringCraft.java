/*
 * Sharvil Phadke
 * Nov 3 2024
 * Working
 * HW Question:
 *  What are some ways to return multiple values? What approach is easiest
 *  only using things we've learned in class? If you know the neccesary
 *  information, what high level approach does Python take? (you don't need
 *  to know about lower level differences, even though there are a few)
 */
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class StringCraft {
    public static void testing() {
        //alias for System.out
        var o = System.out;
        char[] s = "ZSVBESYAMZ".toCharArray();//generateRandomString(10);
        o.print("the string is: ");
        o.println(s);
        o.print(findOptimalSolution(s));
    }
    public static void main(String[] args) {
        //alias for System.out
        var o = System.out;
        o.println("Welcome to String Craft");
        Scanner s = new Scanner(System.in);
        o.print("Enter the length of the string you would like to craft: ");
        int l = s.nextInt();
        s.nextLine();
        char[] cur = generateRandomString(l);
        char[] original = cur.clone();
        int score = score(cur);
        o.println();
        o.print(cur);
        o.printf(" is worth %d points. Your score is %d.\n",score,score);
        for (int i = 0; i < l; i++) o.print(i%10);
        o.println();
        int c = 0;
        while (hasValidMoves(cur)) {
            c++;
            P5_Phadke_Sharvil_Move mo = P5_Phadke_Sharvil_Move.parseMove("Enter the indexes of the characters you would like to swap: ");
            int[] m = mo.getData();
            if (m[0] >= l || m[1] >= l) {
                o.println("That move is illegal because one (or both) of the indexes is out of range");
                c--;
                o.println();
                o.print(cur);
                o.printf(" is worth %d points. Your score is %d.\n",score+c,score);
                for (int i = 0; i < l; i++) o.print(i%10);
                o.println();
            }
            char[] next = cur.clone();
            applySwap(next,m);
            int nextScore = score(next);
            if (nextScore - c >= score) {
                score = nextScore - c;
                cur = next;
                o.println();
                o.print(cur);
                o.printf(" is worth %d points. Your score is %d.\n",nextScore,score);
                for (int i = 0; i < l; i++) o.print(i%10);
                o.println();
            } else {
                o.println("That move is illegal because it would lower your score to "+(nextScore - c));
                c--;
                o.println();
                o.print(cur);
                o.printf(" is worth %d points. Your score is %d.\n",score+c,score);
                for (int i = 0; i < l; i++) o.print(i%10);
                o.println();
            }
        }
        o.println("GAME OVER. Your final score is: "+score);
        o.print("Would you like me to generate the optimal solution? (y/n): ");
        if (s.nextLine().equals("y")) {
            cur = original;
            Solution optimalSolution = findOptimalSolution(cur);
            o.print(optimalSolution);
        }
    }
    public static char[] generateRandomString(int length) {
        char[] oput = new char[length];
        for (int i = 0; i < length; i++) oput[i] = randomLetter();
        return oput;
    }
    public static char randomLetter() {
        return (char) ((char)(Math.random() * 26) + 'A');
    }
    public static boolean hasValidMoves(char[] s) {
        s = s.clone();
        int score = score(s);
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s.length; j++) {
                applySwap(s, i, j);
                if (score(s) > score) {
                    return true;
                }
                applySwap(s, i, j);
            }
        }
        return false;
    }
    public static void applySwap(char[] s, int i1, int i2) {
        // this just edits the input in order to not have to deal with cloning
        char temp = s[i1];
        s[i1] = s[i2];
        s[i2] = temp;
    }
    public static void applySwap(char[] s, int[] is) {
        // this just edits the input in order to not have to deal with cloning
        char temp = s[is[0]];
        s[is[0]] = s[is[1]];
        s[is[1]] = temp;
    }
    public static int score(char[] s) {
        int score = 0;
        char prev = '0'; //can be pretty much anything
        int prevScore = 0;
        for (char c : s) {
            if (prev == c) {
                prevScore += 2;
                score += prevScore;
            }
            else if ((prev + 1 - 'A') % 26 + 'A' == c) {
                prevScore += 1;
                score += prevScore;
            }
            else {
                prevScore = 1;
                score += 1;
            }
            prev = c;
        }
        return score;
    }
    public static List<int[]> getValidMoves(char[] s) {
        List<int[]> output = new ArrayList<int[]>();
        s = s.clone();
        int score = score(s);
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s.length; j++) {
                applySwap(s, i, j);
                if (score(s) > score) {
                    output.add(new int[]{i,j});
                }
                applySwap(s, i, j);
            }
        }
        return output;
    }
    public static Solution findOptimalSolution(char[] s) {
        return findOptimalSolution(s, 0);
    }
    public static Solution findOptimalSolution(char[] s, int c) {
        List<int[]> validMoves = getValidMoves(s);
        int score = score(s);
        Solution optimalSolution = new Solution(new LinkedList<int[]>(),score(s)-c);
        if (validMoves.isEmpty()) return optimalSolution; 
        else {
            for (int[] move : validMoves) {
                applySwap(s,move);
                Solution optimalSolutionOfBranch = findOptimalSolution(s,c+1);
                optimalSolutionOfBranch.moves.addFirst(move);
                applySwap(s,move);
                if (optimalSolutionOfBranch.score >= optimalSolution.score) {
                    optimalSolution = optimalSolutionOfBranch;
                }
            }
        }
        return optimalSolution;
    }
    private static class Solution {
        List<int[]> moves;
        int score;
        Solution(List<int[]> moves, int score) {
            this.moves = moves;
            this.score = score;
        }
        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            b.append("The optimal set of moves was calculated to be:\n");
            for (int[] move : moves) {
                b.append(String.format("(%d,%d)\n",move[0],move[1]));
            }
            b.append("GAME OVER\n");
            b.append("The optimal score was: ");
            b.append(score);
            b.append("\n");
            return b.toString();
        }
    }
}
