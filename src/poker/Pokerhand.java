import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Pokerhand {

    private List<Card> cards;

    public Pokerhand(List<Card> cards) throws IncorrectHandSize {
        this.cards = cards;
        if (!handSize()) {
            throw new IncorrectHandSize("Hand not five cards.");
        }
        cardSort();
    }

    @Override
    public String toString() {
        return "Pokerhand consists of " + cards.toString() + ".";
    }

    public Boolean handSize() {
        return (cards.size() == 5);
    }

    public String getHandType() {
        if (isRoyalFlush()) {
            return "Royal flush";
        } else if (isStraightFlush()) {
            return "Straight flush";
        } else if (isFlush()) {
            return "Flush";
        } else if (isStraight()) {
            return "Straight";
        } else if (isFourofaKind()) {
            return "Four of a kind";
        } else if (isFullHouse()) {
            return "Full house";
        } else if (isThreeofaKind()) {
            return "Three of a kind";
        } else if (isTwoPair()) {
            return "Two pair";
        } else if (isPair()) {
            return "Pair";
        }
        return "High Card";
    }

    private Boolean isFlush() {
        String suit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (!(suit == card.getSuit())) {
                return false;
            }
        }
        return true;
    }

    private Boolean isStraight() {
        for (int i = 0; i < cards.size() - 1; i++) {
            if (numStrengthGetter(i+1) != numStrengthGetter(i)+1) {
                return false;
            }
        }
        return true;
    }

    private Boolean isFourofaKind() {
        if (numStrengthGetter(0) == numStrengthGetter(3)) { // |2, 2, 2, 2|, 1
            return true;
        } else if (numStrengthGetter(1) == numStrengthGetter(4)) { // 4,| 2, 2, 2, 2|
            return true;
        }
        return false;
    }

    private Boolean isFullHouse() {
        if (isThreeofaKind()) {
            if (numStrengthGetter(0) == numStrengthGetter(1) && numStrengthGetter(4) == numStrengthGetter(3)) {
                return true;
            } 
        }
        return false;
    }

    private Boolean isThreeofaKind() {
        if (numStrengthGetter(0) == numStrengthGetter(2)) { // |3, 3, 3|, 2, 1
            return true;
        } else if (numStrengthGetter(1) == numStrengthGetter(3)) { // 5,| 2, 2, 2|, 1
            return true;
        } else if (numStrengthGetter(2) == numStrengthGetter(4)) { // 5, 4, |2, 2, 2|
            return true;
        }
        return false;
    }

    private Boolean isPair() {
        if (numStrengthGetter(0) == numStrengthGetter(1)) { // |4, 4|, 3, 2, 1
            return true;
        } else if (numStrengthGetter(1) == numStrengthGetter(2)) { // 5,| 3, 3|, 2, 1
            return true;
        } else if (numStrengthGetter(2) == numStrengthGetter(3)) { // 5, 4, |2, 2|, 1
            return true;
        } else if (numStrengthGetter(3) == numStrengthGetter(4)) { // 5, 4, 3, |2, 2|
            return true;
        }
        return false;
    }

    private Boolean isTwoPair() {
        if (numStrengthGetter(0) == numStrengthGetter(1)) {
            if (numStrengthGetter(2) == numStrengthGetter(3)) { // x, x, y, y, i
                return true;
            } else if (numStrengthGetter(3) == numStrengthGetter(4)) { // x, x, i, y, y
                return true;
            }
        } else if (numStrengthGetter(1) == numStrengthGetter(2)) {
            if (numStrengthGetter(3) == numStrengthGetter(4)) { // i, x, x, y, y
                return true;
            }
        }
        return false;
    }

    private Boolean isRoyalFlush() {
        if (isFlush() && isStraight()) {
            if (numStrengthGetter(1) == 11) {
                return true;
            }
        }
        return false;
    }

    private Boolean isStraightFlush() {
        if (isFlush() && isStraight()) {
            return true;
        }
        return false;
    }

    private void cardSort() { // Low to high sorter
        Collections.sort(cards, Comparator.comparingInt(Card::getNumberStrength));
    }

    private int numStrengthGetter(int index) {
        return cards.get(index).getNumberStrength();
    }

}
