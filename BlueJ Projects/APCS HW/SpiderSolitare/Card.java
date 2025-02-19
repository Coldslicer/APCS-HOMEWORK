package SpiderSolitare;
import java.util.Comparator;

public class Card {
    public static enum Suite {
        SPADES("Spades",4), HEARTS("Hearts",3), CLUBS("Clubs",2), DIAMONDS("Diamonds",1);
        public String name;
        private int strength;
        Suite(String name, int strength) { this.name = name; this.strength = strength; }
    }
    
    public static StrengthComparator strengthComparator = new StrengthComparator();
    public static SuiteComparator suiteComparator = new SuiteComparator();
    
    private int value;
    private Suite suite;
    
    public Card(String value, String suite) {
        this(parseValue(value),parseSuite(suite));
    }
    
    public Card(int value, Suite suite) {
        this.value = value;
        this.suite = suite;
    }
    
    public static Suite parseSuite(String suite) {
        for (Suite s : new Suite[] {Suite.SPADES,Suite.HEARTS,Suite.CLUBS,Suite.DIAMONDS}) {
            if (suite.equals(s.name)) return s;
        }
        return null;
    }
    
    public static int parseValue(String s) {
        if (s.isEmpty()) return -1;
        if (s.equals("10")) return 10;
        if (s.length() > 1) return -1;
        if (s.charAt(0) >= '2' && s.charAt(0) <= '9') return s.charAt(0) - '0';
        switch (s.charAt(0)) {
            case 'J': return 11;
            case 'Q': return 12;
            case 'K': return 13;
            case 'A': return 1;
        }
        return -1;
    }
    
    public int getStrength() { return value; }
    
    public Suite getSuite() { return suite; }
    
    public String getValue() {
        return strengthAsString(value);
    }
    
    public static String strengthAsString(int value) {
        if (value >= 2 && value <= 10) return new Integer(value).toString();
        switch (value) {
            case 11: return "J";
            case 12: return "Q";
            case 13: return "K";
        }
        return null;
    }
    
    //can only be used for same suite games
    public boolean canBePutOn(Card other) {
        return strengthComparator.compare(this,other) == 1 && suiteComparator.compare(this,other) == 1;
    }
    
    public static class StrengthComparator implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            return c1.value - c2.value;
        }
    }
    
    public static class SuiteComparator implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            return c1.suite.strength - c2.suite.strength;
        }
    }
}
