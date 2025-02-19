import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Lab17_0 {

	public static void main(String[] args) {

		Lab17_0 lab = new Lab17_0();

		ArrayList <Comparable> list = lab.initializeList();
		ArrayList <Comparable> copy = lab.duplicate(list);

		System.out.println("Before Bubble Sort:");
		System.out.println(list);

		lab.bubbleSort(list);	// runs your Bubble Sort code
		Collections.sort(copy);	// runs built-in sorting code
		Collections.reverse(copy);

		System.out.println("After Bubble Sort:");
		System.out.println(list);
		System.out.println(copy.toString().equals(list.toString()) ? "CORRECT" : "NOT SORTED PROPERLY");		
		
		list = lab.initializeList();
		copy = lab.duplicate(list);
		System.out.println("\nBefore Selection Sort:");
		System.out.println(list);

		lab.selectionSort(list);	// runs your Selection Sort code
		Collections.sort(copy);		// runs built-in sorting code

		System.out.println("After Selection Sort:");
		System.out.println(list);
		System.out.println(copy.toString().equals(list.toString()) ? "CORRECT" : "NOT SORTED PROPERLY");		

		list = lab.initializeList();
		copy = lab.duplicate(list);
		System.out.println("\nBefore Insertion Sort:");
		System.out.println(list);

		lab.insertionSort(list);	// runs your Insertion Sort code
		Collections.sort(copy);		// runs built-in sorting code
		Collections.reverse(copy);

		System.out.println("After Insertion Sort:");
		System.out.println(list);
		System.out.println(copy.toString().equals(list.toString()) ? "CORRECT" : "NOT SORTED PROPERLY");		
	}

	/* Write code for a Bubble Sort algorithm that starts at the right side of
	 * of ArrayList of Comparable objects and "bubbles" the largest item to the
	 * left of the list.  The result should be an ArrayList arranged in descending
	 * order.
	*/
	void bubbleSort(ArrayList <Comparable> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = list.size() - 2; j >= i; j--) {
				if (list.get(j).compareTo(list.get(j+1)) < 0) {
					swap(list,j,j + 1);
				}
			}
			
		}
	}

	/* Write code for a Selection Sort algorithm that starts at the left side
	 * of an ArrayList of Comparable objects and searches through the list for
	 * the largest item and then swaps it with the last item in the list.  The
	 * "last item" then becomes the item to its left. The result should be
	 * an ArrayList arranged in ascending order.
	*/
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

	/* Write code for an Insertion Sort algorithm that starts at the left side
	 * of an ArrayList of Comparable objects and inserts the first item (in
	 * position 1) into it's correct place within the first two items...then
	 * inserts the third item into its correct place on the left, then the fourth
	 * item into its correct place on the left, etc, until the last item is
	 * inserted into the list.  Insert items so the result is an ArrayList arranged
	 * in descending order.
	*/
	void insertionSort(ArrayList <Comparable> list) {
		for (int i = 1; i < list.size(); i++) {
			Comparable cur = list.get(i);
			for (int j = i-1; j >= -1; j--) {
				if (j >= 0 && cur.compareTo(list.get(j)) > 0) {
					list.set(j + 1, list.get(j));
				} else {
					list.set(j+1, cur);
					break;
				}
			}
		}
	}

	<T, L extends List<T>> void swap(L l, int i1, int i2) {
		T temp = l.get(i1);
		l.set(i1,l.get(i2));
		l.set(i2, temp);
	}

	ArrayList <Comparable> initializeList() {

		String[] words = {"apple", "orange", "banana", "pear", "peach", "plum",
						  "grape", "cherry", "apricot", "strawberry"};

		ArrayList <Comparable> temp = new ArrayList<Comparable>();
		ArrayList <Comparable> list = new ArrayList<Comparable>();

		for (int i = 0; i < words.length; i++)
			temp.add(words[i]);

		list.clear(); // clear the list before adding to it

		while (temp.size() > 0) {
			list.add(temp.remove((int)(Math.random()*temp.size())));
		}

		return list;
	}
	
	ArrayList <Comparable> duplicate(ArrayList<Comparable> list) {

		ArrayList<Comparable> listCopy = new ArrayList<Comparable>();
		
		Iterator<Comparable> iter = list.iterator();
		
		while(iter.hasNext()){
			listCopy.add(iter.next());
		}
		
		return listCopy;
	}


}
