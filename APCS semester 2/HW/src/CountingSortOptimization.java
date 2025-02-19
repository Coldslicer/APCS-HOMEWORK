import java.util.*;
import java.util.function.Function;

public class CountingSortOptimization {

    public static void main(String[] args) {
        Integer[] arr = {345, 753, 947, 289, 784, 239};//{0,10,5,4,6,7,8,4,1,3,4,6,7};
        System.out.println(Arrays.toString(arr));
        radixSort(arr, (o) -> o);
        System.out.println(Arrays.toString(arr));
    }

public static int RADIX = 10;

    public static <T extends Comparable<T>> void radixSort(T[] arr, Function<T,Integer> getValue) {
        int[] tags = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) tags[i] = getValue.apply(arr[i]);



        int min = Integer.MAX_VALUE;
        for (int n : tags) min = Math.min(n, min);
        for (int i = 0; i < tags.length; i++) tags[i] -= min;


        int max = Integer.MIN_VALUE;
        for (int n : tags) {
            max = Math.max(n, max);
        }

        int digits = 1;
        for (int i = 1; i < max; i *= RADIX) {
            digits++;
        }

        for (int i = 0; i < digits; i++) {
            countingSortByDigit(arr, tags, i, RADIX);
            System.out.println(Arrays.toString(arr));
        }
        
    }

    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void countingSortByDigit(T[] arr, int[] tags, int digit, int radix) {

        Queue<T>[] objBuckets = new Queue[radix];
        Queue<Integer>[] tagBuckets = new Queue[radix];

        // all queue operations are O(1) for linked lists
        for (int i = 0; i < radix; i++) {
            objBuckets[i] = new LinkedList<T>();
            tagBuckets[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < arr.length; i++) {
            int loc = extractDigit(tags[i], digit, radix);
            objBuckets[loc].add(arr[i]);
            tagBuckets[loc].add(tags[i]);
        }

        System.out.println(Arrays.toString(objBuckets));

        // reconstrut arr and tags
        int i = 0;
        for (Queue<T> bucket : objBuckets) {
            while (!bucket.isEmpty()) {
                arr[i++] = bucket.remove();
            }
        }

        i = 0;
        for (Queue<Integer> bucket : tagBuckets) {
            while (!bucket.isEmpty()) {
                tags[i++] = bucket.remove();
            }
        }
    }


    public static int extractDigit(int num, int digit, int radix) {
        // pow is assumed O(1) or O(b) which do not contribute a power of N
        return (num / (int) Math.pow(radix, digit)) % radix;
    }

    public static void basicCountingSort(int[] arr) {
        int max = 0;
        for (int i : arr) max = Math.max(i,max);
        int[] counts = new int[max + 1];
        for(int i : arr) counts[i]++;
        int i, j = i = 0;
        while (i < arr.length) {
            if (counts[j] == 0) j++;
            else {
                counts[j]--;
                arr[i++] = j;
            }
        }
    }
}
