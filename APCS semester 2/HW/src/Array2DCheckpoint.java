import java.util.Arrays;

public class Array2DCheckpoint {
	// write a method printGrid that prints the array in this format:
	// (use printf with left aligned columns of width 3)
	//	1  5  7  4  33
	//	2  3  6  8  29
	//	13 10 12 19 21
	//	22 31 42 16 34
	public static void printGrid(int[][] arr) {
		StringBuilder b = new StringBuilder();
		for (int[] row : arr) {
			for (int n : row) b.append(String.format("%-3d",n));
			b.append('\n');
		}
		System.out.println(b);
	}
	
	// write a method that returns a scores array with the score for each
	// element in the given 2D array, using the following rules:
	// 1. If the element is even, then its score is the sum of
	//    the existing elements to the left and right of it
	// 2. If the element is odd, then its score is the sum of
	//	  the existing elements above and below it
	//
	//	twoDArr			     scores
	//	1  5  7  4  33  -->  2  3  6  40 29 
	//	2  3  6  8  29  -->  3  15 11 35 54
	//	13 10 12 19 21  -->  24 25 29 24 63
	//	22 31 42 16 34  -->  31 10 47 76 16
	public static int[][] getScores(int[][] a) {
		int[][] out = new int[a.length][];
		for (int i = 0; i < a.length; i++) {
			out[i] = new int[a[i].length];
			for (int j = 0; j < a[i].length; j++) out[i][j] = a[i][j] % 2 == 0 ? ((j - 1 >= 0 ? a[i][j-1] : 0) + (j + 1 < a[i].length ? a[i][j+1] : 0)) : ((i - 1 >= 0 && j < a[i].length ? a[i-1][j] : 0) + (i + 1 < a.length && j < a[i].length ? a[i+1][j] : 0));
		}
		return out;
	}
	

	public static void main (String[] args) {		
		
		int[][] twoDArr = {
		//       c0  c1  c2  c3  c4
		/*r0*/	{ 1,  5,  7,  4, 33},
		/*r1*/	{ 2,  3,  6,  8, 29},
		/*r2*/	{13, 10, 12, 19, 21},
		/*r3*/	{22, 31, 42, 16, 34}
		};
		
		System.out.println("deepToString:");
		System.out.println(Arrays.deepToString(twoDArr));
		System.out.println();

		System.out.println("printGrid(twoDArr)");
		printGrid(twoDArr);
		System.out.println();
		
		System.out.println("printGrid(scores)");
		printGrid(getScores(twoDArr));
	}
}
