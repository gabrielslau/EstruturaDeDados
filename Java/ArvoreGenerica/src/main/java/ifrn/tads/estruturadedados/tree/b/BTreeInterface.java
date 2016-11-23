package ifrn.tads.estruturadedados.tree.b;

import java.util.List;

interface BTreeInterface<T extends Comparable<T>> {

    BTreeNodeInterface<T> root();

    BTreeNodeInterface<T> root(BTreeNodeInterface<T> node);

    void insert(List<T> elementsToAdd);

    BTreeNodeInterface<T> insert(T targetData);

    BTreeNodeInterface<T> find(T targetData);

    boolean delete(T data);
}