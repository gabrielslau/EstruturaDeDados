package ifrn.tads.estruturadedados.tree.avl;

import java.util.List;

interface AvlTreeInterface<T extends Comparable<T>> {

    int getBalanceFactor(AvlNode<T> n);

    int getHeight(AvlNode<T> node);

    AvlNode<T> rotate(AvlNode<T> nodeTarget, T data);

    AvlNode<T> rightRotate(AvlNode<T> nodeTarget);

    AvlNode<T> leftRotate(AvlNode<T> nodeTarget);

    void insert(List<T> elementsToAdd);

    AvlNode<T> insert(AvlNode<T> nodeTarget, T data);

    AvlNode<T> find(T targetData);

    boolean delete(T data);
}