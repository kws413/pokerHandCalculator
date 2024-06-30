import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class pokerhandTest {
    
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
        Card[] allcards = new Card[5];
        for (int i = 0; i < 5; i++) {
            Card card = new Card(i, "Hearts");
            allcards[i] = card;
        }
        Pokerhand hand = new Pokerhand(allcards);
        assertTrue(hand.handSize());

    }

    @Test
    public void createOversizeHand() {
        Card[] allcards = new Card[6];
        for (int i = 0; i < 6; i++) {
            Card card = new Card(i, "Hearts");
            allcards[i] = card;
        }
        assertThrows(IncorrectHandSize.class ,() -> {
            @SuppressWarnings("unused")
            Pokerhand hand = new Pokerhand(allcards);
        });
    }

    @Test
    public void canGetHandType() throws IncorrectHandSize{
        Card[] allcards = new Card[5];
        for (int i = 0; i < 5; i++) {
            Card card = new Card(i, "Hearts");
            allcards[i] = card;
        }
        Pokerhand hand = new Pokerhand(allcards);
        assertTrue((hand.getHandType() instanceof String));
    }
}
