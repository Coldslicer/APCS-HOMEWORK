import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class CommonList {
    public static void main(String[] args) {
        var o = System.out;
        List<Integer> a = randomList(25,-5,10);
        List<Integer> b = randomList(25,-5,10);
        
        
        o.print("BEFORE\na = ");
        printList(a);
        o.print("b = ");
        printList(b);
        
        removeNegatives(a);
        removeNegatives(b);
        
        o.print("AFTER REMOVING NEGATIVES\na = ");
        printList(a);
        o.print("b = ");
        printList(b);
        
        System.out.println("COMMON LIST");
        printList(commonList(a,b));
    }
    
    private static Random r = new Random();
    
    public static void printList(List<Integer> nums) {
        StringBuilder output = new StringBuilder();
        for (int i : nums) {
            output.append(i);
            output.append(" ");
        }
        System.out.printf("[%s]\n",output.toString().trim());
    }
    
    public static List<Integer> randomList(int n, int a, int b) {
        List output = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) output.add(r.nextInt(a,b+1));
        return output;
    }
    
    public static void removeNegatives(List<Integer> nums) {
        Iterator<Integer> i = nums.iterator();
        while (i.hasNext()) if (i.next() < 0) i.remove();
    }
    
    public static List<Integer> commonList(List<Integer> list1, List<Integer> list2) {
        List<Integer> output = new ArrayList<Integer>();
        output.addAll(list1);
        output.addAll(list2);
        Set encountered = new HashSet(); // this can be a List as well, I used a set for efficiency
        Iterator<Integer> i = output.iterator();
        while (i.hasNext()) {
            int num = i.next();
            if (encountered.contains(num)) i.remove();
            encountered.add(num);
        }
        return output;
    }
}
