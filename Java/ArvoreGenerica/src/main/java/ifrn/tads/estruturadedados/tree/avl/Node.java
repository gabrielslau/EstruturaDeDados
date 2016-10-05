package ifrn.tads.estruturadedados.tree.avl;

class Node<T extends Comparable<?>> {
    T data;
    Integer height;
    Node<T> left, right, parent;

    public Node(T data) {
        this.data = data;
        left = null;
        right = null;
        parent = null;
        height = 1;
    }

    public boolean isLeftChild() {
        return (parent.left != null && parent.left.data == data);
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

    public int numberOfChildren() {
        int number = 0;

        if (hasLeftChild()) number++;
        if (hasRightChild()) number++;

        return number;
    }
}