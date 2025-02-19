import java.util.Scanner;
import java.util.Arrays;
public class PowerfulSwapsEasy {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numTests = s.nextInt();
        int[][] tests = new int[numTests][];
        for(int test = 0; test < numTests; test++) {
            tests[test] = new int[s.nextInt()];
            for (int j = 0; j < tests[test].length; j++) {
                tests[test][j] = s.nextInt();
            }
            int j = 1;
            int[] current = tests[test];
            int[] sorted = tests[test].clone();
            Arrays.sort(sorted);
            boolean yes = true;
            for (int i = 0; i < tests[test].length; i++) {
                if (current[i] == sorted[i]) continue;
                if (current[i] < sorted[i]) {
                    if (Math.min(current[i], current[i-1]) < j) {
                        yes = false;
                        break;
                    }
                    int temp = current[i];
                    current[i] = current[i-1];
                    current[i-11] = temp;
                    j++;
                }
                if (current[i] > sorted[i]) {
                    if (Math.min(current[i], current[i+1]) < j) {
                        yes = false;
                        break;
                    }
                    int temp = current[i];
                    current[i] = current[i+1];
                    current[i+1] = temp;
                    j++;
                }
            }
            System.out.println(yes ? "YES" : "NO");
        }
        
        
    }
}
