package ifrn.tads.estruturadedados.tree;

public interface INode<T> {

    T data();

    boolean isLeftChild();

    boolean isRightChild();

    boolean hasLeftChild();

    boolean hasRightChild();

    int numberOfChildren();

    Integer getHeight();

    Integer getLeftHeight();

    Integer getRightHeight();

    INode<T> left();

    INode<T> right();

    INode<T> parent();

    INode<T> grandParent();

    INode<T> uncle();

    INode<T> brother();
}