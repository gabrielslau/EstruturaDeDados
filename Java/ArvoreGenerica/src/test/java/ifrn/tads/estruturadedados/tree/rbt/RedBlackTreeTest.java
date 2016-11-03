package ifrn.tads.estruturadedados.tree.rbt;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class RedBlackTreeTest extends TestCase {
    private RedBlackTree<Integer> tree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        tree = new RedBlackTree<Integer>();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        RedBlackTreePrinter.printNode(tree.root());

        tree = null;
    }

    public void testInsertAndDisplayInOrder() {
        tree.insert(Arrays.asList(4, 2, 1, 5, 6, 9, 14, 11, 10, 20));

        tree.displayInOrder(tree.root());
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 6, 9, 10, 11, 14, 20));

        assertEquals(expected, tree.elements());
    }

    /**
     * The root is ALWAYS BLACK
     */
    public void testCaseOne() {
        tree.insert(10);

        assertEquals(NodeColor.BLACK, tree.root().getColor());
    }

    /**
     * All new nodes are RED
     */
    public void testCaseTwo() {
        tree.insert(Arrays.asList(10, 6, 22));

        assertEquals(NodeColor.RED, tree.root().left().getColor());
        assertEquals(NodeColor.RED, tree.root().right().getColor());
    }

    /**
     * Red nodes cannot have a red parent or red child
     */
    public void testCaseThree() {
        // Case 3: left side
        tree.insert(Arrays.asList(50, 40, 75, 25));

        RedBlackTreeNode<Integer> node = tree.find(25);

        assertEquals(NodeColor.RED, node.getColor());
        assertEquals(NodeColor.BLACK, node.parent().getColor());
        assertEquals(NodeColor.BLACK, node.grandParent().getColor());
        assertEquals(NodeColor.BLACK, node.uncle().getColor());

        // Case 3: right side
        tree.insert(Arrays.asList(45, 47));

        assertEquals(NodeColor.BLACK, node.getColor());
        assertEquals(NodeColor.RED, node.parent().getColor());
        assertEquals(NodeColor.BLACK, node.brother().getColor());
        assertEquals(NodeColor.RED, node.brother().right().getColor());
    }

    /**
     * Red nodes cannot have a red parent or red child
     * In this case, a rotation must be done
     */
    public void testCaseFour() {
        tree.insert(Arrays.asList(50, 40, 75, 25, 45, 47, 49));

        RedBlackTreeNode<Integer> node = tree.find(47);

        assertNotNull(node);

        // Assert Colors
        assertEquals(NodeColor.BLACK, node.getColor());
        assertEquals(NodeColor.RED, node.left().getColor());
        assertEquals(NodeColor.RED, node.right().getColor());
        assertEquals(NodeColor.RED, node.parent().getColor());
        assertEquals(NodeColor.BLACK, node.brother().getColor());
        assertEquals(NodeColor.BLACK, node.grandParent().getColor());
        assertEquals(NodeColor.BLACK, node.grandParent().right().getColor());

        // Assert Data
        assertEquals(new Integer(45), node.left().data());
        assertEquals(new Integer(49), node.right().data());
        assertEquals(new Integer(40), node.parent().data());
        assertEquals(new Integer(25), node.brother().data());
        assertEquals(new Integer(50), node.grandParent().data());
        assertEquals(new Integer(75), node.grandParent().right().data());
    }

    /**
     * Same case as CaseFour
     * Except that here we have to do a double rotation
     */
    public void testCaseFive() {
        tree.insert(Arrays.asList(50, 40, 75, 25, 44, 47, 46));

        RedBlackTreeNode<Integer> node = tree.find(46);

        assertNotNull(node);

        // Assert Data
        assertEquals(new Integer(44), node.left().data());
        assertEquals(new Integer(47), node.right().data());
        assertEquals(new Integer(40), node.parent().data());
        assertEquals(new Integer(25), node.brother().data());
        assertEquals(new Integer(50), node.grandParent().data());
        assertEquals(new Integer(75), node.grandParent().right().data());

        // Assert Colors
        assertTrue(node.isBlack());
        assertTrue(node.left().isRed());
        assertTrue(node.right().isRed());
        assertTrue(node.parent().isRed());
        assertTrue(node.brother().isBlack());
        assertTrue(node.grandParent().isBlack());
        assertTrue(node.grandParent().right().isBlack());

        tree.insert(45);
        // The new inserted node makes a double rotation and change the root
        assertEquals(tree.root(), node);
        assertEquals(new Integer(40), node.left().data());
        assertEquals(new Integer(25), node.left().left().data());
        assertEquals(new Integer(44), node.left().right().data());
        assertEquals(new Integer(45), node.left().right().right().data());
        assertEquals(new Integer(50), node.right().data());
        assertEquals(new Integer(47), node.right().left().data());
        assertEquals(new Integer(75), node.right().right().data());

        // Assert the new colors
        assertTrue(node.left().isRed());
        assertTrue(node.left().left().isBlack());
        assertTrue(node.left().right().isBlack());
        assertTrue(node.left().right().right().isRed());
        assertTrue(node.right().isRed());
        assertTrue(node.right().left().isBlack());
        assertTrue(node.right().right().isBlack());
    }

    public void testHeight() {
        tree.insert(Arrays.asList(50, 40, 75, 25));
        assertEquals(2, tree.getHeight(tree.root()));

        // After recolor some nodes, the height must be the same
        tree.insert(Arrays.asList(45, 47, 49, 79, 77));
        assertEquals(2, tree.getHeight(tree.root()));

        tree.insert(Arrays.asList(41, 80));
        assertEquals(3, tree.getHeight(tree.root()));
    }
}
