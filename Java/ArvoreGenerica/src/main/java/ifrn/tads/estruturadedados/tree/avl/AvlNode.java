package ifrn.tads.estruturadedados.tree.avl;

import ifrn.tads.estruturadedados.tree.INode;

public class AvlNode<T> implements AvlNodeInterface<T> {

    protected T data;
    protected AvlNode<T> left, right, parent;

    public AvlNode(T data) {
        this.data = data;
        left = null;
        right = null;
        parent = null;
    }

    public boolean isLeftChild() {
        return (parent() != null && parent().left() != null && parent().left().data() == data());
    }

    public boolean isRightChild() {
        return (parent() != null && parent().right() != null && parent().right().data() == data());
    }

    public boolean hasLeftChild() {
        return left() != null;
    }

    public boolean hasRightChild() {
        return right() != null;
    }

    public int numberOfChildren() {
        int number = 0;

        if (hasLeftChild()) number++;
        if (hasRightChild()) number++;

        return number;
    }

    public T data() {
        return data;
    }

    public Integer getHeight() {
        return Math.max(getLeftHeight(), getRightHeight()) + 1;
    }

    public Integer getLeftHeight() {
        return hasLeftChild() ? left().getHeight() : 0;
    }

    public Integer getRightHeight() {
        return hasRightChild() ? right().getHeight() : 0;
    }

    public AvlNode<T> left() {
        return left;
    }

    public AvlNode<T> left(AvlNodeInterface<T> node) {
        left = (AvlNode<T>) node;

        return left;
    }

    public AvlNode<T> right() {
        return right;
    }

    public AvlNode<T> right(AvlNodeInterface<T> node) {
        right = (AvlNode<T>) node;

        return right;
    }

    public AvlNode<T> parent() {
        return parent;
    }

    public AvlNode<T> parent(AvlNodeInterface<T> node) {
        parent = (AvlNode<T>) node;

        return parent;
    }

    public AvlNode<T> grandParent() {
        return (parent() != null) ? parent().parent() : null;
    }

    public AvlNode<T> uncle() {
        if (grandParent() == null) {
            return null;
        } else if (parent().isLeftChild()) {
            return grandParent().right();
        } else {
            return grandParent().left();
        }
    }

    public INode<T> brother() {
        if (parent() == null) {
            return null;
        } else {
            return isLeftChild() ? parent().right() : parent().left();
        }
    }

    public Integer getBalanceFactor() {
        return getLeftHeight() - getRightHeight();
    }
}
