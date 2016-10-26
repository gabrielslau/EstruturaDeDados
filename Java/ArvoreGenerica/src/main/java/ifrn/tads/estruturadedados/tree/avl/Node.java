package ifrn.tads.estruturadedados.tree.avl;

class Node<T extends Comparable<?>> extends ifrn.tads.estruturadedados.tree.binarysearchtree.Node<T> {

    public Node(T data) {
        super(data);
    }

    public Integer getHeight() {
        return Math.max(getLeftHeight(), getRightHeight()) + 1;
    }

    public Integer getLeftHeight() {
        return hasLeftChild() ? ((Node<T>) left).getHeight() : 0;
    }

    public Integer getRightHeight() {
        return hasRightChild() ? ((Node<T>) right).getHeight() : 0;
    }

    public Integer getBalanceFactor() {
        return (getLeftHeight() - getRightHeight());
    }
}