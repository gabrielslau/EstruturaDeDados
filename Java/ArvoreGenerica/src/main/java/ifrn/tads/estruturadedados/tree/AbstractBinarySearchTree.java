package ifrn.tads.estruturadedados.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinarySearchTree<T extends Comparable<T>> {

    protected AbstractNode<T> root;
    protected ArrayList<T> elements;

    public AbstractBinarySearchTree() {
        this.root = null;
        elements = new ArrayList<T>();
    }

    public AbstractBinarySearchTree(List<T> elementsToAdd) {
        this();

        for (T element : elementsToAdd) {
            insert(element);
        }
    }

    public ArrayList<T> elements() {
        return elements;
    }

    public AbstractNode<T> root() {
        return root;
    }

    public AbstractNode<T> find(T targetData) {
        AbstractNode<T> current = root;

        while (current != null) {
            int comparator = current.data.compareTo(targetData);

            if (comparator == 0) {
                return current;
            } else if (comparator > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    /**
     * @param nodeToDelete node to delete
     * @return the smallest node of the right child of the node which is about to be deleted
     */
    public AbstractNode<T> findSuccessor(AbstractNode<T> nodeToDelete) {
        AbstractNode<T> successor = null;
        AbstractNode<T> current = nodeToDelete.right;

        while (current != null) {
            successor = current;
            current = current.left;
        }

        return successor;
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    protected abstract AbstractNode<T> insert(AbstractNode<T> nodeTarget, T data);

    public void displayPreOrder(AbstractNode<T> root) {
        if (root == null) return;

        elements.add(root.data);
        displayPreOrder(root.left);
        displayPreOrder(root.right);
    }

    public void displayPostOrder(AbstractNode<T> root) {
        if (root == null) return;

        displayPostOrder(root.left);
        displayPostOrder(root.right);
        elements.add(root.data);
    }

    public void displayInOrder(AbstractNode<T> root) {
        if (root == null) return;

        displayInOrder(root.left);
        elements.add(root.data);
        displayInOrder(root.right);
    }

    public abstract boolean delete(T data);

    public void oddNodes(AbstractNode<T> node) {
        if (node == null) return;

        oddNodes(node.left);

        if ((Integer) node.data % 2 != 0) {
            elements.add(node.data);
        }

        oddNodes(node.right);
    }
}