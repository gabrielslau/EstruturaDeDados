package ifrn.tads.estruturadedados.tree.avl;

import java.util.ArrayList;
import java.util.List;

class AvlTree<T extends Comparable<T>> implements AvlTreeInterface<T> {

    protected AvlNode<T> root;
    protected ArrayList<T> elements;

    public AvlTree() {
        this.root = null;
        elements = new ArrayList<T>();
    }

    public AvlNode<T> root() {
        return root;
    }

    public AvlNode<T> root(AvlNode<T> node) {
        root = node;

        return root;
    }

    public ArrayList<T> elements() {
        return elements;
    }

    public int getBalanceFactor(AvlNode<T> node) {
        return node != null ? node.getBalanceFactor() : 0;
    }

    public int getHeight(AvlNode<T> node) {
        return node != null ? node.getHeight() : 0;
    }

    public AvlNode<T> rotate(AvlNode<T> nodeTarget, T data) {
        int balanceFactor = getBalanceFactor(nodeTarget);

        // Right Rotate (Rotação Simples a Direita)
        if (balanceFactor > 1 && nodeTarget.left().data().compareTo(data) > 0) {
            return rightRotate(nodeTarget);
        }

        // Left Rotate (Rotação Simples a Esquerda)
        if (balanceFactor < -1 && nodeTarget.right().data().compareTo(data) < 0) {
            return leftRotate(nodeTarget);
        }

        // Left Right Rotate (Rotação Dupla a Direita)
        if (balanceFactor > 1 && nodeTarget.left().data().compareTo(data) < 0) {
            nodeTarget.left(leftRotate(nodeTarget.left()));
            return rightRotate(nodeTarget);
        }

        // Right Left Rotate (Rotação Dupla a Esquerda)
        if (balanceFactor < -1 && nodeTarget.right().data().compareTo(data) > 0) {
            nodeTarget.right(rightRotate(nodeTarget.right()));
            return leftRotate(nodeTarget);
        }

        return nodeTarget;
    }

    public AvlNode<T> rightRotate(AvlNode<T> nodeTarget) {
        /*AvlNode<T> pivot = nodeTarget.left();
        AvlNode<T> tempNode = pivot.right();

        // Rotation
        pivot.right(nodeTarget);
        nodeTarget.left(tempNode);

        return pivot;*/

        AvlNode<T> pivot, tempNode;

        pivot = nodeTarget.left();
        tempNode = pivot.right();

        // Rotation
        if (nodeTarget.parent() != null) {
            // Only updates the parent if the node is not the root
            if (nodeTarget.isLeftChild()) {
                nodeTarget.parent().left(pivot);
            } else {
                nodeTarget.parent().right(pivot);
            }
        }
        pivot.parent(nodeTarget.parent());

        nodeTarget.left(tempNode);
        if (tempNode != null) {
            tempNode.parent(nodeTarget);
        }

        pivot.right(nodeTarget);
        nodeTarget.parent(pivot);

        return pivot;
    }

    public AvlNode<T> leftRotate(AvlNode<T> nodeTarget) {
        /*AvlNode<T> pivot = nodeTarget.right();
        AvlNode<T> tempNode = pivot.left();

        // Rotation
        pivot.left(nodeTarget);
        nodeTarget.right(tempNode);

        return pivot;*/

        AvlNode<T> pivot, tempNode;

        pivot = nodeTarget.right();
        tempNode = pivot.left();

        // Rotation
        if (nodeTarget.parent() != null) {
            // Only updates the parent if the node is not the root
            if (nodeTarget.isLeftChild()) {
                nodeTarget.parent().left(pivot);
            } else {
                nodeTarget.parent().right(pivot);
            }
        }
        pivot.parent(nodeTarget.parent());

        nodeTarget.right(tempNode);
        if (tempNode != null) {
            tempNode.parent(nodeTarget);
        }

        pivot.left(nodeTarget);
        nodeTarget.parent(pivot);

        return pivot;
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    public void insert(List<T> elementsToAdd) {
        for (T element : elementsToAdd) {
            insert(element);
        }
    }

    public AvlNode<T> insert(AvlNode<T> nodeTarget, T data) {
        if (nodeTarget == null) {
            return new AvlNode<T>(data);
        }

        // Default BST insertion
        if (nodeTarget.data().compareTo(data) >= 0) {
            boolean isLeaf = nodeTarget.left() == null;
            nodeTarget.left(insert(nodeTarget.left(), data));

            if (isLeaf) {
                nodeTarget.left().parent(nodeTarget);
            }
        } else {
            boolean isLeaf = nodeTarget.right() == null;
            nodeTarget.right(insert(nodeTarget.right(), data));

            if (isLeaf) {
                nodeTarget.right().parent(nodeTarget);
            }
        }

        return rotate(nodeTarget, data);
    }

    public AvlNode<T> find(T targetData) {
        AvlNode<T> current = root();

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

    public void displayPreOrder(AvlNodeInterface<T> root) {
        if (root == null) return;

        elements.add(root.data());
        displayPreOrder(root.left());
        displayPreOrder(root.right());
    }

    public void displayPostOrder(AvlNodeInterface<T> root) {
        if (root == null) return;

        displayPostOrder(root.left());
        displayPostOrder(root.right());
        elements.add(root.data());
    }

    public void displayInOrder(AvlNodeInterface<T> root) {
        if (root == null) return;

        displayInOrder(root.left());
        elements.add(root.data());
        displayInOrder(root.right());
    }

    public boolean delete(T data) {
        // TODO
        return false;
    }
}