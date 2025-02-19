import java.util.*;
public class Spilled_Milk_1
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int input = s.nextInt();
        int n2 = 0, n3 = 0, n5 = 0;
        while (true) {
            if (input % 2 == 0) {
                n2++;
                input /= 2;
            }
            if (input % 3 == 0) {
                n3++;
                input /= 3;
            }
            if (input % 5 == 0) {
                n5++;
                input /= 5;
            }
            if(input == 1) break;
            if (input % 2 != 0 && input % 3 != 0 && input % 5 != 0) {
                System.out.println(-1);
                return;
            }
        }
        // 6 > 2 and 3
        // 4 == 2 and 2
        int n6 = Math.min(n2,n3);
        n2 -= n6;
        n3 -= n6;
        int answer = n2 * 2 + n3 * 3 + n5 * 5 + n6 * 6;
        if (answer > 1000000000) answer = 1000000007;
        System.out.println(answer);
    }
}
