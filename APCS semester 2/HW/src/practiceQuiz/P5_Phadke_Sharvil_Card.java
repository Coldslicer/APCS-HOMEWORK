public class P5_Phadke_Sharvil_Card implements Comparable<P5_Phadke_Sharvil_Card> {

	/* Attributes */
	private char symbol;
	private int value;
	private String suit;
	private boolean isTrump;

	/* Constructors */
	public P5_Phadke_Sharvil_Card(char symbol, int value, String suit) {
		this.symbol = symbol;
		this.value = value;
		this.suit = suit;
		this.isTrump = false;
	}

	public P5_Phadke_Sharvil_Card(char symbol, int value, String suit, boolean isTrump) {
		this.symbol = symbol;
		this.value = value;
		this.suit = suit;
		this.isTrump = isTrump;
	}

	/* Getters and Setters */
	public char getSymbol() {
		return symbol;
	}

	public int getValue() {
		return value;
	}

	public String getSuit() {
		return suit;
	}
	
	public boolean isTrump() {
		return isTrump;
	}

	public void setTrump(boolean isTrump) {
		this.isTrump = isTrump;
	}

	/* Other methods */
	@Override
	public String toString() {
		return symbol + "" + suit.charAt(0);
	}
	
	/* Add your code here */
	@Override
	public int compareTo(P5_Phadke_Sharvil_Card o) {
		if (this.isTrump) {
			if (o.isTrump) {
				return this.value - o.value;
			} else {
				return 1;
			}
		} else {
			if (this.suit.equals(o.suit)) {
				return this.value - o.value;
			} else {
				return this.suit.compareTo(o.suit);
			}
		}
	}
	
}
