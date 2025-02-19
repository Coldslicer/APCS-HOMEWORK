public class PrimesSearch {
    public static void main(String[] args) {
        int n = 100;
        long[] found = new long[n];
        found[0] = 1;
        int numFound = 1;
        long i = 2;
        while (numFound < n) {
            boolean isPrime = true;
            for (int j = 1; j < numFound; j++) {
                if (i % found[j] == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                found[numFound] = i;
                numFound++;
            }
            i++;
        }
        System.out.printf("The %dth prime number is %d\n",n,found[n-1]);
    }
}
