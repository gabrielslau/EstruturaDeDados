package ifrn.tads.estruturadedados.tree.binarytree;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class BinaryTreeTest extends TestCase {
    private BinaryTree<Integer> binaryTree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        binaryTree = new BinaryTree<Integer>();
        binaryTree.insertElement(6);
        binaryTree.insertElement(2);
        binaryTree.insertElement(4);
        binaryTree.insertElement(1);
        /*
                 6
                /
               2
              / \
             1   4
        */
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        binaryTree = null;
    }

    public void testInsertPostOrder() {
        binaryTree.postOrderTraversel(binaryTree.rootNode);
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 6));

        assertEquals(expected, binaryTree.getElements());
    }

    public void testInsertPreOrder() {
        binaryTree.preOrderTraversel(binaryTree.rootNode);
        ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(6, 2, 1, 4));

        assertEquals(expected, binaryTree.getElements());
    }
}
