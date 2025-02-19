import java.util.Arrays;
import java.util.Collections;

public class P5_Phadke_SharvilCardTester {

	public static void main(String[] args) {

		char[] symbols = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
		int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
		
		P5_Phadke_Sharvil_Card[] cards = new P5_Phadke_Sharvil_Card[symbols.length * suits.length];
		int counter = 0;
		
		for (int j = 0; j < suits.length; j++) {
			for (int i = 0; i < symbols.length; i++) {
				cards[counter] = new P5_Phadke_Sharvil_Card(symbols[i], values[i], suits[j]);
				counter++;
			}
		}

		System.out.println("Testing your implementation of the Comparable interface");
		for (int i = 0; i < 10; i++) {
			P5_Phadke_Sharvil_Card c1 = cards[(int)(Math.random()*cards.length)];
			P5_Phadke_Sharvil_Card c2 = cards[(int)(Math.random()*cards.length)];			
			System.out.println(c1 + ".compareTo(" + c2 + ") returned " + c1.compareTo(c2));
		}
		
		System.out.println("Before shuffling (this is what the deck looks like in ascending order):");
		System.out.println(Arrays.toString(cards));

		shuffle(cards);
		System.out.println("\nAfter shuffling:\n" + Arrays.toString(cards));

		Arrays.sort(cards);
		System.out.println("\nAfter sorting with Java's built-in sort:\n" + Arrays.toString(cards));

		shuffle(cards);
		bubbleSort(cards);
		System.out.println("\nAfter sorting with your Bubble Sort:\n" + Arrays.toString(cards));
	}
	
	public static void shuffle(P5_Phadke_Sharvil_Card[] cards) {
        for (int i = 0; i < cards.length; i++) {
        	int randIndex = (int)(Math.random()*cards.length);
        	P5_Phadke_Sharvil_Card temp = cards[i];
        	cards[i] = cards[randIndex];
        	cards[randIndex] = temp;
        }		
	}

	public static void bubbleSort(P5_Phadke_Sharvil_Card[] cards) {
		for (int i = 1; i < cards.length; i++) {
			for (int loc = cards.length - 2; loc >= 0; loc--) {
				if (cards[loc+1].compareTo(cards[loc]) < 0) {
					P5_Phadke_Sharvil_Card temp = cards[loc];
					cards[loc] = cards[loc+1];
					cards[loc+1] = temp;
				}
			}
		}
	}
}
