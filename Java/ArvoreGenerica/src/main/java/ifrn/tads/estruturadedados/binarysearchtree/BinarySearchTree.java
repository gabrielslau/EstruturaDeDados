package ifrn.tads.estruturadedados.binarysearchtree;

import java.util.ArrayList;
import java.util.List;

class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;
    private ArrayList<T> elements;

    public BinarySearchTree() {
        this.root = null;
        elements = new ArrayList<T>();
    }

    public BinarySearchTree(List<T> elementsToAdd) {
        this();

        for (T element : elementsToAdd) {
            insert(element);
        }
    }

    public ArrayList<T> elements() {
        return elements;
    }

    public Node<T> root() {
        return root;
    }

    public Node<T> find(T targetData) {
        Node<T> current = root;

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
    public Node<T> findSuccessor(Node<T> nodeToDelete) {
        Node<T> successor = null;
        Node<T> current = nodeToDelete.right;

        while (current != null) {
            successor = current;
            current = current.left;
        }

        return successor;
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    protected Node<T> insert(Node<T> nodeTarget, T data) {
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

    public void displayPreOrder(Node<T> root) {
        if (root == null) return;

        elements.add(root.data);
        displayPreOrder(root.left);
        displayPreOrder(root.right);
    }

    public void displayPostOrder(Node<T> root) {
        if (root == null) return;

        displayPostOrder(root.left);
        displayPostOrder(root.right);
        elements.add(root.data);
    }

    public void displayInOrder(Node<T> root) {
        if (root == null) return;

        displayInOrder(root.left);
        elements.add(root.data);
        displayInOrder(root.right);
    }

    public boolean delete(T data) {
        Node<T> node = find(data);

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
            Node<T> nodeSuccessor = findSuccessor(node);

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

    public void oddNodes(Node<T> node) {
        if (node == null) return;

        oddNodes(node.left);

        if ((Integer) node.data % 2 != 0) {
            elements.add(node.data);
        }

        oddNodes(node.right);
    }
}