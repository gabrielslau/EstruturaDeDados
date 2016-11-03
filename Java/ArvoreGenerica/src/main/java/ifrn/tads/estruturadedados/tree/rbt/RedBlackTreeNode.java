package ifrn.tads.estruturadedados.tree.rbt;

class RedBlackTreeNode<T extends Comparable<?>> implements RedBlackTreeNodeInterface<T> {

    private NodeColor color;
    protected T data;
    protected RedBlackTreeNode<T> left, right, parent;

    RedBlackTreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
        parent = null;

        this.red();
    }

    public T data() {
        return data;
    }

    public NodeColor getColor() {
        return color;
    }

    public void black() {
        this.color = NodeColor.BLACK;
    }

    public void red() {
        this.color = NodeColor.RED;
    }

    public boolean isBlack() {
        return color == NodeColor.BLACK;
    }

    public boolean isRed() {
        return color == NodeColor.RED;
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

    public RedBlackTreeNode<T> left() {
        return left;
    }

    public RedBlackTreeNode<T> left(RedBlackTreeNodeInterface<T> node) {
        left = (RedBlackTreeNode<T>) node;

        return left;
    }

    public RedBlackTreeNode<T> right() {
        return right;
    }

    public RedBlackTreeNode<T> right(RedBlackTreeNodeInterface<T> node) {
        right = (RedBlackTreeNode<T>) node;

        return right;
    }

    public RedBlackTreeNode<T> parent() {
        return parent;
    }

    public RedBlackTreeNode<T> parent(RedBlackTreeNodeInterface<T> node) {
        parent = (RedBlackTreeNode<T>) node;

        return parent;
    }

    public RedBlackTreeNode<T> grandParent() {
        return (parent() != null) ? parent().parent() : null;
    }

    public RedBlackTreeNodeInterface<T> uncle() {
        if (grandParent() == null) {
            return null;
        } else if (parent().isLeftChild()) {
            return grandParent().right();
        } else {
            return grandParent().left();
        }
    }

    public RedBlackTreeNodeInterface<T> brother() {
        if (parent() == null) {
            return null;
        } else {
            return isLeftChild() ? parent().right() : parent().left();
        }
    }

    /**
     * @return the number of descendant black nodes + the current one (if it is black)
     */
    public Integer getHeight() {
        int blackNodes = getColor() == NodeColor.BLACK ? 1 : 0;

        return blackNodes + (hasLeftChild() ? getLeftHeight() : getRightHeight());
    }

    public Integer getLeftHeight() {
        return hasLeftChild() ? left().getHeight() : 0;
    }

    public Integer getRightHeight() {
        return hasRightChild() ? right().getHeight() : 0;
    }

    public RedBlackTreeNode<T> rotateLeft() {
        RedBlackTreeNode<T> pivot, tempNode;

        pivot = this.right();
        tempNode = pivot.left();

        // Rotation
        if (this.parent() != null) {
            // Only updates the parent if the node is not the root
            if (this.isLeftChild()) {
                this.parent().left(pivot);
            } else {
                this.parent().right(pivot);
            }
        }
        pivot.parent(this.parent());

        this.right(tempNode);
        if (tempNode != null) {
            tempNode.parent(this);
        }

        pivot.left(this);
        this.parent(pivot);

        return pivot;
    }

    public RedBlackTreeNode<T> rotateRight() {
        RedBlackTreeNode<T> pivot, tempNode;

        pivot = this.left();
        tempNode = pivot.right();

        // Rotation
        if (this.parent() != null) {
            // Only updates the parent if the node is not the root
            if (this.isLeftChild()) {
                this.parent().left(pivot);
            } else {
                this.parent().right(pivot);
            }
        }
        pivot.parent(this.parent());

        this.left(tempNode);
        if (tempNode != null) {
            tempNode.parent(this);
        }

        pivot.right(this);
        this.parent(pivot);

        return pivot;
    }
}