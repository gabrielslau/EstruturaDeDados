package ifrn.tads.estruturadedados.tree.avl;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class AvlTreeTest extends TestCase {
    private AvlTree<Integer> tree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        tree = new AvlTree<Integer>(Arrays.asList(4, 2, 1, 5, 6, 9, 14, 11, 10, 20));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        BTreePrinter.printNode(tree.root());

        tree = null;
    }

    public void testInsertAndDisplayInOrder() {
        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 6, 9, 10, 11, 14, 20));

        assertEquals(expected, tree.elements());
    }

    public void testRoot() {
        Node<Integer> expected = tree.find(5);

        assertEquals(expected, tree.root());
    }

    public void testFindMustReturnNodeWithSameData() {
        Integer expected = 4;
        Node<Integer> result = tree.find(expected);

        assertEquals(expected, result.data);
    }

    public void testFindMustReturnNull() {
        Node<Integer> result = tree.find(9000);
        assertEquals(null, result);
    }
}
