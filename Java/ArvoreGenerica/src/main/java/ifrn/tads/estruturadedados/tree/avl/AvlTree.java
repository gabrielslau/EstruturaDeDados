package ifrn.tads.estruturadedados.tree.avl;

import java.util.ArrayList;
import java.util.List;

class AvlTree<T extends Comparable<T>> {

    private Node<T> root;
    private ArrayList<T> elements;

    public AvlTree() {
        this.root = null;
        elements = new ArrayList<T>();
    }

    public AvlTree(List<T> elementsToAdd) {
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

    public int getBalance(Node<T> n) {
        if (n != null) {
            return (getHeight(n.left) - getHeight(n.right));
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
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        // Rotation
        x.right = y;
        y.left = T2;

        // update their heights
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return x;
    }

    public Node<T> leftRotate(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        // Rotation
        y.left = x;
        x.right = T2;

        // update their heights
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    protected Node<T> insert(Node<T> nodeTarget, T data) {
        if (nodeTarget == null) {
            return new Node<T>(data);
        }

        // Default BST insertion
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

        // update the node height
        nodeTarget.height = Math.max(getHeight(nodeTarget.left), getHeight(nodeTarget.right)) + 1;

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
            nodeTarget.left = leftRotate(nodeTarget.left);
            return rightRotate(nodeTarget);
        }

        // Right Left Rotate
        //if (balance < -1 && data < nodeTarget.right.data) {
        if (balance < -1 && nodeTarget.right.data.compareTo(data) >= 0) {
            nodeTarget.right = rightRotate(nodeTarget.right);
            return leftRotate(nodeTarget);
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
        // TODO
        return false;
    }
}