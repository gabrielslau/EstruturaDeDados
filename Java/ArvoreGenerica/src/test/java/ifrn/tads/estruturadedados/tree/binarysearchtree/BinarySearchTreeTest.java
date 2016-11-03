package ifrn.tads.estruturadedados.tree.binarysearchtree;

import ifrn.tads.estruturadedados.tree.AbstractBTreePrinter;
import ifrn.tads.estruturadedados.tree.INode;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BinarySearchTreeTest extends TestCase {
    private BinarySearchTree<Integer> tree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        tree = new BinarySearchTree<Integer>(Arrays.asList(4, 2, 6, 1, 3, 5));

        /*
             4
            / \
           /   \
          2     6
         / \   / \
        1   3 5   7


        */
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        AbstractBTreePrinter.printNode(tree.root());

        tree = null;
    }

    public void testInsertAndDisplayPostOrder() {
        tree.displayPostOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 3, 2, 5, 6, 4));

        assertEquals(expected, tree.elements());
    }

    public void testInsertAndDisplayPreOrder() {
        tree.displayPreOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(4, 2, 1, 3, 6, 5));

        assertEquals(expected, tree.elements());
    }

    public void testInsertAndDisplayInOrder() {
        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertEquals(expected, tree.elements());
    }

    public void testFindMustReturnNodeWithSameData() {
        Integer expected = 4;
        INode<Integer> result = tree.find(expected);

        assertEquals(expected, result.data());
    }

    public void testFindMustReturnNull() {
        INode<Integer> result = tree.find(9000);
        assertEquals(null, result);
    }

    public void testNodeParent() {
        Integer expected4 = 4;
        Integer expected2 = 2;
        Integer expected6 = 6;

        assertNull(tree.find(4).parent());
        assertEquals(expected4, tree.find(2).parent().data());
        assertEquals(expected4, tree.find(6).parent().data());
        assertEquals(expected2, tree.find(1).parent().data());
        assertEquals(expected2, tree.find(3).parent().data());
        assertEquals(expected6, tree.find(5).parent().data());
    }

    public void testDeleteRootWithNoChildren() {
        tree = new BinarySearchTree<Integer>(Collections.singletonList(4));

        assertTrue(tree.delete(4));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>();

        assertEquals(expected, tree.elements());
    }

    public void testDeleteRootWithBothChild() {
        tree.insert(7);

        assertTrue(tree.delete(4));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 5, 6, 7));

        assertEquals(expected, tree.elements());
    }

    public void testDeleteRightNodeWithLonelinessLeftChildNode() {
        assertTrue(tree.delete(6));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

        assertEquals(expected, tree.elements());
    }

    public void testDeleteRightNodeWithLonelinessRightChildNode() {
        tree.insert(7);
        assertTrue(tree.delete(5));
        // Delete the node with a right child (7)
        assertTrue(tree.delete(6));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 7));

        assertEquals(expected, tree.elements());
    }

    public void testDeleteLeftNodeWithLonelinessLeftChildNode() {
        assertTrue(tree.delete(3));
        // Delete the node with a left child (1)
        assertTrue(tree.delete(2));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 4, 5, 6));

        assertEquals(expected, tree.elements());
    }

    public void testDeleteLeftNodeWithLonelinessRightChildNode() {
        assertTrue(tree.delete(1));
        // Delete the node with a right child (3)
        assertTrue(tree.delete(2));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(3, 4, 5, 6));

        assertEquals(expected, tree.elements());
    }

    public void testDeleteLeftChildNode() {
        assertTrue(tree.delete(2));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 3, 4, 5, 6));

        assertEquals(expected, tree.elements());
    }

    public void testDeleteRightChildNode() {
        tree.insert(7);

        assertTrue(tree.delete(6));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 7));

        assertEquals(expected, tree.elements());
    }

    public void testDeleteLeftLeafNode() {
        assertTrue(tree.delete(1));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5, 6));

        assertEquals(expected, tree.elements());
        assertNull(tree.find(2).left());
    }

    public void testDeleteRightLeafNode() {
        assertTrue(tree.delete(3));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 6));

        assertEquals(expected, tree.elements());
        assertNull(tree.find(2).right());
    }

    public void testGetOddNodes() {
        tree.oddNodes(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 3, 5));

        assertEquals(expected, tree.elements());
    }
}
