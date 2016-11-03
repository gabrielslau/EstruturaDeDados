package ifrn.tads.estruturadedados.tree.rbt;

import ifrn.tads.estruturadedados.tree.INode;

public interface RedBlackTreeNodeInterface<T> extends INode<T> {
    NodeColor getColor();

    void black();

    void red();

    boolean isBlack();

    boolean isRed();

    RedBlackTreeNodeInterface<T> left();

    RedBlackTreeNodeInterface<T> left(RedBlackTreeNodeInterface<T> node);

    RedBlackTreeNodeInterface<T> right();

    RedBlackTreeNodeInterface<T> right(RedBlackTreeNodeInterface<T> node);

    RedBlackTreeNodeInterface<T> parent();

    RedBlackTreeNodeInterface<T> parent(RedBlackTreeNodeInterface<T> node);

    RedBlackTreeNodeInterface<T> grandParent();

    RedBlackTreeNodeInterface<T> uncle();

    RedBlackTreeNodeInterface<T> brother();

    RedBlackTreeNodeInterface<T> rotateLeft();

    RedBlackTreeNodeInterface<T> rotateRight();
}