package ifrn.tads.estruturadedados.tree.b;

import java.util.List;

interface BTreeInterface<T extends Comparable<T>> {

    BTreeNodeInterface<T> root();

    BTreeNodeInterface<T> root(BTreeNodeInterface<T> node);

    void add(List<T> keys);

    BTreeNodeInterface<T> add(T key);

    BTreeNodeInterface<T> find(T key);

    boolean remove(T key);
}