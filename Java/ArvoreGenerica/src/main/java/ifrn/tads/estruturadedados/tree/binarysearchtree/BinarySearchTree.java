package ifrn.tads.estruturadedados.tree.binarysearchtree;

import ifrn.tads.estruturadedados.tree.AbstractBinarySearchTree;
import ifrn.tads.estruturadedados.tree.AbstractNode;

import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> extends AbstractBinarySearchTree<T> {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(List<T> elementsToAdd) {
        super(elementsToAdd);
    }

    public Node<T> find(T targetData) {
        return (Node<T>) super.find(targetData);
    }

    public AbstractNode<T> insert(AbstractNode<T> nodeTarget, T data) {
        return insert((Node<T>) nodeTarget, data);
    }

    public Node<T> insert(Node<T> nodeTarget, T data) {
        if (nodeTarget == null) {
            return new Node<T>(data);
        }

        if (nodeTarget.data.compareTo(data) >= 0) {
            boolean isLeaf = nodeTarget.left == null;
            nodeTarget.left = insert(nodeTarget.left, data);

            if (isLeaf) {
                nodeTarget.left.parent = nodeTarget;
            }
        } else {
            boolean isLeaf = nodeTarget.right == null;
            nodeTarget.right = insert(nodeTarget.right, data);

            if (isLeaf) {
                nodeTarget.right.parent = nodeTarget;
            }
        }

        return nodeTarget;
    }

    public boolean delete(T data) {
        AbstractNode<T> node = find(data);

        if (node == null) return false;

        // Case 1: if node to be deleted is a leaf node
        if (node.numberOfChildren() == 0) {
            if (node == root) {
                root = null;
            } else if (node.isLeftChild()) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }

        // Case 2: if node to be deleted has only one child
        else if (node.numberOfChildren() == 1) {
            if (node.hasLeftChild()) {
                node.left.parent = node.parent;

                if (node.isLeftChild()) {
                    node.parent.left = node.left;
                } else {
                    node.parent.right = node.left;
                }
            } else {
                node.right.parent = node.parent;

                if (node.isLeftChild()) {
                    node.parent.left = node.right;
                } else {
                    node.parent.right = node.right;
                }
            }
        }

        // Case 3: if node to be deleted has two childrens
        else {
            AbstractNode<T> nodeSuccessor = findSuccessor(node);

            if (node == root) {
                root = nodeSuccessor;
            } else if (node.isLeftChild()) {
                node.parent.left = nodeSuccessor;
            } else {
                node.parent.right = nodeSuccessor;
            }

            if (nodeSuccessor.isLeftChild()) {
                nodeSuccessor.parent.left = null;
            } else {
                nodeSuccessor.parent.right = null;
            }

            nodeSuccessor.left = node.left;
            nodeSuccessor.right = node.right;
            nodeSuccessor.parent = node.parent;

            if (node.hasLeftChild()) {
                nodeSuccessor.left.parent = nodeSuccessor;
            }

            if (node.hasRightChild()) {
                nodeSuccessor.right.parent = nodeSuccessor;
            }

            nodeSuccessor.parent = node.parent;

            // Move to trash
            node = null;
        }

        return true;
    }
}