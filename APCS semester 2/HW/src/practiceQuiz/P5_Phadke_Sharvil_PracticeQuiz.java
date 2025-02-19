/*
 * Sharvil Phadke P5
 * Feb 2
 * hw question:
 * 		in insertion sort, should you be using swaps?
 * 		Compare runtime with swaps or copying
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class P5_Phadke_Sharvil_PracticeQuiz {

	public static void main(String[] args) {

		String[] words = { "abe", "bat", "car", "cat", "dab", "elf", "fig", "get" };
		ArrayList<String> wordList = new ArrayList<String>();

		for (String word : words) {
			wordList.add(word);
		}

		System.out.println("***** Selection Sort *****");
		Collections.shuffle(wordList);
		System.out.println("Before sorting: " + wordList);
		selectionSort(wordList);
		System.out.println("After sorting:  " + wordList);

		int[] intArray = generateRandomIntArray(8);
		
		System.out.println("\n***** Midsertion Sort *****");
		System.out.println("Before sorting: " + Arrays.toString(intArray));
		midsertionSort(intArray);
		System.out.println("After sorting:  " + Arrays.toString(intArray));

		intArray = generateRandomIntArray(8);
		
		System.out.println("\n***** V Sort *****");
		System.out.println("Before sorting: " + Arrays.toString(intArray));
		vSort(intArray);
		System.out.println("After sorting:  " + Arrays.toString(intArray));
	}
	

	private static int[] generateRandomIntArray(int length) {
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
        	arr[i] = (int)(Math.random() * length);
        }
		return arr;
	}
	
	private static void selectionSort(ArrayList<String> arr) {
		for (int i = arr.size() - 1; i > 0; i--) {
			int max = 0;
			for (int j = 1; j <= i; j++) {
				if (arr.get(j).compareTo(arr.get(max)) < 0) {
					max = j;
				}
			}
			String temp = arr.get(i);
			arr.set(i,arr.get(max));
			arr.set(max,temp);
		}
	}

	public static void midsertionSort(int[] arr) {
		// sort pairs
		for (int i = 0; i <= arr.length / 2 + 1; i++) {
			if (arr[i] > arr[arr.length-i-1]) {
				swap(arr,i,arr.length-i-1);
			}
		}

		System.out.println(Arrays.toString(arr));

		int start = arr.length / 2, end = arr.length - arr.length / 2;
		boolean doStart = true;
		while (start != 0 && end != arr.length - 1) {
			if (doStart) {
				int copy = arr[--start], loc = start;
				while (loc < end && arr[loc + 1] < copy) {
					arr[loc] = arr[++loc];
				}
				arr[loc] = copy;
				
			} else {
				int copy = arr[++end], loc = end;
				while (loc > start && arr[loc - 1] > copy) {
					arr[loc] = arr[--loc];
				}
				arr[loc] = copy;
			}
			doStart = !doStart;
			System.out.println(Arrays.toString(arr));
		}
	}

	public static void vSort(int[] arr) {
		for (int i = 1; i < arr.length/2; i++) {
            int copy = arr[i], loc = i;

            while (loc > 0 && copy <= arr[loc - 1]) {
                arr[loc] = arr[loc - 1];
                loc--;
            }

            arr[loc] = copy;
            System.out.println(Arrays.toString(arr));
        }

		for (int i = arr.length - 1; i > arr.length/2; i--) {
			int max = arr.length/2;
			for (int j = arr.length/2; j <= i; j++) {
				if (arr[j] > arr[max]) max = arr[j];
			}
			swap(arr,i,max);
			System.out.println(Arrays.toString(arr));
		}
	}

	public static void swap(int[] arr, int i1, int i2) {
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
}
