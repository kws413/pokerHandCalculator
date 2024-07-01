public class Card {

    private int strength;
    private String suit;

    public Card(int strength, String suit) {
        this.suit = suit;
        this.strength = strength;
    }

    public int getNumberStrength() {
        return strength;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        if (this.strength < 11) {
            return String.valueOf(strength);
        } else if (strength == 11) {
            return "Jack";
        } else if (strength == 12) {
            return "Queen";
        } else if (strength == 13) {
            return "King";
        } else {
            return "Ace";
        }
    }
}
