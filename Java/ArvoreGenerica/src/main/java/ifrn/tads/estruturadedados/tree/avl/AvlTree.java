package ifrn.tads.estruturadedados.tree.avl;

import ifrn.tads.estruturadedados.tree.AbstractNode;
import ifrn.tads.estruturadedados.tree.binarysearchtree.BinarySearchTree;

import java.util.List;

class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    public AvlTree(List<T> elementsToAdd) {
        super(elementsToAdd);
    }

    public int getBalanceFactor(Node<T> n) {
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

    public Node<T> rightRotate(Node<T> nodeTarget) {
        Node<T> rotatedNode = (Node<T>) nodeTarget.left;
        Node<T> tempNode = (Node<T>) rotatedNode.right;

        // Rotation
        rotatedNode.right = nodeTarget;
        nodeTarget.left = tempNode;

        // update their heights
        rotatedNode.height = Math.max(getHeight((Node<T>) rotatedNode.left), getHeight((Node<T>) rotatedNode.right)) + 1;
        nodeTarget.height = Math.max(getHeight((Node<T>) nodeTarget.left), getHeight((Node<T>) nodeTarget.right)) + 1;

        return rotatedNode;
    }

    public Node<T> leftRotate(Node<T> nodeTarget) {
        Node<T> rotatedNode = (Node<T>) nodeTarget.right;
        Node<T> tempNode = (Node<T>) rotatedNode.left;

        // Rotation
        rotatedNode.left = nodeTarget;
        nodeTarget.right = tempNode;

        // update their heights
        nodeTarget.height = Math.max(getHeight((Node<T>) nodeTarget.left), getHeight((Node<T>) nodeTarget.right)) + 1;
        rotatedNode.height = Math.max(getHeight((Node<T>) rotatedNode.left), getHeight((Node<T>) rotatedNode.right)) + 1;

        return rotatedNode;
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

        int balanceFactor = getBalanceFactor(nodeTarget);

        // Left Rotate
        //if (balDiff > 1 && data < node.left.data) {
        if (balanceFactor > 1 && nodeTarget.left.data.compareTo(data) >= 0) {
            return rightRotate(nodeTarget);
        }

        // Right Rotate
        //if (balanceFactor < -1 && data > nodeTarget.right.data) {
        if (balanceFactor < -1 && nodeTarget.right.data.compareTo(data) < 0) {
            return leftRotate(nodeTarget);
        }

        // Left Right Rotate
        //if (balanceFactor > 1 && data > nodeTarget.left.data) {
        if (balanceFactor > 1 && nodeTarget.left.data.compareTo(data) < 0) {
            nodeTarget.left = leftRotate((Node<T>) nodeTarget.left);
            return rightRotate(nodeTarget);
        }

        // Right Left Rotate
        //if (balanceFactor < -1 && data < nodeTarget.right.data) {
        if (balanceFactor < -1 && nodeTarget.right.data.compareTo(data) >= 0) {
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