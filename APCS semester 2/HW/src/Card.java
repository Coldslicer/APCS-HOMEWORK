/**
 *
 * A card represents a basic playing card.
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card. 
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;

    /**
     * Creates a new card instance.
     *
     * @param symbol a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    /**
     * Creates a new card instance.
     *
     * @param symbol a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     * @param isFaceUp a <code>boolean</code> value defining whether the card is face up
     */    
    public Card(String symbol, int value, boolean isFaceUp) {
        this.symbol = symbol;
        this.value = value;
        this.isFaceUp = isFaceUp;
    }

    /**
     * Getter method to access this card's symbol.
     * 
     * @return this card's symbol.
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * Getter method to access this card's value
     * 
     * @return this Card's value
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Getter method to access if this card is face up
     * 
     * @return if this card is face up
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Setter method to set if this Card is face up
     * 
     * @param state the next state of this card's face up property
     * 
     * @return this card
     */
    public Card setFaceUp(boolean state) {
        isFaceUp = state;
        return this;
    }

    /**
     * Returns whether or not this card is equal to another
     * 
     * @param other the card to compare to
     *  
     *  @return whether or not this Card is equal to other.
     */
    public boolean equals(Card other) {
        return this.value == other.value && this.symbol == other.symbol;
    }
    
    /**
     * Returns this card as a String. If the card is face down, "X"
     * is returned. Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol or and X,
     * depending on whether the card is face up or down.
     */
    @Override
    public String toString() {
        return isFaceUp ? symbol : "X";
    }

    /**
     * Returns the string representation of this card to be saved in a txt safe file. The save string consists of 3 tokens separated by spaces.
     * <br/>1. the symbol
     * <br/>2. the value
     * <br/>3. 1 if face up, else 0
     * @return A set of 3 tokens representing this card's data
     */
    public String toSaveString() {
        return String.format("%s %d %d",symbol,value,isFaceUp ? 1 : 0);
    }
    
    /**
     * Compares this card to another card by value
     * 
     * @param other The other card
     * 
     * @return The difference in value between the this card and the other card
     */
    public int compareTo(Card other) {
        return this.value - other.value;
    }
}
