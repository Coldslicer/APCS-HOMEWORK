import java.util.Arrays;

public class P5_Phadke_Sharvil_MagicSquare {
	/**
	 * Uses the other methods in this class to determine whether or not the
	 * given array is a Magic Square
	 */
	boolean isMagicSquare(int[][] matrix) {
		int expected = sumRow(matrix, 0);
		for (int i = 0; i < matrix.length; i++) if (sumCol(matrix, i) != expected || sumRow(matrix, i) != expected) return false;
		return sumULDiagonal(matrix) == expected && sumURDiagonal(matrix) == expected && unique(matrix);
	}

	/** Returns the sum of values in the given row */
	int sumRow(int[][] matrix, int row) {
		int sum = 0;
		for (int n : matrix[row]) sum += n;
		return sum;
	}

	/** Returns the sum of values in the given column */
	int sumCol(int[][] matrix, int col) {
		int sum = 0;
		for (int i = 0; i < matrix.length; i++) sum += matrix[i][col];
		return sum;
	}

	/** Returns the sum of the upper left diagonal */
	int sumULDiagonal(int[][] matrix) {
		int sum = 0;
		for (int i = 0; i < matrix.length; i++) sum += matrix[i][i];
		return sum;
	}

	/** Returns the sum of the upper right diagonal */
	int sumURDiagonal(int[][] matrix) {
		int sum = 0;
		for (int i = 0; i < matrix.length; i++) sum += matrix[i][matrix.length-i-1];
		return sum;
	}

	/**
	 * Returns whether or not the given matrix is unique.  A matrix is
	 * unique if it is a square matrix and contains each integer from
	 * 1 to MAX*MAX where MAX is the length of either edge of the matrix.
	 * For example, a 3x3 matrix must contain each integer from 1 to 9.
	 */
	boolean unique(int[][] matrix) {
		boolean[] mask = new boolean[matrix.length * matrix.length];
		for (int[] row : matrix) for (int num : row) {
			if (num > mask.length || num < 1 || mask[num-1]) return false;
			mask[num-1] = true;
		}
		return true;
	}

	public static void main(String[] args) {

		/**** Sample arrays used during testing ****/

		// A 4x4 magic square
		int[][] array1 = { {16,  3,  2, 13},
						   { 5, 10,	11,  8},
						   { 9,  6,  7, 12},
						   { 4, 15, 14,  1} };
		
		// A 5x5 magic square
		int[][] array2 = { { 1,  2, 19, 20, 23},
				   		   {18, 16,	 9, 14,  8},
				   		   {21, 11, 13, 15,  5},
				   		   {22, 12, 17, 10,  4},
						   { 3, 24,  7,  6, 25} };

		// A 4x4 semi-magic square (diagonals don't match row/col sums)
		int[][] array3 = { {110,  72,  63, 80 },
						   { 64, 105,  66, 90 },
						   { 81,  88, 100, 56 },
						   { 70,  60,  96, 99 } };

		// a 5x5 non-magical square (bottom row, last col don't add)
		int[][] array4 = { { 11, 15, 24,  3,  8 },
						   {  5, 14, 18, 22,  1 },
						   {  4,  8, 12, 16, 20 },
						   { 23,  2,  6, 10, 19 },
						   { 17, 21,  0,  9, 13 } };

		// Unique only
		int[][] array5 = { {16,  3,  1, 13},
						   { 5, 10,	11,  8},
						   { 9,  6,  7, 12},
						   { 4, 15, 14,  2} };

		// Sums all same but not unique
		int[][] array6 = { { 2, 2, 2, 2},
				   		   { 2, 2, 2, 2},
				   		   { 2, 2, 2, 2},
				   		   { 2, 2, 2, 2} };

		// Random
		int[][] array7 = { { 5, 8, 2, 5 },
						   { 1, 4, 6, 3 },
						   { 9, 4, 2, 1 },
						   { 7, 3, 8, 2 } };
		
		// Not unique b/c not 1 to n^2
		int[][] array8 = { { 1, 12,  8,  4 },
				   		   { 7,  2, 14, 15 },
				   		   { 5, 13,  9,  0 },
				   		   { 3,  6, 11, 10 } };

		// Not a magic square b/c two entries are switched
		int[][] array9 = { {16,  3,  2, 13},
						   { 5, 10,	11,  8},
						   { 9,  6,  7, 14},
						   { 4, 15, 12,  1} };
		
		// Not a magic square b/c not square
		int[][] array10 = { {16,  3,  2, 13},
						   { 5, 10,	11,  8},
						   { 4, 15, 12,  1} };

		/**** Test Cases ****/

		P5_Phadke_Sharvil_MagicSquare m = new P5_Phadke_Sharvil_MagicSquare();
		
		System.out.println("Test 1 (row sum): " + (m.sumRow(array3, 0) == 325 && m.sumRow(array7, 3) == 20 && m.sumRow(array5, 1) == 34 ? "PASSED" : "**** FAILED *****"));
		System.out.println("Test 2 (col sum): " + (m.sumCol(array3, 0) == 325 && m.sumCol(array7, 2) == 18 && m.sumCol(array7, 3) == 11? "PASSED" : "**** FAILED *****"));
		System.out.println("Test 3 (UL diag): " + (m.sumULDiagonal(array1) == 34 && m.sumULDiagonal(array7) == 13  && m.sumULDiagonal(array3) == 414 ? "PASSED" : "**** FAILED *****"));
		System.out.println("Test 4 (UR diag): " + (m.sumURDiagonal(array1) == 34 && m.sumURDiagonal(array7) == 22  && m.sumURDiagonal(array3) == 304 ? "PASSED" : "**** FAILED *****"));
		System.out.println("Test 5 (unique) : " + (m.unique(array1) && m.unique(array2) && !m.unique(array4) && m.unique(array5) && !m.unique(array6) && !m.unique(array8) && !m.unique(array10) ? "PASSED" : "**** FAILED *****"));
		System.out.println("Test 6 (isMagic): " + (m.isMagicSquare(array1) && m.isMagicSquare(array2) && !m.isMagicSquare(array3) && !m.isMagicSquare(array4) && !m.isMagicSquare(array5) && !m.isMagicSquare(array6) && !m.isMagicSquare(array8) && !m.isMagicSquare(array9) && !m.unique(array10) ? "PASSED" : "**** FAILED *****"));
	}

}