package ifrn.tads.estruturadedados.tree.heap;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class HeapTest extends TestCase {
    private Heap<Integer> tree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        tree = new Heap<Integer>(Arrays.asList(4, 2, 6, 1, 3, 5));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        tree = null;
    }

    public void testInsertAndDisplay() {
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 5, 4, 3, 6));

        assertEquals(expected, tree.nodes());
    }

    public void testDeleteMinAndDisplay() {
        tree.deleteMin();
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 4, 6));

        assertEquals(expected, tree.nodes());
    }

    public void testDeleteAndDisplay() {
        // delete the element at index 2
        tree.delete(2);
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 4, 6));

        assertEquals(expected, tree.nodes());
    }
}
