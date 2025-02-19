import java.util.*;
import java.util.function.Function;

/**
 *  Driver program for the Sorts class.
 *
 *  modified by Jason Quesenberry and Nancy Quesenberry
 *  January 16, 2006
 *
 * @author     G. Peck
 * @created    July 18, 2002
 */
public class P5_Phadke_Sharvil_SortStep {
  private Scanner console;
  private ArrayList <Comparable> myArray;
  private P5_Phadke_Sharvil_Sorts mySorts;
  private String listType;

  /**
   *  Constructor for the SortStep object
   */
  public P5_Phadke_Sharvil_SortStep(){
    console = new Scanner(System.in);
    mySorts = new P5_Phadke_Sharvil_Sorts();
    myArray = null;
    listType = "Integer";
  }

  public static void main(String[] args) {
  	P5_Phadke_Sharvil_SortStep s = new P5_Phadke_Sharvil_SortStep();
  	s.sortMenu();
  }

  /**
   *  Asks the user to select a sorting algorithm, fills the array
   *  with an amount of random integer data chosen by the user, calls
   *  the sorting algorithm, and gives an option of printing out the
   *  data after it has been sorted.
   */
  public void sortMenu(){
    String choice;
    String print;

    do{
      System.out.println();
      System.out.println("Sorting algorithm menu");
      System.out.println();
      System.out.println("(0) Radix sort");
      System.out.println("(1) Bubble sort");
      System.out.println("(2) Selection sort");
      System.out.println("(3) Insertion sort");
      System.out.println("(4) Recursive mergesort");
      System.out.println("(5) Fill with random Integers");
      System.out.println("(6) Fill with sorted Integers");
      System.out.println("(7) Fill with reverse sorted Integers");
      System.out.println("(8) Fill with almost sorted Integers");
      System.out.println("(Q) Quit");
      System.out.println();
      System.out.print("Choice ---> ");
      choice = console.next() + " ";
      if ('0' <= choice.charAt(0) && choice.charAt(0) <= '8'){
        System.out.println();

        mySorts.setStepCount(0);
        if ('0' <= choice.charAt(0) && choice.charAt(0) <= '4') resetArray();
        long startTime = System.currentTimeMillis();
        switch (choice.charAt(0)){
            case '0':
              // assuming only integers or strings for now
              mySorts.radixSort(myArray, myArray.get(0) instanceof Integer ? ((o) -> (Integer) o) : ((o) -> ((String)o).compareTo("")));
              break;
            case '1':
              mySorts.bubbleSort(myArray);
              break;
            case '2':
              mySorts.selectionSort(myArray);
              break;
            case '3':
              mySorts.insertionSort(myArray);
              break;
            case '4':
              int last = myArray.size() - 1;
              mySorts.mergeSort(myArray, 0, last);
              break;
            case '5':
        	  listType = "Random";
              break;
            case '6':
              listType = "Sorted";
              break;
            case '7':
              listType = "Reversed";
              break;
            case '8':
              listType = "Almost";
              break;

        }
        if ('0' <= choice.charAt(0) && choice.charAt(0) <= '4'){
        	System.out.println();
        	System.out.println("Array sorted to:");
	        screenOutput();
	        System.out.println();
	        System.out.println("# steps = " + mySorts.getStepCount());
	        System.out.println();
          System.out.println("Time taken: "+(System.currentTimeMillis() - startTime));
        }
      }
    } while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
  }

  /**
   *  Initializes myArray with random integers in the range
   *  1..largestInt
   *
   * @param  numInts     number of integers to generate (size of
   *      myArray)
   * @param  largestInt  largest possible random integer to create
   */
  private void fillArrayWithInts(){

    System.out.print("How many numbers do you wish to generate? ");
    int numInts = console.nextInt();
    System.out.print("Largest integer to generate? ");
    int largestInt = console.nextInt();

    Random randGen = new Random();
    myArray = new ArrayList <Comparable>();

    for (int loop = 0; loop < numInts; loop++){
      Integer x = new Integer(randGen.nextInt(largestInt) + 1);
      myArray.add(x);
    }
  }

  /**
   *  Initializes myArray with a few hard-coded Strings
   *
   * @param  numInts     number of integers to generate (size of
   *      myArray)
   * @param  largestInt  largest possible random integer to create
   */
  @Deprecated
  private void fillArrayWithStrings(){
    myArray = new ArrayList <Comparable>();
    myArray.add("eat");
    myArray.add("steaks");
    myArray.add("juicy");
    myArray.add("huge");
    myArray.add("dogs");
    myArray.add("big");
  }

  /**
   *  reset the array for the next sort
   */
  private void resetArray(){
    if (myArray == null || listType.equals("Random")){
    	fillArrayWithInts();
    } else {
      System.out.print("How many numbers do you wish to generate? ");
      int numInts = console.nextInt();
      
      myArray = new ArrayList<Comparable>();
      for (int i = 0; i < numInts; i++) {
        myArray.add(i);
      }

      if (listType.equals("Reversed")) myArray = new ArrayList<>(myArray.reversed());
      if (listType.equals("Almost")) myArray.add(myArray.remove((int)(Math.random() * myArray.size())));

    }
    System.out.println();
    System.out.println("Array reset to:");
    screenOutput();
  }

  /**
   *  prints out the contents of the array in tabular form, 12 columns
   */
  private void screenOutput(){
    for (int loop = 0; loop < myArray.size(); loop++){
      if (loop % 12 == 0){
        System.out.println();
      }
      System.out.print(myArray.get(loop) + "  ");
    }
    System.out.println();
  }
}
  /*
 * P5 Phadke, Sharvil
 * Jan 16 2025
 * Working
 * HW Question:
 *    what is the time complexity of the sorts we've done so far
 *      Answer: O(n^2)
 *    what is one assumption we have to make to sort a list of comparables?
 *      Possible answer: that if A > B and B > C, then A > C
 */

/**
 *  Description of the Class
 *
 * @author     Your Name Here
 * @created    Month Day, Year
 */
class P5_Phadke_Sharvil_Sorts {
  private long steps;

  /**
   *  Description of Constructor
   *
   * @param  list  Description of Parameter
   */
  public P5_Phadke_Sharvil_Sorts() {
    steps = 0;
  }

  /**
   *  Description of the Method
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public <T extends Comparable<T>> void bubbleSort(List<T> list) {
    steps += 4; // alloc, call, assign, compare
    for (int i = list.size(); i > 0; i--) {
      steps += 3; // alloc, call, compare
      for (int j = 1; j < i; j++) {
        steps  += 5; // 3 calls, sub, compare
        if (list.get(j - 1).compareTo(list.get(j)) > 0) {
          steps += 2; // sub, call
          swap(list, j - 1, j);
        }
        steps += 2; // increment, compare
      }
      steps += 2; // decrement, compare
    }
  }

  /**
   *  Description of the Method
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public <T extends Comparable<T>> void selectionSort(List<T> list) {
    steps += 5; // alloc, call, sub, assign, compare
    for (int i = list.size() - 1; i >= 0; i--) {
      steps += 2; // alloc, assign
      int m = i;
      steps += 3; // alloc, assign, compare
      for (int j = i; j >= 0; j--) {
          steps += 4; // 3 calls, compare
          if (list.get(j).compareTo(list.get(m)) > 0) {
            steps += 1; // assign
            m = j;
          }
          steps += 2; // decrement, compare
      }
      steps += 1; // call
      swap(list, m, i);
      steps += 2; // decrement, compare
    }
  }

  /**
   *  Description of the Method
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public <T extends Comparable<T>> void insertionSort(List<T> list) {
    steps += 3; // alloc, call, assign
    int l = list.size();
    steps += 3; // alloc, assign, compare
    for (int i = 1; i < l; i++) {
      steps += 3; // alloc ptr, call, assign
      T t = list.get(i);

      for (int j = i; j >= -1; j--) {
        if (j > 0) steps += 1; // compare
        else steps += 6; // compare, sub, 2 calls, compare, logical op
        if (j > 0 && list.get(j - 1).compareTo(t) > 0) {
          steps += 3; // sub, 2 calls
          list.set(j, list.get(j - 1));
        } else {
          steps++; // call
          list.set(j,t);
          break;
        }
        steps += 2; // decrement, compare
      }
      steps += 2; // increment, compare
    }
  }

  
  public static void main(String[] args) {
    for (int n = 0; n < 10; n++) {
      P5_Phadke_Sharvil_Sorts s = new P5_Phadke_Sharvil_Sorts();
      List<Integer> l1 = new ArrayList<>();
      List<Integer> l2 = new ArrayList<>();
      Random r = new Random();
      for (int i = 0; i < 10; i++) {
        l1.add(r.nextInt(100));
        l2.add(r.nextInt(100));
      }
      l1.sort(null);
      l2.sort(null);

      l1.addAll(l2);
      System.out.println(l1);
      s.merge(l1,0,l1.size()/2 - 1,l1.size()-1);
      System.out.println(l1);
    }
  }
 /**
   *  Takes in entire vector, but will merge the following sections
   *  together:  Left sublist from a[first]..a[mid], right sublist from
   *  a[mid+1]..a[last].  Precondition:  each sublist is already in
   *  ascending order
   *
   * @param  a      reference to an array of integers to be sorted
   * @param  first  starting index of range of values to be sorted
   * @param  mid    midpoint index of range of values to be sorted
   * @param  last   last index of range of values to be sorted
   */
  private <T extends Comparable<T>> void merge(List<T> a, int first, int mid, int last) {
    steps += 3; // alloc, construct, assign
    Queue<T> merged = new LinkedList<>();
    steps += 8; // 3 allocs, add, 3 assigns, compare
    for (int i, p1 = i = first, p2 = mid + 1; i <= last; i++) {
      steps += 8; // 2 allocs, 2 compares, 2 calls, 2 assigns
      T e1 = p1 <= mid ? a.get(p1) : null, e2 = p2 <= last ? a.get(p2) : null;
      if (p2 > last) steps ++; //compare
      else if (p1 > mid) steps += 3; // compare, or
      else steps += 6; // 3 compares, call, and, or
      if (p2 > last || p1 <= mid && e1.compareTo(e2) <= 0) {
        steps += 1; // call
        merged.add(e1);
        steps += 1; // inc
        p1++;
      } else {
        steps += 1; // call
        merged.add(e2);
        steps += 1; // inc
        p2++;
      }
      steps += 2; // inc, compare
    }
    steps += 2; // alloc, assign
    for (int i = first; i <= last; i++) {
      steps += 2; // 2 calls
      a.set(i,merged.remove());
      steps += 2; // inc, compare
    }
  }

  /**
   *  Recursive mergesort of an array of integers
   *
   * @param  a      reference to an array of integers to be sorted
   * @param  first  starting index of range of values to be sorted
   * @param  last   ending index of range of values to be sorted
   */
  public <T extends Comparable<T>> void mergeSort(List<T> a, int first, int last) {
    if (first < last) {
      steps += 4; // alloc, add, div, assign
      int mid = (last + first) / 2;
      steps++; // call
      mergeSort(a, first, mid);
      steps += 2; // add, call
      mergeSort(a, mid + 1, last);
      steps++; // call
      merge(a, first, mid, last);
    }
  }

  public static int RADIX = 0x10;

  public <T> void radixSort(ArrayList<T> arr, Function<Object,Integer> getValue) {
    Object[] newArr = this.<Object>radixSortArray(arr.toArray(), getValue);
    arr.clear();
    for (Object elem : newArr) arr.add((T)elem);
  }

    public <T> T[] radixSortArray(T[] arr, Function<T,Integer> getValue) {
        steps += 3; // alloc, construct, assign
        int[] tags = new int[arr.length];
        
        steps += 3; // alloc, assign, compare
        for (int i = 0; i < arr.length; i++) {
          steps += 2; // call, assign
          tags[i] = getValue.apply(arr[i]);
          steps += 2; // inc, compare
        }

        steps += 2; // alloc, assign
        int min = Integer.MAX_VALUE;
        
        for (int n : tags) {
          steps += 2; // foreach logic, call
          min = Math.min(n, min);
        }

        steps += 3; // alloc, assign, compare
        for (int i = 0; i < tags.length; i++) {
          steps += 1; // subequals
          tags[i] -= min;
          steps += 2; // compare, inc
        }

        steps += 2; // alloc, assign
        int max = Integer.MIN_VALUE;
        for (int n : tags) {
            steps += 2; // foreach logic, call
            max = Math.max(n, max);
        }

        steps += 2; // alloc, assign
        int digits = 1;
        steps += 3; // alloc, assign, compare
        for (int i = 1; i < max; i *= RADIX) {
            steps += 1; // inc
            digits++;
            steps += 2; // multequals, comp
        }

        steps += 3; // alloc, assign, compare
        for (int i = 0; i < digits; i++) {
            steps += 1; // call
            countingSortByDigit(arr, tags, i, RADIX);
            steps += 2; // inc, comp
        }
        return arr;
    }

    @SuppressWarnings("unchecked")
    public <T> void countingSortByDigit(T[] arr, int[] tags, int digit, int radix) {
        steps += 3; // alloc, construct, assign
        Queue<T>[] objBuckets = new Queue[radix];
        steps += 3; // alloc, construct, assign
        Queue<Integer>[] tagBuckets = new Queue[radix];

        // all relevant queue operations are O(1) for linked lists

        steps += 3; // alloc, assign, compare
        for (int i = 0; i < radix; i++) {
            steps += 2; // constuct, assign
            objBuckets[i] = new LinkedList<T>();
            steps += 2; // constuct, assign
            tagBuckets[i] = new LinkedList<Integer>();
        }

        steps += 3; // alloc, assign, comp
        for (int i = 0; i < arr.length; i++) {
            steps += 3; // alloc, call, assign
            int loc = extractDigit(tags[i], digit, radix);
            steps++; // call
            objBuckets[loc].add(arr[i]);
            steps++; // call
            tagBuckets[loc].add(tags[i]);
        }

        // reconstrut arr and tags
        steps += 2; // alloc, assign
        int i = 0;
        for (Queue<T> bucket : objBuckets) {
            steps += 1; // foreach logic
            while (!bucket.isEmpty()) {
                steps += 2; // call, inv
                steps += 3; // inc, call, assign
                arr[i++] = bucket.remove();
            }
        }

        steps++; // assign;
        i = 0;
        for (Queue<Integer> bucket : tagBuckets) {
            steps += 1; // foreach logic
            while (!bucket.isEmpty()) {
                steps += 2; // call, inv
                steps += 3; // inc, call, assign
                tags[i++] = bucket.remove();
            }
        }
    }


    public int extractDigit(int num, int digit, int radix) {
        steps += 4; // call, cast, div, mod
        // pow is assumed O(1) or O(b) which do not contribute a power of N
        return (num / (int) Math.pow(radix, digit)) % radix;
    }


  /**
   *  Accessor method to return the current value of steps
   *
   */
  public long getStepCount() {
    return steps;
  }

  /**
   *  Modifier method to set or reset the step count. Usually called
   *  prior to invocation of a sort method.
   *
   * @param  stepCount   value assigned to steps
   */
  public void setStepCount(long stepCount){
    steps = stepCount;
  }

   /**
   *  Interchanges two elements in an ArrayList
   *
   * @param  list  reference to an array of integers
   * @param  a     index of integer to be swapped
   * @param  b     index of integer to be swapped
   */
  public <T extends Comparable<T>, L extends List<T>> void swap(L list, int a, int b) {
    steps += 3; // alloc, call, assign
    T temp = list.get(a);
    steps += 2; // 2 calls
    list.set(a,list.get(b));
    steps += 1; // call
    list.set(b,temp);
  }
}


