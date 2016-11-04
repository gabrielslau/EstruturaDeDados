package ifrn.tads.estruturadedados.tree.avl;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class AvlTreeTest extends TestCase {
    private AvlTree<Integer> tree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        tree = new AvlTree<Integer>();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        AvlTreePrinter.printNode(tree.root());

        tree = null;
    }

    public void testInsertAndDisplayInOrder() {
        tree.insert(Arrays.asList(4, 2, 1, 5, 6, 9, 14, 11, 10, 20));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 6, 9, 10, 11, 14, 20));

        assertEquals(expected, tree.elements());
    }

    public void testRoot() {
        AvlNode<Integer> expected;
        tree.insert(Arrays.asList(4, 2, 1));

        expected = tree.find(2);
        assertEquals(expected, tree.root());

        tree.insert(Arrays.asList(3, 5, 6));
        expected = tree.find(4);
        assertEquals(expected, tree.root());
    }

    public void testFindMustReturnNodeWithSameData() {
        tree.insert(Arrays.asList(4, 2, 1, 5, 6));

        Integer expected = 4;
        AvlNode<Integer> result = tree.find(expected);

        assertEquals(expected, result.data());
    }

    public void testHeights() {
        tree.insert(4);
        assertEquals(new Integer(1), tree.find(4).getHeight());

        tree.insert(Arrays.asList(2, 1));

        assertEquals(new Integer(2), tree.find(2).getHeight());
        assertEquals(new Integer(1), tree.find(1).getHeight());
        assertEquals(new Integer(1), tree.find(4).getHeight());

        tree.insert(Arrays.asList(3, 5, 6));

        assertEquals(new Integer(3), tree.find(4).getHeight());
        assertEquals(new Integer(2), tree.find(2).getHeight());
        assertEquals(new Integer(2), tree.find(5).getHeight());
        assertEquals(new Integer(1), tree.find(1).getHeight());
        assertEquals(new Integer(1), tree.find(3).getHeight());
        assertEquals(new Integer(1), tree.find(6).getHeight());
    }

    public void testSimpleLeftRotation() {
        AvlNode<Integer> root;
        tree.insert(Arrays.asList(6, 8));

        root = tree.root();
        assertEquals(new Integer(6), root.data());

        tree.insert(9);

        // Test the rotation
        root = tree.root();
        assertEquals(new Integer(8), root.data());
        assertEquals(new Integer(6), root.left().data());
        assertEquals(new Integer(9), root.right().data());
    }

    public void testSimpleRightRotation() {
        AvlNode<Integer> root;
        tree.insert(Arrays.asList(10, 9));

        root = tree.root();
        assertEquals(new Integer(10), root.data());
        assertEquals(new Integer(9), root.left().data());

        tree.insert(8);

        // Test the rotation
        root = tree.root();
        assertEquals(new Integer(9), root.data());
        assertEquals(new Integer(8), root.left().data());
        assertEquals(new Integer(10), root.right().data());
    }

    public void testDoubleLeftRotation() {
        AvlNode<Integer> root;
        tree.insert(Arrays.asList(8, 94));

        root = tree.root();
        assertEquals(new Integer(8), root.data());
        assertEquals(new Integer(94), root.right().data());

        tree.insert(31);

        // Test the rotation
        root = tree.root();
        assertEquals(new Integer(31), root.data());
        assertEquals(new Integer(8), root.left().data());
        assertEquals(new Integer(94), root.right().data());
    }

    public void testDoubleRightRotation() {
        AvlNode<Integer> root;
        tree.insert(Arrays.asList(10, 5));

        root = tree.root();
        assertEquals(new Integer(10), root.data());
        assertEquals(new Integer(5), root.left().data());

        tree.insert(8);

        // Test the rotation
        root = tree.root();
        assertEquals(new Integer(8), root.data());
        assertEquals(new Integer(5), root.left().data());
        assertEquals(new Integer(10), root.right().data());
    }
}
