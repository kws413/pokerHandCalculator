import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class PokerhandTest {

    private List<Card> straight;
    private List<Card> highCard;
    private List<Card> pair;
    private List<Card> flush;

    @BeforeEach
    public void setup() {
        straight = new ArrayList<>(List.of(
            new Card(2, "Diamonds"),
            new Card(3, "Clubs"),
            new Card(4, "Hearts"),
            new Card(5, "Spades"),
            new Card(6, "Diamonds")
        ));

        highCard = new ArrayList<>(List.of(
            new Card(2, "Diamonds"),
            new Card(8, "Clubs"),
            new Card(4, "Hearts"),
            new Card(5, "Spades"),
            new Card(12, "Diamonds")
        ));

        pair = new ArrayList<>(List.of(
            new Card(2, "Diamonds"),
            new Card(3, "Clubs"),
            new Card(4, "Hearts"),
            new Card(10, "Spades"),
            new Card(10, "Diamonds")
        ));

        flush = new ArrayList<>(List.of(
            new Card(2, "Clubs"),
            new Card(3, "Clubs"),
            new Card(4, "Clubs"),
            new Card(10, "Clubs"),
            new Card(10, "Clubs")
        ));
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
        Pokerhand hand = new Pokerhand(straight);
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
        Pokerhand hand = new Pokerhand(pair);
        assertTrue((hand.getHandType() instanceof String));
    }

    @Test
    public void handTypeIsFlush() throws IncorrectHandSize{
        Pokerhand hand = new Pokerhand(flush);
        assertEquals(hand.getHandType(), "Flush");

        Pokerhand falsehand = new Pokerhand(highCard);
        assertEquals(falsehand.getHandType(), "High Card");
    }

    @Test
    public void handTypeIsStraight() throws IncorrectHandSize {
        Pokerhand hand = new Pokerhand(straight);
        assertEquals(hand.getHandType(), "Straight");
        
        Pokerhand falsehand = new Pokerhand(highCard);
        assertEquals(falsehand.getHandType(), "High Card");
    }
}