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
    }

    @Override
    public String toString() {
        return "Pokerhand consists of " + cards.toString() + ".";
    }

    public Boolean handSize() {
        return (cards.size() == 5);
    }

    public String getHandType() {
        if (isFlush()) {
            return "Flush";
        } else if (isStraight()) {
            return "Straight";
        } else if (isFourofaKind()) {
            return "Four of a kind";
        } else if (isThreeofaKind()) {
            return "Three of a kind";
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
        cardSort();
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i+1).getNumberStrength() != cards.get(i).getNumberStrength()+1) {
                return false;
            }
        }
        return true;
    }

    private Boolean isFourofaKind() {
        cardSort();
        if (cards.get(0).getNumberStrength() == cards.get(3).getNumberStrength()) { // |2, 2, 2, 2|, 1
            return true;
        } else if (cards.get(1).getNumberStrength() == cards.get(4).getNumberStrength()) { // 4,| 2, 2, 2, 2|
            return true;
        }
        return false;
    }

    private Boolean isThreeofaKind() {
        cardSort();
        if (cards.get(0).getNumberStrength() == cards.get(2).getNumberStrength()) { // |3, 3, 3|, 2, 1
            return true;
        } else if (cards.get(1).getNumberStrength() == cards.get(3).getNumberStrength()) { // 5,| 2, 2, 2|, 1
            return true;
        } else if (cards.get(2).getNumberStrength() == cards.get(4).getNumberStrength()) { // 5, 4, |2, 2, 2|
            return true;
        }
        return false;
    }

    private void cardSort() {
        Collections.sort(cards, Comparator.comparingInt(Card::getNumberStrength));
    }

}
