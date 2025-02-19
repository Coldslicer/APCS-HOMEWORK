
public class P5_Phadke_Sharvil_RecursiveReverseAndPalindrome
{
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        do {
            System.out.print("Enter a string to be reversed:\t");
            System.out.println(recursiveStringReverse(s.nextLine()));
            System.out.print("Enter a string to check if it is a palindrome:\t");
            System.out.printf("it is %sa palindrome\n",recursiveIsPalindrome(s.nextLine()) ? "" : "not ");
            System.out.print("continue? (yes/no)");
        } while (s.nextLine().equals("yes"));
    }
    public static String recursiveStringReverse(String s) {
        if (s.length() <= 1) return s;
        return s.charAt(s.length()-1) + recursiveStringReverse(s.substring(0,s.length()-1));
    }
    public static boolean recursiveIsPalindrome(String s) {
        if (s.length() <= 1) return true;
        boolean frontIsLetter = Character.isLetter(s.charAt(0)), backIsLetter = Character.isLetter(s.charAt(s.length()-1));
        if (frontIsLetter && backIsLetter) return Character.toUpperCase(s.charAt(0)) == Character.toUpperCase(s.charAt(s.length()-1)) && recursiveIsPalindrome(s.substring(1,s.length()-1));
        return recursiveIsPalindrome(s.substring(frontIsLetter ? 0 : 1,backIsLetter ? s.length() : s.length()-1));
    }
}
