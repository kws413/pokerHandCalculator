import java.util.Arrays;

public class Pokerhand {

    private Card[] cards = {};

    public  Pokerhand(Card[] cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Pokerhand [cards=" + Arrays.toString(cards) + "]";
    }

    public Boolean handSize() {
        return (cards.length == 5);
    }

}
