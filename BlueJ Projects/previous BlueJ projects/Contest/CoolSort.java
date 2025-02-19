import java.util.Scanner;
public class CoolSort
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numStudents = s.nextInt();
        int[] students = new int[numStudents];
        int answer = 0;
        for (int i = 0; i < numStudents; i++) {
            students[i] = s.nextInt();
        }
        for (int i = 0; i < students.length; i++) {
            if (students[i] % 2 != (i + 1) % 2) {
                System.out.println(-1);
                return;
            }
            answer += Math.abs((i+1) - students[i])/2;
        }
        answer /= 2;
        System.out.println(answer);
    }
}
