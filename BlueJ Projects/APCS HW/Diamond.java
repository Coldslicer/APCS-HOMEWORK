public class Diamond {
    public static void main(String[] args) {
        Diamond d = new Diamond();
        d.printDiamond(5);
        d.printDiamond(6);
        d.printDiamond(20);
        d.printDiamond(-10);
        d.printDiamond(60);
    }
    public void printDiamond(int n) {
        if (n < 1 || n > 40) {
            System.err.printf("size %d is invalid\n",n);
            return;
        }
        for (int i = -(n-1); i <= n-1; i++) {
            for (int j = 0; j < (i < 0 ? -i : i); j++) System.out.print(" ");
            for (int j = 0; j < 2 * (n - (i < 0 ? -i : i)) - 1; j++) System.out.print("*");
            System.out.println();
        }
    }
}
