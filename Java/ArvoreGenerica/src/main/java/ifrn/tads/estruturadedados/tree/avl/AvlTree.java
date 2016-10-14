package ifrn.tads.estruturadedados.tree.avl;

import ifrn.tads.estruturadedados.tree.AbstractNode;
import ifrn.tads.estruturadedados.tree.binarysearchtree.BinarySearchTree;

import java.util.List;

class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    public AvlTree(List<T> elementsToAdd) {
        super(elementsToAdd);
    }

    public int getBalance(Node<T> n) {
        if (n != null) {
            return (getHeight((Node<T>) n.left) - getHeight((Node<T>) n.right));
        }
        return 0;
    }

    public int getHeight(Node<T> n) {
        if (n != null) {
            return n.height;
        }
        return 0;
    }

    public Node<T> rightRotate(Node<T> y) {
        Node<T> x = (Node<T>) y.left;
        Node<T> T2 = (Node<T>) x.right;

        // Rotation
        x.right = y;
        y.left = T2;

        // update their heights
        x.height = Math.max(getHeight((Node<T>) x.left), getHeight((Node<T>) x.right)) + 1;
        y.height = Math.max(getHeight((Node<T>) y.left), getHeight((Node<T>) y.right)) + 1;

        return x;
    }

    public Node<T> leftRotate(Node<T> x) {
        Node<T> y = (Node<T>) x.right;
        Node<T> T2 = (Node<T>) y.left;

        // Rotation
        y.left = x;
        x.right = T2;

        // update their heights
        x.height = Math.max(getHeight((Node<T>) x.left), getHeight((Node<T>) x.right)) + 1;
        y.height = Math.max(getHeight((Node<T>) y.left), getHeight((Node<T>) y.right)) + 1;

        return y;
    }

    protected AbstractNode<T> insert(AbstractNode<T> nodeTarget, T data) {
        return insert((Node<T>) nodeTarget, data);
    }

    protected Node<T> insert(Node<T> nodeTarget, T data) {
        if (nodeTarget == null) {
            return new Node<T>(data);
        }

        // Default BST insertion
        nodeTarget = (Node<T>) super.insert(nodeTarget, data);

        int leftHeight = getHeight((Node<T>) nodeTarget.left);
        int rightHeight = getHeight((Node<T>) nodeTarget.right);

        // update the node height
        nodeTarget.height = Math.max(leftHeight, rightHeight) + 1;

        int balance = getBalance(nodeTarget);

        // Left Rotate
        //if (balDiff > 1 && data < node.left.data) {
        if (balance > 1 && nodeTarget.left.data.compareTo(data) >= 0) {
            return rightRotate(nodeTarget);
        }

        // Right Rotate
        //if (balance < -1 && data > nodeTarget.right.data) {
        if (balance < -1 && nodeTarget.right.data.compareTo(data) < 0) {
            return leftRotate(nodeTarget);
        }

        // Left Right Rotate
        //if (balance > 1 && data > nodeTarget.left.data) {
        if (balance > 1 && nodeTarget.left.data.compareTo(data) < 0) {
            nodeTarget.left = leftRotate((Node<T>) nodeTarget.left);
            return rightRotate(nodeTarget);
        }

        // Right Left Rotate
        //if (balance < -1 && data < nodeTarget.right.data) {
        if (balance < -1 && nodeTarget.right.data.compareTo(data) >= 0) {
            nodeTarget.right = rightRotate((Node<T>) nodeTarget.right);
            return leftRotate(nodeTarget);
        }

        return nodeTarget;
    }

    public Node<T> find(T targetData) {
        return (Node<T>) super.find(targetData);
    }

    public boolean delete(T data) {
        // TODO
        return false;
    }
}