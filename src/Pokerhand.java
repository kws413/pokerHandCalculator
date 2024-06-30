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
        }
        return "";
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

}
