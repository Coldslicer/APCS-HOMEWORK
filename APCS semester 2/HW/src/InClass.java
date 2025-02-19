import java.util.Arrays;

public class InClass {
    public static void main(String[] args) {
        insertionSort(new int[] {1, 5, 8, 2 ,3 ,6, 4545445});
    }
    
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int copy = arr[i], loc = i;
            while (loc > 0 && copy > arr[loc - 1]) {
                arr[loc] = arr[loc - 1];
                loc--;
            }
            arr[loc] = copy;
            
            System.out.println(Arrays.toString(arr));
        }
    }
}