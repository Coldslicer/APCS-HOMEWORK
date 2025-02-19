/*
 * Sharvil Phadke P5
 * Nov 17
 * Working
 * HW question: List some reasons why arrays are more efficient than lists. How do lists break past the limitations
 * of arrays, and what sacrifices are made? If Java Strings are analogous to arrays, what are C++ Strings analogous to?
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;

public class P5_Phadke_Sharvil_Compact {
    {
        Scanner s;
        try {
            s = new Scanner(new File("compact.txt"));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            s = null;
        }
        int length = 0;
        try {
            while (s.hasNext()) length += 1 + 0*s.nextInt();
        } catch (InputMismatchException ime) {
            System.err.printf("Error reading token \"%s\"\n",s.next());
            throw ime;
        }
        int[] nums = new int[length];
        int i = 0;
        s.close();
        try {
            s = new Scanner(new File("compact.txt"));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            s = null;
        }
        try {
            while (s.hasNext()) {
                nums[i] = s.nextInt();
                i++;
            }
        } catch (InputMismatchException ime) {
            System.err.printf("Error reading token \"%s\"\n",s.next());
            throw ime;
        }
        s.close();
        List list = new ArrayList(length);
        for (int n : nums) list.add(n);
        this.list = list;
        this.arr = nums;
    }
    private List<Integer> list;
    private int[] arr;
    
    public static void main(String[] args) {
        var o = System.out;
        P5_Phadke_Sharvil_Compact c = new P5_Phadke_Sharvil_Compact();
        o.printf("Array:\n\tBefore: %s\n\tAfter: %s\n",new IntArrayWrapper(c.arr),new IntArrayWrapper(c.compactArray(c.arr)));
        o.printf("Array:\n\tBefore: %s\n\tAfter: %s\n",c.list,c.compactArrayList(c.list));
    }
    
    private static class IntArrayWrapper {
        public int[] data;
        public IntArrayWrapper(int[] data) { 
            this.data = data; 
        }
        @Override 
        public String toString() {
            StringBuilder s = new StringBuilder();
            
            for (Object obj : data) {
                s.append(obj.toString());
                s.append(" ");
            }
            return s.toString().substring(0,s.length()-1);
        }
    }
    
    public int[] compactArray(int[] arr) {
        arr = arr.clone();
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[j] = arr[i];
                arr[i] = 0;
                j++;
            }
        }
        return arr;
    }
    /*
    //old method with bubble sort
    
    public int[] compactArray(int[] arr) {
        arr = arr.clone();
        boolean cont = true;
        while (cont) {
            cont = false;
            for (int i = 0; i < arr.length-1; i++) if (arr[i] == 0 && arr[i+1] != 0) {
                swap(arr, i, i+1);
                cont = true;
            }
        }
        return arr;
    }*/
    
    private void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
    
    public List compactArrayList(List<Integer> arr) { 
        List l = new ArrayList(arr);
        l.removeIf(n -> (int) n == 0); 
        return l;
    }
}
