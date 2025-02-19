/*
 * P5 Phadke Sharvil
 * Jan 22 2025
 * Working
 * HW Question:
 * 		The result of a merge is only sorted under what conditions?
 * Answer:
 *  	The two lists must first be sorted
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("all")
public class P5_Phadke_Sharvil_Merge {
 
	void selectionSort(ArrayList <Comparable> list) {
		for (int i = list.size() - 1; i >= 0; i--) {
			int max = i;
			for (int j = 0; j < i; j++) {
				if (list.get(j).compareTo(list.get(max)) > 0) {
					max = j;
				}
			}
			swap(list, i, max);

		}
	}

	<T, L extends List<T>> void swap(L l, int i1, int i2) {
		T temp = l.get(i1);
		l.set(i1,l.get(i2));
		l.set(i2, temp);
	}
 
	/**
	 *  Write a merge method to merge two sorted lists.
	 *
	 *  Preconditions: Lists A and B are sorted in nondecreasing order.
	 *  Action:        Lists A and B are merged into one list, C.
	 *  Postcondition: List C contains all the values from
	 *                 Lists A and B, in nondecreasing order.
	 */
	public void merge (ArrayList <Comparable> a, ArrayList <Comparable> b, ArrayList <Comparable> c) {
		int aSize, bSize, cSize = (aSize = a.size()) + (bSize = b.size());
		for (int i, j, k = j = i = 0; i < cSize; i++) {
			if (k >= bSize || j < aSize && a.get(j).compareTo(b.get(k)) <= 0) {
				c.add(a.get(j));
				j++;
			} else {
				c.add(b.get(k));
				k++;
			}
		}
	}

	/**
	*  Write a method to
	*    - Ask the user how many numbers to generate
	*    - Ask the user to enter the largest integer to generate
	*    - Initialize an ArrayList of random Integers from 1 to largestInt
	*	- Return the ArrayList
	*
	* @return  an ArrayList of size specified by the user filled
	*          with random numbers
	*/
	public ArrayList <Comparable> fillArray() {
		Scanner s = new Scanner(System.in);
		int size, max;
		try {
			System.out.print("Enter the size of the list: ");
			size = s.nextInt();
			System.out.print("Enter the maximum element value: ");
			max = s.nextInt();
		} catch (InputMismatchException ime) {
			s.close();
			ime.printStackTrace();
			return null;
		}
		ArrayList<Comparable> output = new ArrayList<>(size);
		
		for (int i = 0; i < size; i++) output.add((int) (Math.random() * (max + 1)));

		return output;
	}

	/**
	*  Write a method to print out the contents of the ArrayList
	*  in tabular form, NUM_COLS columns.  You can use the \t escape character
	*  or use printf to format using fields. Thus, you should go to the
	*  next line every NUM_COLS elements.
Example:
98	7	8	56	45	6	4	3	2	1	9	888	7	7	6	5	4	3	2	9
7	6	4	8	6	7	9	0	6	8	7	4	3	7	2	4	7	6	100	68
6	4	3	8	5	7	5	67	9	7

	*/

	private static final int NUM_COLS = 10;
	public void screenOutput(ArrayList <Comparable> temp) {
		int numRows = temp.size() / NUM_COLS;
		for (int i = 0; i < numRows; i++) {
			StringBuilder b = new StringBuilder();
			for (int j = 0; j < NUM_COLS; j++) {
				b.append(temp.get(j + NUM_COLS * i));
				b.append('\t');
			}
			System.out.println(b);
		}
		StringBuilder b = new StringBuilder();
		int numLeft = temp.size() % NUM_COLS;
		for (int i = 0; i < numLeft; i++) {
			b.append(temp.get(numRows * NUM_COLS + i));
			b.append('\t');
		}
		System.out.println(b);

		
	}
}

