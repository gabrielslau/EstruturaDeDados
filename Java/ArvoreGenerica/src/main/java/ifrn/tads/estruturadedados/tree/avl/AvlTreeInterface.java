package ifrn.tads.estruturadedados.tree.avl;

import ifrn.tads.estruturadedados.tree.AbstractNode;

import java.util.List;

interface AvlTreeInterface<T extends Comparable<T>> {

    public int getBalanceFactor(Node<T> n);

    public int getHeight(Node<T> node);

    public Node<T> tryToRotate(Node<T> nodeTarget, T data);

    public Node<T> rightRotate(Node<T> nodeTarget);

    public Node<T> leftRotate(Node<T> nodeTarget);

    public AbstractNode<T> insert(AbstractNode<T> nodeTarget, T data);

    public void insert(List<T> elementsToAdd);

    public Node<T> insert(Node<T> nodeTarget, T data);

    public Node<T> find(T targetData);

    public boolean delete(T data);
}