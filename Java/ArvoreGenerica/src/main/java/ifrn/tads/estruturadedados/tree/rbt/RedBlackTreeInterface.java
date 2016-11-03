package ifrn.tads.estruturadedados.tree.rbt;

import ifrn.tads.estruturadedados.tree.INode;

import java.util.List;

interface RedBlackTreeInterface<T extends Comparable<T>> {

    RedBlackTreeNodeInterface<T> root();

    RedBlackTreeNodeInterface<T> root(RedBlackTreeNodeInterface<T> node);

    int getHeight(INode<T> node);

    void insert(List<T> elementsToAdd);

    RedBlackTreeNodeInterface<T> insert(T targetData);

    RedBlackTreeNodeInterface<T> find(T targetData);

    boolean delete(T data);

    void displayPreOrder(RedBlackTreeNodeInterface<T> root);

    void displayPostOrder(RedBlackTreeNodeInterface<T> root);

    void displayInOrder(RedBlackTreeNodeInterface<T> root);

    void fixRedBlackTree(RedBlackTreeNodeInterface<T> nodeTarget);

    void handleCase1(RedBlackTreeNodeInterface<T> nodeTarget);

    void handleCase2(RedBlackTreeNodeInterface<T> nodeTarget);

    void handleCase3(RedBlackTreeNodeInterface<T> nodeTarget);

    void handleCase4(RedBlackTreeNodeInterface<T> nodeTarget);

    void handleCase5(RedBlackTreeNodeInterface<T> nodeTarget);
}