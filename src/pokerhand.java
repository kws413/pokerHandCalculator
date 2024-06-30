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
        return "Pokerhand [cards=" + cards.toString() + "]";
    }

    public Boolean handSize() {
        return (cards.size() == 5);
    }

    public String getHandType() {
        return "";
    }

}
