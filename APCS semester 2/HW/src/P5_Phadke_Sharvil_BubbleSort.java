/*
 * Sharvil Phadke P5
 * Mon Jan 6 2025
 * Working
 * HW Question: How do you change which way it bubbles? how about the order?
 * If you want to apply bubble sort to objects, ideally what interface should it implement?
 */

import java.util.Arrays;

public class P5_Phadke_Sharvil_BubbleSort {
    public static void main(String[] args) {
        int[] arr = {1,5,9,7,5,3,4,5,6,7,7,8};
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
        bubbleSort1(arr);
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
        String[] ss = {"z","hi","buy","man","woman","who","bye","cool","idk","i"};
        for (String s : ss) System.out.print(s+" ");
        System.out.println();
        bubbleSort2(ss);
        for (String s : ss) System.out.print(s+" ");
    }

    public static void bubbleSort1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] < arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void bubbleSort2(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length-1; j >= 1; j--) {
                if (arr[j].compareTo(arr[j-1]) < 0) {
                    String temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
