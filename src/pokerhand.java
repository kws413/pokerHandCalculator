import java.util.Arrays;

public class Pokerhand {

    private Card[] cards = {};

    public Pokerhand(Card[] cards) throws IncorrectHandSize {
        this.cards = cards;
        if (!handSize()) {
            throw new IncorrectHandSize("Hand not five cards.");
        }
    }

    @Override
    public String toString() {
        return "Pokerhand [cards=" + Arrays.toString(cards) + "]";
    }

    public Boolean handSize() {
        return (cards.length == 5);
    }

}
