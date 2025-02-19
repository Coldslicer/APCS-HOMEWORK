/*
 * Sharvil Phadke P5
 * still working
 * hw question:
 *      Name some common mistakes while making binary search. How do the provided
 *      test cases ensure the student has not made one of these errors?
 * 
 *      What are some common mistakes that cannot be tested with testcases and why? 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class P5_Phadke_Sharvil_StoreDriver {  
    public static void main(String[] args) {
        Store s = new Store("file50.txt");
        s.sort();
        s.displayStore();
        s.testSearch();
    }
}


class Item implements Comparable<Item> {
    private int myId;
    private int myInv;

    /**
    *  Constructor for the Item object
    *
    * @param  id   id value
    * @param  inv  inventory value
    */  
    public Item(int id, int inv){
        myId = id;
        myInv = inv;
    }

    /**
    *  Gets the id attribute of the Item object
    *
    * @return    The id value
    */  
    public int getId(){
        return myId;
    }

    /**
    *  Gets the inv attribute of the Item object
    *
    * @return    The inv value
    */  
    public int getInv(){
        return myInv;
    }

    /**
    *  Compares this item to another item based on id number. Returns the
    *  difference between this item's id and the other item's id. A
    *  difference of zero means the items' ids are equal in value.
    *
    * @param  other  Item object to compare to
    * @return        positive int if myId > other.myId
    *                0 if myId == other.myId
    *                negative int if myId < other.myId
    */  
    public int compareTo(Item other){
        return myId - other.getId();
    }

    /**
    *  Compares the Item to the specified object
    *
    * @param  otherObject  Item object to compare to
    * @return              true if equal, false otherwise
    */  
    public boolean equals(Item other){
        return myId == other.getId();
    }

    /**
    *  Overrides the default toString() of Object.
    *  Returns a String representation of this object. It's up to you
    *  exactly what this looks like.
    */
    public String toString(){
        return String.format("%d of item %d", myInv,  myId);
    }
}   


class Store {

    private ArrayList <Item> myStore = new ArrayList <Item>();

    /**
    *  Creates a Store object from data stored in the given file name
    *
    *  @param  fName  name of the file containing id/inv pairs of data
    */
    public Store(String fName){
        try {
            loadFile(fName);
        } catch(FileNotFoundException fnfe) {
            System.err.println("Invalid file location");
        } catch (InputMismatchException ime) {
            System.err.println("unable to parse file");
        }
    }

    /**
    *  Reads a file containing id/inv data pairs one pair per line. 
    *
    *  @param  inFileName  name of file containing id/inv pairs of data
     * @throws FileNotFoundException 
    */
    private void loadFile(String inFileName) throws FileNotFoundException, InputMismatchException { // these are intentional
        File f = new File(inFileName);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) myStore.add(new Item(s.nextInt(), s.nextInt()));
        s.close();
    }

    /**
    *  Prints the store contents in the format shown below
    *  Line #       Id          Inv
    *  1            184         14
    *  2            196         60
    */
    public void displayStore(){
        StringBuilder b = new StringBuilder();
        b.append(String.format("%-10s%-10s%-10s\n\n", "Line #","Id","Inv"));
        for (int i = 0; i < myStore.size(); i++) {
            Item im = myStore.get(i);
            b.append(String.format("%-10d%-10d%-10d\n",i + 1,im.getId(),im.getInv()));
            if (i % 10 == 9) b.append('\n');
        }
        System.out.println(b);
    }

    /**
    *  Sorts the store ArrayList using recursive mergesort
    */
    public void sort(){
        mergeSort(myStore);
    }

    /**
   *  Takes in entire vector, but will merge the following sections
   *  together:  Left sublist from a[first]..a[mid], right sublist from
   *  a[mid+1]..a[last].  Precondition:  each sublist is already in
   *  ascending order
   *
   * @param  a      reference to an array of comparables to be sorted
   * @param  first  starting index of range of values to be sorted
   * @param  mid    midpoint index of range of values to be sorted
   * @param  last   last index of range of values to be sorted
   */
  private <T extends Comparable<T>> void merge(List<T> a, int first, int mid, int last) {
    Queue<T> merged = new LinkedList<>();
    for (int i, p1 = i = first, p2 = mid + 1; i <= last; i++) {
      T e1 = p1 <= mid ? a.get(p1) : null, e2 = p2 <= last ? a.get(p2) : null;
      if (p2 > last || p1 <= mid && e1.compareTo(e2) <= 0) {
        merged.add(e1);
        p1++;
      } else {
        merged.add(e2);
        p2++;
      }
    }
    for (int i = first; i <= last; i++) {
      a.set(i,merged.remove());
    }
  }

  /**
   *  Recursive mergesort of an array of comparables
   *
   * @param  a      reference to an array of comparables to be sorted
   * @param  first  starting index of range of values to be sorted
   * @param  last   ending index of range of values to be sorted
   */
  public <T extends Comparable<T>> void mergeSort(List<T> a, int first, int last) {
    if (first < last) {
      int mid = (last + first) / 2;
      mergeSort(a, first, mid);
      mergeSort(a, mid + 1, last);
      merge(a, first, mid, last);
    }
  }

  public <T extends Comparable<T>> void mergeSort(List<T> a) {
    mergeSort(a, 0, a.size()-1);
  }

  public void  testSearch() {  
    int  idToFind;  
    int  invReturn;  
    int  index;  
    Scanner in =  new  Scanner(System.in);  

    System.out.println("Testing search algorithm\n");  
    do {  
        System.out.println();  
        System.out.print("Enter Id value to search for (-1 to quit) ---> ");  
        idToFind = in.nextInt();  
        index = bsearch(new  Item(idToFind,0));  
        //recursive version call  
        //index = bsearch (new  Item(idToFind, 0), 0, myStore.size()-1);  
        System.out.print("Id # " + idToFind);  
        if  (index == -1){  
            System.out.println(" No such part in stock");  
        }else{  
            System.out.println(" Inventory = " + myStore.get(index).getInv());  
        }  
    }  while  (idToFind >= 0);  
    }

/**  
* Searches the myStore ArrayList of Item Objects for the specified  
* item object using a iterative binary search algorithm  
*  
* @param idToSearch Item object containing id value being searched for  
* @return index of Item if found, -1 if not found  
*/  
private int bsearch(Item idToSearch) {  
    int first = 0, last = myStore.size() - 1;
    while (first < last) {
        int mid = (first + last) / 2;
        int c = idToSearch.compareTo(myStore.get(mid));
        if (c > 0) first = mid + 1;
        else if (c < 0) last = mid - 1;
        else return mid;
    }
    if (myStore.get(first).compareTo(idToSearch) == 0) return first;
    return -1;
}

    /**  
    * Searches the specified ArrayList of Item Objects for the specified  
    * id using a recursive binary search algorithm  
    *  
    * @param idToSearch Id value being search for  
    * @param first Starting index of search range  
    * @param last Ending index of search range  
    * @return index of Item if found, -1 if not found  
    */  
  private int bsearch(Item idToSearch, int first, int last){  
    if (first < last) {
        int mid = (first + last) / 2;
        int comp = idToSearch.compareTo(myStore.get(mid));
        if (comp > 0) return bsearch(idToSearch, mid + 1, last);
        if (comp < 0) return bsearch(idToSearch, first, mid - 1);
        return mid;
    } else {
        if (myStore.get(first).compareTo(idToSearch) == 0) return first;
        return -1;
    }
  }
}