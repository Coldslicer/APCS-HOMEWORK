/*
 * Sharvil Phadke P5
 * Mon Jan 8 2025
 * HW Question: Compare bubble sort and selection sort in terms of runtime.
 * Do they take the same time, does one take longer? Aternatively,
 * Which one takes more swaps to complete the task, 
 * or do they take the same amount?
 */

import java.util.*;

public class P5_Phadke_Sharvil_SelectionSort {
    public static void main(String[] args) {
        int[] nums = {9,5,1,3,7,4};
        for (int i : nums) System.out.print(i + " ");
        System.out.println();
        selectionSort1(nums);
        for (int i : nums) System.out.print(i + " ");
        System.out.println();

        String[] ss = {"z","hi","buy","man","woman","who","bye","cool","idk","i"};
        for (String s : ss) System.out.print(s+" ");
        System.out.println();
        selectionSort2(ss);
        for (String s : ss) System.out.print(s+" ");

        /*
        String[] symbols = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int[] values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        List<Card> cardList = new LinkedList<>();
        for (int i = 0; i < symbols.length; i++) cardList.add(new Card(symbols[i],values[i],true));

        System.out.println(cardList);

        Random r = new Random();
        ArrayList<Card> shuffedCards = new ArrayList<>(12);
        while (cardList.size() > 0) shuffedCards.add(cardList.remove(r.nextInt(cardList.size())));

        selectionSort(shuffedCards);
        */

    }
    
    public static void selectionSort1(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int s = i;
            for (int j = i - 1; j >= 0 ; j--) {
                if (arr[j] > arr[s]) {
                    s = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[s];
            arr[s] = temp;
            System.out.println (Arrays.toString(arr));
        }
    }
        
    public static void selectionSort2(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int loc = i;
            for (int j = i + 1; j < arr.length ; j++) {
                if (arr[j].compareTo(arr[loc]) > 0) {
                    loc = j;
                }
            }
            String temp = arr[i];
            arr[i] = arr[loc];
            arr[loc] = temp;
            System.out.println (Arrays.toString(arr));
        }
    }

    public static void selectionSort(ArrayList<Card> list) {
        Card[] arr = new Card[list.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = list.get(i);
        for (int i = arr.length - 1; i > 0; i--) {
            int s = i;
            for (int j = i - 1; j >= 0 ; j--) {
                if (arr[j].compareTo(arr[s]) > 0) {
                    s = j;
                }
            }
            Card temp = arr[i];
            arr[i] = arr[s];
            arr[s] = temp;
            System.out.println (Arrays.toString(arr));
        }
        list.clear();
        for (Card elem : arr) list.add(elem);
    }
}
