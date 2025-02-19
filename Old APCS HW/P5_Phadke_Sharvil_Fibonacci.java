/*
 * Sharvil Phadke P5
 * Sep 27
 * working: yes
 * 
 * hw question:
 * explain what a stack overflow is
 * advanced: explain two potential ways to optimize recusion in some way
 * and implement one of them for the fibonacci sequence
 */
import java.util.*;
public class P5_Phadke_Sharvil_Fibonacci
{
    private static List<Long> fibMemos = new ArrayList<Long>();
    public static long fib(int x) {
        if (x <= 0) return 1;
        if (fibMemos.size() > x) return fibMemos.get(x);
        long answer = fib(x - 1) + fib(x - 2);
        fibMemos.add(answer);
        return answer;
    }
    public static void test(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.printf("the number at index %d in the fibonacci sequence is %d\n",i,fib(i));
        }
        for (int i = 0; i < 10; i++) {
            System.out.printf("positive integer %d * positive integer %d = %d\n",i,i+1,positive_multiply(i,i+1));
        }
        for (int i = -5; i < 5; i++) {
            System.out.printf("%d * %d = %d\n",-i,i+1,multiply(-i,i+1));
        }
        
    }
    @Deprecated
    public static long positive_multiply(int n1, int n2) {
        if (n2 == 0 || n2 == 0) return 0;
        return n1 + positive_multiply(n1, n2 - 1);
    }

    public static long multiply(int n1, int n2) {
        return ((n1 < 0 ^ n2 < 0) ? -1 : 1) * positive_multiply(Math.abs(n1),Math.abs(n2));
    }
}
