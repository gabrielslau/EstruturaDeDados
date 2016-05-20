package ifrn.tads.estruturadedados.deck;

import junit.framework.TestCase;

public class DeckTest extends TestCase {
    private Deck<Integer> deck;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DeckTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        deck = new Deck<Integer>();
    }

    public void testPush() {
        deck.pushInicio(5);
        deck.pushFim(2);
        deck.pushFim(4);
        deck.pushInicio(1);
        deck.pushInicio(3);
        deck.pushFim(3);

        assertEquals("[3, 1, 5, 2, 4, 3]", deck.toString());
    }

    public void testPop() {
        deck.pushInicio(5);
        deck.pushFim(2);
        deck.pushFim(4);
        deck.pushInicio(1);
        deck.pushInicio(3);
        deck.pushFim(3);

        deck.popInicio();
        deck.popFim();
        deck.popInicio();
        deck.popFim();

        assertEquals("[5, 2]", deck.toString());
    }

    public void testPeek() {
        deck.pushInicio(5);
        deck.pushFim(2);
        deck.pushFim(4);
        deck.pushInicio(1);
        deck.pushInicio(9);
        deck.pushFim(7);

        Integer expectedInicio = 9;
        Integer expectedFim = 7;

        assertEquals(expectedInicio, deck.inicio());
        assertEquals(expectedFim, deck.fim());

        // permanece inalterado
        assertEquals("[9, 1, 5, 2, 4, 7]", deck.toString());
    }

    public void testEmpty() {
        assertTrue(deck.isEmpty());

        deck.pushInicio(5);
        deck.pushInicio(2);
        deck.pushFim(4);
        deck.pushInicio(1);
        deck.pushInicio(3);

        assertFalse(deck.isEmpty());

        deck.flush();

        assertTrue(deck.isEmpty());
    }
}
