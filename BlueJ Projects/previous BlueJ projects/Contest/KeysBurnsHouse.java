import java.util.*;
public class KeysBurnsHouse
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        int[] manga =  new int[num];
        for (int i = 0; i < num; i++) {
            manga[i] = s.nextInt();
        }

        int answer = 0;
        for (int i = 0; i < manga.length; i++) {
            answer += manga[manga[i] > manga[num-i-1] ? i : num-i-1];
            manga[manga[i] > manga[num-i-1] ? i : num-i-1] = 0;
            if (manga[i+1] + manga[num-i-2] > Math.max(manga[i],manga[num-i-1]) &&
            manga[i+2] + manga[num-i-3] <= manga[i+1] + manga[num-i-2]) {
                answer += manga[manga[i+1] > manga[num-i-2] ? i + 1 : num-i-2];
                manga[manga[i+1] > manga[num-i-2] ? i : num-i-2] = 0;
            }
        }
        System.out.println(answer);
        /*
        process:
        get the larger one 
        UNLESS 
        the next sum is larger than the larger one, then get the next ones
        UNLESS 
        the next sum + the larger one is larger than the sum, then get the larger one
        
        past that, our current actions don't matter
         */
    }
}
