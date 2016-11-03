package ifrn.tads.estruturadedados.tree.binarysearchtree;

import ifrn.tads.estruturadedados.tree.INode;

public interface BinarySearchTreeNodeInterface<T> extends INode<T> {

    BinarySearchTreeNodeInterface<T> left();

    BinarySearchTreeNodeInterface<T> left(BinarySearchTreeNodeInterface<T> node);

    BinarySearchTreeNodeInterface<T> right();

    BinarySearchTreeNodeInterface<T> right(BinarySearchTreeNodeInterface<T> node);

    BinarySearchTreeNodeInterface<T> parent();

    BinarySearchTreeNodeInterface<T> parent(BinarySearchTreeNodeInterface<T> node);

    BinarySearchTreeNodeInterface<T> grandParent();

    BinarySearchTreeNodeInterface<T> uncle();

    BinarySearchTreeNodeInterface<T> brother();
}