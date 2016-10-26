package ifrn.tads.estruturadedados.tree.avl;

import ifrn.tads.estruturadedados.tree.AbstractNode;
import ifrn.tads.estruturadedados.tree.binarysearchtree.BinarySearchTree;

class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T> implements AvlTreeInterface<T> {

    public AvlTree() {
        super();
    }

    public int getBalanceFactor(Node<T> node) {
        return node != null ? node.getBalanceFactor() : 0;
    }

    public int getHeight(Node<T> node) {
        return node != null ? node.getHeight() : 0;
    }

    public Node<T> tryToRotate(Node<T> nodeTarget, T data) {
        int balanceFactor = getBalanceFactor(nodeTarget);

        // Right Rotate (Rotação Simples a Direita)
        if (balanceFactor > 1 && nodeTarget.left.data.compareTo(data) > 0) {
            return rightRotate(nodeTarget);
        }

        // Left Rotate (Rotação Simples a Esquerda)
        if (balanceFactor < -1 && nodeTarget.right.data.compareTo(data) < 0) {
            return leftRotate(nodeTarget);
        }

        // Left Right Rotate (Rotação Dupla a Direita)
        if (balanceFactor > 1 && nodeTarget.left.data.compareTo(data) < 0) {
            nodeTarget.left = leftRotate((Node<T>) nodeTarget.left);
            return rightRotate(nodeTarget);
        }

        // Right Left Rotate (Rotação Dupla a Esquerda)
        if (balanceFactor < -1 && nodeTarget.right.data.compareTo(data) > 0) {
            nodeTarget.right = rightRotate((Node<T>) nodeTarget.right);
            return leftRotate(nodeTarget);
        }

        return nodeTarget;
    }

    public Node<T> rightRotate(Node<T> nodeTarget) {
        Node<T> rotatedNode = (Node<T>) nodeTarget.left;
        Node<T> tempNode = (Node<T>) rotatedNode.right;

        // Rotation
        rotatedNode.right = nodeTarget;
        nodeTarget.left = tempNode;

        return rotatedNode;
    }

    public Node<T> leftRotate(Node<T> nodeTarget) {
        Node<T> rotatedNode = (Node<T>) nodeTarget.right;
        Node<T> tempNode = (Node<T>) rotatedNode.left;

        // Rotation
        rotatedNode.left = nodeTarget;
        nodeTarget.right = tempNode;

        return rotatedNode;
    }

    public AbstractNode<T> insert(AbstractNode<T> nodeTarget, T data) {
        return insert((Node<T>) nodeTarget, data);
    }

    public Node<T> insert(Node<T> nodeTarget, T data) {
        if (nodeTarget == null) {
            return new Node<T>(data);
        }

        // Default BST insertion
        nodeTarget = (Node<T>) super.insert(nodeTarget, data);

        return tryToRotate(nodeTarget, data);
    }

    public Node<T> find(T targetData) {
        return (Node<T>) super.find(targetData);
    }

    public boolean delete(T data) {
        // TODO
        return false;
    }
}