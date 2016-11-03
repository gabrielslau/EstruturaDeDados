package ifrn.tads.estruturadedados.tree.binarysearchtree;

class BinarySearchTreeNode<T extends Comparable<?>> implements BinarySearchTreeNodeInterface<T> {

    protected T data;
    protected BinarySearchTreeNode<T> left, right, parent;

    public BinarySearchTreeNode(T data) {
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

    public BinarySearchTreeNode<T> left() {
        return left;
    }

    public BinarySearchTreeNode<T> left(BinarySearchTreeNodeInterface<T> node) {
        left = (BinarySearchTreeNode<T>) node;

        return left;
    }

    public BinarySearchTreeNode<T> right() {
        return right;
    }

    public BinarySearchTreeNode<T> right(BinarySearchTreeNodeInterface<T> node) {
        right = (BinarySearchTreeNode<T>) node;

        return right;
    }

    public BinarySearchTreeNode<T> parent() {
        return parent;
    }

    public BinarySearchTreeNode<T> parent(BinarySearchTreeNodeInterface<T> node) {
        parent = (BinarySearchTreeNode<T>) node;

        return parent;
    }

    public BinarySearchTreeNode<T> grandParent() {
        return (parent() != null) ? parent().parent() : null;
    }

    public BinarySearchTreeNodeInterface<T> uncle() {
        if (grandParent() == null) {
            return null;
        } else if (parent().isLeftChild()) {
            return grandParent().right();
        } else {
            return grandParent().left();
        }
    }

    public BinarySearchTreeNodeInterface<T> brother() {
        if (parent() == null) {
            return null;
        } else {
            return isLeftChild() ? parent().right() : parent().left();
        }
    }

    public T data() {
        return data;
    }

    public Integer getHeight() {
        return (hasLeftChild() ? getLeftHeight() : getRightHeight());
    }

    public Integer getLeftHeight() {
        return hasLeftChild() ? left().getHeight() : 0;
    }

    public Integer getRightHeight() {
        return hasRightChild() ? right().getHeight() : 0;
    }
}