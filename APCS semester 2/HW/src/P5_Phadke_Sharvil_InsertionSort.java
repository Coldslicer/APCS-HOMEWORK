/*
 * Sharvil Phadke P5
 * Jan 12
 * Working
 * HW Quiz:
 *      Which of the three sorting methods we've used so far
 *      are most viable for use for a linked list? 
 * 
 *      Answer: InsertionSort is very viable, Bubble sort is very viable
 *      Selection sort is possible but would be more complicated than with arrays
 *      if time complexity should be preserved.
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;

public class P5_Phadke_Sharvil_InsertionSort {
    public static void main(String[] args) {
        insertionSort1(new int[] {9,5,1,3,7,4});
        insertionSort2(new String[] {"far","don't","trees","from","fall","apples"});
        ArrayList<P5_Phadke_Sharvil_YelpRating> l = new ArrayList<>();
        l.add(new P5_Phadke_Sharvil_YelpRating("Cosmic", "Terrible", 0, "Coldslicer"));
        l.add(new P5_Phadke_Sharvil_YelpRating("Coldslicer", "Terrible", 1, "Cosmic"));
        l.add(new P5_Phadke_Sharvil_YelpRating("Coldslicer", "Amazing", Double.MAX_VALUE, "Coldslicer"));
        l.add(new P5_Phadke_Sharvil_YelpRating("Apple", "I hate you", 2, "Google"));
        l.add(new P5_Phadke_Sharvil_YelpRating("Google", "Ehh they're ok", 3, "Apple"));
        insertionSort3(l);
    }

    public static void insertionSort1(int[] arr) {
        // ascending order by inserting items into their proper place on the left
        for (int i = 1; i < arr.length; i++) {
            int copy = arr[i], loc = i;

            while (loc > 0 && copy <= arr[loc - 1]) {
                arr[loc] = arr[loc - 1];
                loc--;
            }

            arr[loc] = copy;
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void insertionSort2(String[] arr) {
        // sorts the list into descending order by inserting items into their proper place on the right. This means your outer loop should run backward and your inner loop should run forward. Print the list at the end of each outer loop iteration.
        for (int i = arr.length - 2; i >= 0; i--) {
            String copy = arr[i];
            int loc = i;

            while (loc < arr.length - 1 && copy.compareTo(arr[loc + 1]) < 0) {
                arr[loc] = arr[loc + 1];
                loc++;
            }

            arr[loc] = copy;

            System.out.println(Arrays.toString(arr));
        }
    }

    public static void insertionSort3(ArrayList<P5_Phadke_Sharvil_YelpRating> list) {
        for (int i = 1; i < list.size(); i++) {
            P5_Phadke_Sharvil_YelpRating copy = list.get(i);
            int loc = i;
            while (loc > 0 && copy.compareTo(list.get(loc-1)) > 0) {
                list.set(loc, list.get(loc-1));
                loc--;
            }
            list.set(loc,copy);
            System.out.println(list);
        }
    }
}
