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
    private List<Card> fourofakind;
    private Pokerhand highHand;

    @BeforeEach
    public void setup() throws IncorrectHandSize {
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
        highHand = new Pokerhand(highCard);

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

        fourofakind = new ArrayList<>(List.of(
            new Card(6, "Hearts"),
            new Card(10, "Clubs"),
            new Card(6, "Clubs"),
            new Card(6, "Spades"),
            new Card(6, "Diamonds")
        ));

        pair = new ArrayList<>(List.of(
            new Card(6, "Spades"),
            new Card(6, "Clubs"),
            new Card(10, "Hearts"),
            new Card(8, "Spades"),
            new Card(2, "Diamonds")
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
        assertEquals("Flush", hand.getHandType());
        assertEquals("High Card", highHand.getHandType());
    }

    @Test
    public void handTypeIsStraight() throws IncorrectHandSize {
        Pokerhand hand = new Pokerhand(straight);
        assertEquals("Straight", hand.getHandType());
        assertEquals("High Card", highHand.getHandType());
    }

    @Test 
    public void handTypeIsFourOfAKind() throws IncorrectHandSize {
        Pokerhand hand = new Pokerhand(fourofakind);
        assertEquals("Four of a kind", hand.getHandType());
        assertEquals("High Card", highHand.getHandType());
    }

    @Test
    public void handTypeIsThreeOfAKind() throws IncorrectHandSize {
        // Make four of a kind, three of a kind
        Card card = new Card(5, "Diamonds"); // Card to replace instead of Four of a kind
        fourofakind.remove(0);
        fourofakind.add(card);
        Pokerhand hand = new Pokerhand(fourofakind);
        assertEquals("Three of a kind", hand.getHandType());
        assertEquals("High Card", highHand.getHandType());
    }

    @Test
    public void handTypeIsPair() throws IncorrectHandSize {
        Pokerhand hand = new Pokerhand(pair);
        assertEquals("Pair", hand.getHandType());
        assertEquals("High Card", highHand.getHandType());
    }

    @Test
    public void handTypeIsFullHouse() throws IncorrectHandSize {
        // Make four of a kind, full house
        fourofakind.remove(0);
        Card card = new Card(10, "Spades");
        fourofakind.add(card);
        Pokerhand hand = new Pokerhand(fourofakind);
        assertEquals("Full house", hand.getHandType());
        assertEquals("High Card", highHand.getHandType());
    }

    @Test
    public void handTypeIsTwoPair() throws IncorrectHandSize {
        pair.remove(4);
        Card card = new Card(8, "Diamonds");
        pair.add(card);
        Pokerhand hand = new Pokerhand(pair);
        assertEquals("Two pair", hand.getHandType());
        assertEquals("High Card", highHand.getHandType());
    }
}
