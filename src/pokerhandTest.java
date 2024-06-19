
import static org.junit.Assert.assertEquals;

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

    /* 
    public void canCreateHand() {
        assert
    }
        */
}
