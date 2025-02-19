/*
 * Sharvil Phadke P5
 * Nov 8 2024
 * Working
 * HW Question: What is the main difference between an ArrayList and an array (and a LinkedList if you know what that is)
 * Give example use cases for each. For example: when would you want to use an array instead of an ArrayList?
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class P5_Phadke_Sharvil_Permutations {
    private static final List<Integer> nums = List.of(1,2,3,4,5,6,7,8,9,10);
    private static final Random r = new Random();
    public static List<Integer> nextPermutation() {
        int[] arr = new int[10];
        List<Integer> clone = new ArrayList(nums);
        for (int i = 0; i < arr.length; i++) arr[i] = clone.remove(r.nextInt(clone.size()));
        for (int i = 0; i < arr.length; i++) clone.add(arr[i]);
        return clone;
    }
    
    public static void main(String[] args) {
        // alias for System.out
        var o = System.out;
        for (int i = 0; i < 10; i++) {
            o.printf("A permutation of %s is %s\n",nums.toString(),nextPermutation().toString());
        }
    }
}
