package ifrn.tads.estruturadedados.fila;

import junit.framework.TestCase;

public class FilaTest extends TestCase {
    private Fila fila;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        fila = new Fila();
    }

    public void testPush() {
        fila.push(3);
        fila.push(5);
        fila.push(8);

        assertEquals("[3, 5, 8]", fila.toString());
    }

    public void testPop() {
        fila.push(3);
        fila.push(5);
        fila.push(8);

        Integer popped = fila.pop();
        Integer expectedPopped = 3;

        assertEquals(expectedPopped, popped);
        assertEquals("[5, 8]", fila.toString());
    }

    public void testSizeAndEmpty() {
        assertTrue(fila.isEmpty());

        fila.push(3);
        fila.push(5);
        fila.pop();
        fila.push(8);
        fila.push(1);

        Integer expectedSize = 3;
        assertEquals(expectedSize, fila.size());

        fila.pop();
        fila.pop();
        fila.pop();

        assertTrue(fila.isEmpty());
    }

    public void testHead() {
        fila.push(3);
        fila.push(5);
        fila.push(8);

        Integer head = fila.head();
        Integer expectedHead = 3;

        assertEquals(expectedHead, head);
        assertEquals("[3, 5, 8]", fila.toString());
    }

    public void testTail() {
        fila.push(3);
        fila.push(5);
        fila.push(8);
        fila.pop();
        fila.push(7);

        Integer tail = fila.tail();
        Integer expectedTail = 7;

        assertEquals(expectedTail, tail);
        assertEquals("[5, 8, 7]", fila.toString());
    }

    public void testFlush() {
        fila.push(3);
        fila.push(5);
        fila.push(8);
        fila.flush();

        assertTrue(fila.isEmpty());
    }
}