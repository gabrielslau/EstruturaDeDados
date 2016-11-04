package ifrn.tads.estruturadedados.tree.avl;

import ifrn.tads.estruturadedados.tree.INode;

public interface AvlNodeInterface<T> extends INode<T> {

    AvlNodeInterface<T> left();

    AvlNodeInterface<T> left(AvlNodeInterface<T> node);

    AvlNodeInterface<T> right();

    AvlNodeInterface<T> right(AvlNodeInterface<T> node);

    AvlNodeInterface<T> parent();

    AvlNodeInterface<T> parent(AvlNodeInterface<T> node);

    AvlNodeInterface<T> grandParent();

    AvlNodeInterface<T> uncle();

    Integer getBalanceFactor();
}