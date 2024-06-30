import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class PokerhandTest {

    private List<Card> straight;
    private List<Card> highCard;
    private List<Card> pair;

    @BeforeEach
    public void setup() {
        straight = Arrays.asList(
            new Card(2, "Diamonds"),
            new Card(3, "Clubs"),
            new Card(4, "Hearts"),
            new Card(5, "Spades"),
            new Card(6, "Diamonds")
        );

        highCard = Arrays.asList(
            new Card(2, "Diamonds"),
            new Card(8, "Clubs"),
            new Card(4, "Hearts"),
            new Card(5, "Spades"),
            new Card(12, "Diamonds")
        );

        pair = Arrays.asList(
            new Card(2, "Diamonds"),
            new Card(3, "Clubs"),
            new Card(4, "Hearts"),
            new Card(10, "Spades"),
            new Card(10, "Diamonds")
        );
    }
    
    @Test
    public void isCard() {
        Card card = new Card(5, "Diamonds");
        assertEquals(5, card.getNumberStrength());
        assertEquals("Diamonds", card.getSuit());
    }

    @Test
    public void allowsFaceCards() {
        Card card = new Card(12, "Hearts");
        assertEquals("Queen", card.getRank());
    }

    @Test
    public void canCreateHand() throws IncorrectHandSize{
        Pokerhand hand = new Pokerhand(highCard);
        assertTrue(hand.handSize());
    }

    @Test
    public void createOversizeHand() {
        Card card = new Card(11, "Diamonds");
        highCard.add(card);
        assertThrows(IncorrectHandSize.class ,() -> {
            @SuppressWarnings("unused")
            Pokerhand hand = new Pokerhand(highCard);
        });
    }

    @Test
    public void canGetHandType() throws IncorrectHandSize{
        Pokerhand hand = new Pokerhand(highCard);
        assertTrue((hand.getHandType() instanceof String));
    }
}
