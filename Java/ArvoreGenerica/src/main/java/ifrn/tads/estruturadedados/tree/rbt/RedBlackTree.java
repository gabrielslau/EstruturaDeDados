package ifrn.tads.estruturadedados.tree.rbt;

import ifrn.tads.estruturadedados.tree.INode;

import java.util.ArrayList;
import java.util.List;

class RedBlackTree<T extends Comparable<T>> implements RedBlackTreeInterface<T> {

    private RedBlackTreeNode<T> root;
    private ArrayList<T> elements;

    public RedBlackTree() {
        this.root = null;
        elements = new ArrayList<T>();
    }

    public RedBlackTreeNode<T> root() {
        return root;
    }

    public RedBlackTreeNode<T> root(RedBlackTreeNodeInterface<T> node) {
        root = (RedBlackTreeNode<T>) node;

        return root;
    }

    public ArrayList<T> elements() {
        return elements;
    }

    public int getHeight(INode<T> node) {
        return node != null ? node.getHeight() : 0;
    }

    public void insert(List<T> elementsToAdd) {
        for (T element : elementsToAdd) {
            insert(element);
        }
    }

    public RedBlackTreeNode<T> insert(T targetData) {
        RedBlackTreeNode<T> newNode = new RedBlackTreeNode<T>(targetData);
        RedBlackTreeNode<T> current;

        if (root == null) {
            root(newNode);

            current = root;
        } else {
            current = root;

            while (current != null) {
                int comparator = current.data().compareTo(targetData);

                if (comparator == 0) {
                    // Do not insert the same value twice
                    return current;
                } else if (comparator > 0) {
                    if (current.left() == null) {
                        // bind the nodes
                        newNode.parent(current);
                        current.left(newNode);

                        current = current.left();
                        break;
                    }

                    current = current.left();
                } else {
                    if (current.right() == null) {
                        // bind the nodes
                        newNode.parent(current);
                        current.right(newNode);

                        current = current.right();
                        break;
                    }

                    current = current.right();
                }
            }
        }

        fixRedBlackTree(current);

        return current;
    }

    /**
     * Fix the properties of a RedBlack Tree
     *
     * @param nodeTarget the recently inserted node
     */
    public void fixRedBlackTree(RedBlackTreeNodeInterface<T> nodeTarget) {
        handleCase1(nodeTarget);
    }

    /**
     * Case 1: The root of tree is always black
     *
     * @param nodeTarget the recently inserted node
     */
    public void handleCase1(RedBlackTreeNodeInterface<T> nodeTarget) {
        if (nodeTarget.parent() == null) {
            nodeTarget.black();
        } else {
            handleCase2(nodeTarget);
        }
    }

    /**
     * Case 2: If the parent node is BLACK, do nothing
     *
     * @param nodeTarget the recently inserted node
     */
    public void handleCase2(RedBlackTreeNodeInterface<T> nodeTarget) {
        if (nodeTarget.parent().isRed()) {
            handleCase3(nodeTarget);
        }
    }

    /**
     * Case 3: If the uncle is RED,
     * replace the color with the grandparent
     *
     * @param nodeTarget the recently inserted node
     */
    public void handleCase3(RedBlackTreeNodeInterface<T> nodeTarget) {
        if (nodeTarget.uncle() != null && nodeTarget.uncle().isRed()) {
            nodeTarget.parent().black();
            nodeTarget.uncle().black();
            nodeTarget.grandParent().red();

            // Makes sure that the grandparent is not the root node.
            // Else, continue fixing by that point
            handleCase1(nodeTarget.grandParent());
        } else {
            handleCase4(nodeTarget);
        }
    }

    /**
     * Case 4: Rotate the tree
     *
     * @param nodeTarget the recently inserted node
     */
    public void handleCase4(RedBlackTreeNodeInterface<T> nodeTarget) {
        // Left Right Case
        if (nodeTarget.isRightChild() && nodeTarget.parent().isLeftChild()) {
            nodeTarget.parent().rotateLeft();

            nodeTarget = nodeTarget.left();
        }
        // Right Left Case
        else if (nodeTarget.isLeftChild() && nodeTarget.parent().isRightChild()) {
            nodeTarget.parent().rotateRight();

            nodeTarget = nodeTarget.right();
        }

        handleCase5(nodeTarget);
    }

    /**
     * Case 5: Assert the new colors
     * and finish the rotations
     *
     * @param nodeTarget the recently inserted node
     */
    public void handleCase5(RedBlackTreeNodeInterface<T> nodeTarget) {
        RedBlackTreeNode<T> newPivot;
        boolean updateRoot = nodeTarget.grandParent() == root();

        nodeTarget.parent().black();
        nodeTarget.grandParent().red();

        // Left Left Case
        if (nodeTarget.isLeftChild()) {
            newPivot = (RedBlackTreeNode<T>) nodeTarget.grandParent().rotateRight();
        }
        // Right Right Case
        else {
            newPivot = (RedBlackTreeNode<T>) nodeTarget.grandParent().rotateLeft();
        }

        if (updateRoot) {
            root(newPivot);
        }
    }

    public RedBlackTreeNode<T> find(T targetData) {
        RedBlackTreeNode<T> current = root;

        while (current != null) {
            int comparator = current.data().compareTo(targetData);

            if (comparator == 0) {
                return current;
            } else if (comparator > 0) {
                current = current.left();
            } else {
                current = current.right();
            }
        }

        return null;
    }

    public void displayPreOrder(RedBlackTreeNodeInterface<T> root) {
        if (root == null) return;

        elements.add(root.data());
        displayPreOrder(root.left());
        displayPreOrder(root.right());
    }

    public void displayPostOrder(RedBlackTreeNodeInterface<T> root) {
        if (root == null) return;

        displayPostOrder(root.left());
        displayPostOrder(root.right());
        elements.add(root.data());
    }

    public void displayInOrder(RedBlackTreeNodeInterface<T> root) {
        if (root == null) return;

        displayInOrder(root.left());
        elements.add(root.data());
        displayInOrder(root.right());
    }

    /**
     * @param nodeToDelete node to delete
     * @return the smallest node of the right child of the node which is about to be deleted
     */
    public RedBlackTreeNode<T> findSuccessor(RedBlackTreeNodeInterface<T> nodeToDelete) {
        RedBlackTreeNode<T> successor = null;
        RedBlackTreeNode<T> current = (RedBlackTreeNode<T>) nodeToDelete.right();

        while (current != null) {
            successor = current;
            current = current.left();
        }

        return successor;
    }

    /**
     * Case 1: if node to be deleted is a leaf node
     *
     * @param nodeTarget
     */
    public void handleCase1ForBinarySearchTreeDelete(RedBlackTreeNodeInterface<T> nodeTarget) {
        if (nodeTarget.numberOfChildren() == 0) {
            if (nodeTarget == root) {
                root = null;
            } else if (nodeTarget.isLeftChild()) {
                nodeTarget.parent().left(null);
            } else {
                nodeTarget.parent().right(null);
            }
        } else {
            handleCase2ForBinarySearchTreeDelete(nodeTarget);
        }
    }

    /**
     * Case 2: if node to be deleted has only one child
     *
     * @param nodeTarget
     */
    public void handleCase2ForBinarySearchTreeDelete(RedBlackTreeNodeInterface<T> nodeTarget) {
        if (nodeTarget.numberOfChildren() == 1) {
            if (nodeTarget.hasLeftChild()) {
                nodeTarget.left().parent(nodeTarget.parent());

                handleCase2ForDelete(nodeTarget, nodeTarget.left());

                if (nodeTarget.isLeftChild()) {
                    nodeTarget.parent().left(nodeTarget.left());
                } else {
                    nodeTarget.parent().right(nodeTarget.left());
                }
            } else {
                nodeTarget.right().parent(nodeTarget.parent());

                handleCase2ForDelete(nodeTarget, nodeTarget.right());

                if (nodeTarget.isLeftChild()) {
                    nodeTarget.parent().left(nodeTarget.right());
                } else {
                    nodeTarget.parent().right(nodeTarget.right());
                }
            }
        } else {
            handleCase3ForBinarySearchTreeDelete(nodeTarget);
        }
    }

    /**
     * Case 3: if node to be deleted has two children
     *
     * @param nodeTarget
     */
    public void handleCase3ForBinarySearchTreeDelete(RedBlackTreeNodeInterface<T> nodeTarget) {
        RedBlackTreeNode<T> nodeSuccessor = findSuccessor(nodeTarget);

        if (nodeTarget == root) {
            root = nodeSuccessor;
        } else if (nodeTarget.isLeftChild()) {
            nodeTarget.parent().left(nodeSuccessor);
        } else {
            nodeTarget.parent().right(nodeSuccessor);
        }

        if (nodeSuccessor.isLeftChild()) {
            nodeSuccessor.parent().left(null);
        } else {
            nodeSuccessor.parent().right(null);
        }

        nodeSuccessor.left(nodeTarget.left());
        nodeSuccessor.right(nodeTarget.right());
        nodeSuccessor.parent(nodeTarget.parent());

        if (nodeTarget.hasLeftChild()) {
            nodeSuccessor.left().parent(nodeSuccessor);
        }

        if (nodeTarget.hasRightChild()) {
            nodeSuccessor.right().parent(nodeSuccessor);
        }

        nodeSuccessor.parent(nodeTarget.parent());
    }

    /**
     * Simple Case (2): If either nodeTarget or successor is red
     *
     * @param nodeTarget the node to be deleted
     * @param successor the node to replace the node deleted
     */
    public void handleCase2ForDelete(RedBlackTreeNodeInterface<T> nodeTarget, RedBlackTreeNodeInterface<T> successor) {
        if (nodeTarget.isRed() || successor.isRed()) {
            successor.black();
        }
    }

    public boolean delete(T data) {
        RedBlackTreeNode<T> node = find(data);

        if (node == null) return false;

        handleCase1ForBinarySearchTreeDelete(node);

        return false;
    }
}