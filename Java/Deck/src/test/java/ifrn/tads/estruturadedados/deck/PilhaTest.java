package ifrn.tads.estruturadedados.deck;

import ifrn.tads.estruturadedados.normal.Pilha;
import junit.framework.TestCase;

public class PilhaTest extends TestCase {
    private Pilha<Integer> pilha;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PilhaTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        pilha = new Pilha<Integer>();
    }

    public void testPush() {
        pilha.push(5);
        pilha.push(2);
        pilha.push(4);
        pilha.push(1);
        pilha.push(3);

        assertEquals("[5, 2, 4, 1, 3]", pilha.toString());
    }

    public void testPop() {
        pilha.push(5);
        pilha.push(2);
        pilha.push(4);
        pilha.push(1);
        pilha.push(3);

        pilha.pop();
        pilha.pop();
        pilha.pop();

        assertEquals("[5, 2]", pilha.toString());
    }

    public void testPeek() {
        pilha.push(5);
        pilha.push(2);
        pilha.push(4);
        pilha.push(1);
        pilha.push(3);

        Integer expected = 3;

        assertEquals(expected, pilha.peek());
        assertEquals(expected, pilha.peek());
        assertEquals(expected, pilha.peek());

        // permanece inalterado
        assertEquals("[5, 2, 4, 1, 3]", pilha.toString());
    }

    public void testEmpty() {
        assertTrue(pilha.isEmpty());

        pilha.push(5);
        pilha.push(2);
        pilha.push(4);
        pilha.push(1);
        pilha.push(3);

        assertFalse(pilha.isEmpty());

        pilha.pop();
        pilha.pop();
        pilha.pop();
        pilha.pop();
        pilha.pop();

        assertTrue(pilha.isEmpty());
    }
}
